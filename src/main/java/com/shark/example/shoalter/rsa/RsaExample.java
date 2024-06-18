package com.shark.example.shoalter.rsa;

import org.apache.commons.net.util.Base64;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import static java.security.Security.addProvider;

public class RsaExample {
    public static void main(String[] args) {
        String privateKey = "MIIEpAIBAAKCAQEAxujmi+Df8mldeI06a23luII4vYBNgAVM00qcB/2gKXZ2fRz5sbar0axKbSaNoqe9bwy4MPzVk2e0jytYPvkWfi1OhCafeSECtv56g5RAGG3Yq4gAhDIJHJIJcsF2sinw3pqZ/WqSGgFk0xFF4TVIaOjL4RWAO3o6/wZfuiaIVkvZmVvx2Q1QTjG2Bl+PiJtjcrzPRiDQTBQCc5KIRsyxtk8sO2SZkA+F7dUDhAKCHQnuOyV3vF59Q8YqLlZsqjfJ0FZojO2cc/6PSvn/YKzBs6qadCYxTJtT9OUe4VoIwj9FGMQvyjFpGft9M0E7GxRX8yfuJqDZwt0EeEshX4DfpwIDAQABAoIBAQC6cp+AaGai/J7wHDmxb48QV4hGIVkD8nPj7qC5YMTbeL5yYlGvglImNQCCtcu3ttH7oQmaabTs+h2mwfNy+9tYpLFfcDC1wvqfe4Rth+5Yt8aMauxI/fnKfR08IxpWwLeWgYZso5OVqs7OhfdhtadNJ0HMl3HezqE0Yc4jWHEdqUxpuhGvkegaxR5Wm3O+wlGQ2EQYSpuRBu3IfoMm5TRT4V/6Rjv6W17p8Kh5el3o3v32UkxKEfEnJcvZyCw/B5IosWCyCYS4HtreUZxL1jCalkeyDp8R61NDs9t8wbLXH0T55cCOWY6Tmmt4IaxRh4hVSTwAgiEkTfJkNjiDjk0hAoGBAPn0Ygvh0xnPGJMuy7Vo1BIFGTcDc6aQoieUTAzcQbGvjckW9UV3D/zWA/ZBliUinQkG0z7VrALth7cxFk+EoYAZ7oTQOIvuEVyOqX1wjVrUlOiaguYkYtgLkT3TegQ2RV+G9tYtaN4dXaqVLr9p7XfRnT/BBatMQYCi2oHJgPj3AoGBAMu4d/tSA2Knf78bmMJ3Mw/J78TIBJ0pw1SHpyXRkJ3dppAnkElUw4nBHB+h5oNHV1noXPaE4cNWwKffrvrf0bu9mbhkPUCOg07Gx9sXQWrgiiPIN6K3uj5yuffhRq4o7YgzdMS9fepmbHYFvsCa/Jps/Oe01GmEF6jQQejLHdLRAoGBAMCvNLoGLu8RZ9dmVqEVG458obB4F7pKesZaGro7POVV+M4Qxaj0HL4C2XEHpFAiO75OpaAlpYFoTJva2mEYVEtlMiruPPkZ4AZi76OElWN0xwn8unXhjNURLbjkgoYmtBns9/GpddLr/l3WvP/QD8CzNS6FhPJFSaV5ZUpx5NuHAoGAOqKirgyB+vA4/ZmE01wZr06xG1NyuIWfugG9Y045MKbuXQl2JxUlns2dOaGQxnJPOwp9T1sZ2+5pTyg7peur4zyENSVs19rtAkyGk5HS+18bg4cKNGRrT7QXwLfvbwtKquwcjM/5oPDQAPxQVgGF+TCrbkmG5cPmR2mBvu5PbDECgYBlb8IpuGJ7DihuJ/tnxYLsc1Hfq4MpTlU49nfCB1myf8khH3iwZAwqynNKixPG2PVoAqhiLyC6oshAhuBIZ2+Gn4VHFaDJPg6DynHG+BSanVcggpYe+kA1r1KXBbjwXHuYAHaOno/OPemjRZyh27b/+MxvH+RLutcrR7fq+OTOkw==";
        String publicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAxujmi+Df8mldeI06a23luII4vYBNgAVM00qcB/2gKXZ2fRz5sbar0axKbSaNoqe9bwy4MPzVk2e0jytYPvkWfi1OhCafeSECtv56g5RAGG3Yq4gAhDIJHJIJcsF2sinw3pqZ/WqSGgFk0xFF4TVIaOjL4RWAO3o6/wZfuiaIVkvZmVvx2Q1QTjG2Bl+PiJtjcrzPRiDQTBQCc5KIRsyxtk8sO2SZkA+F7dUDhAKCHQnuOyV3vF59Q8YqLlZsqjfJ0FZojO2cc/6PSvn/YKzBs6qadCYxTJtT9OUe4VoIwj9FGMQvyjFpGft9M0E7GxRX8yfuJqDZwt0EeEshX4DfpwIDAQAB";

        String data = "this is test string, time: " + System.currentTimeMillis();
        System.out.println("data: " + data);
        String encrypt = privateKeyEncrypt(privateKey, data);
        System.out.println("encrypt: " + encrypt);
        String result = publicKeyDecrypt(publicKey, encrypt);
        System.out.println("result: " + result);
    }

    private static String publicKeyDecrypt(String publicKeyBase64, String encryptData) {
        //私鑰加密、公鑰解密——解密
        String decryptResult = null;
        X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(Base64.decodeBase64(publicKeyBase64));
        KeyFactory keyFactory;
        try {
            keyFactory = KeyFactory.getInstance("RSA");
            PublicKey publicKey = keyFactory.generatePublic(x509EncodedKeySpec);
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.DECRYPT_MODE, publicKey);
            byte[] result = cipher.doFinal(Base64.decodeBase64(encryptData));
            decryptResult = new String(result);
        } catch (NoSuchAlgorithmException | InvalidKeyException | NoSuchPaddingException | IllegalBlockSizeException |
                 InvalidKeySpecException | BadPaddingException e) {
            e.printStackTrace();
        }
        return decryptResult;
    }


    private static String privateKeyEncrypt(String privateKeyBase64, String data) {
        addProvider(new BouncyCastleProvider());
        String encryptResult = null;
        try {
            byte[] keyBytes = java.util.Base64.getMimeDecoder().decode(privateKeyBase64);
            PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(keyBytes);
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            PrivateKey privateKey =  keyFactory.generatePrivate(spec);
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.ENCRYPT_MODE, privateKey);
            byte[] result = cipher.doFinal(data.getBytes());
            encryptResult = Base64.encodeBase64String(result);

        } catch (NoSuchAlgorithmException | NoSuchPaddingException | IllegalBlockSizeException |
                 InvalidKeySpecException | BadPaddingException | InvalidKeyException e) {
            e.printStackTrace();
        }
        return encryptResult;
    }
}
