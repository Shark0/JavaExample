package com.shark.example.security.rsa;

import org.apache.commons.net.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

public class RsaEncryptExample {
    public static void main(String[] argv) {
        String data = "this is test string, time: " + System.currentTimeMillis();
        System.out.println("data: " + data);
        String privateKeyBase64 = "MIIBVAIBADANBgkqhkiG9w0BAQEFAASCAT4wggE6AgEAAkEAlVTtysisPq9zw8KF8EENy616muwX\n" +
                "1AalN+xVX3xaqtDsqi68DDz9CX9krKFycmriPs5PTgSjVntdCzQYSTRjJwIDAQABAkACU5Fk0F2G\n" +
                "mHsoOJaXmJvbaXd4hGcWoYffaCooU6qh8Jsq+KviurdGnrzK6UeFYULJQ4jm8e2/qa+05qBOrXmp\n" +
                "AiEA3iHCeTG9HC6C5jeiLNFeI6JJULYRmjbAImm5E2S5jpUCIQCsGaN1ZsKSir92RVbrs2h4xo/8\n" +
                "N3V+kfUOWrCfxTFHywIgOlTvNthC6vWj1kThx5cre3wQeKt3L+IUznSo71jSCYkCIQCWoUYZTpzE\n" +
                "iF411VDTsc6hzyFdHuleo796FdR7squEawIgU5uPZrlKpzBhRNQeoKt2ZIT7r3yP8cxbKN7d5Wlv\n" +
                "CGs=";
        System.out.println("private key base64: " + privateKeyBase64);
        String publicKeyBase64 = "MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBAJVU7crIrD6vc8PChfBBDcuteprsF9QGpTfsVV98WqrQ\n" +
                "7KouvAw8/Ql/ZKyhcnJq4j7OT04Eo1Z7XQs0GEk0YycCAwEAAQ==\n" +
                "CGs=";
        System.out.println("public key: " + publicKeyBase64);
        String encryptResult = privateKeyEncrypt(privateKeyBase64, data);
        System.out.println("私鑰加密、公鑰解密 — 加密: " + encryptResult);
        String decryptResult = publicKeyDecrypt(publicKeyBase64, encryptResult);
        System.out.println("私鑰加密、公鑰解密 — 解密：" + decryptResult);
        encryptResult = publicKeyEncrypt(publicKeyBase64, data);
        System.out.println("公鑰加密、私鑰解密 — 加密: " + encryptResult);
        decryptResult = privateKeyDecrypt(privateKeyBase64, encryptResult);
        System.out.println("公鑰加密、私鑰解密 — 解密: " + decryptResult);

    }

    private static String privateKeyDecrypt(String privateKeyBase64, String encryptData) {
        PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(Base64.decodeBase64(privateKeyBase64));
        String decryptData = null;
        try {
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            PrivateKey privateKey = keyFactory.generatePrivate(pkcs8EncodedKeySpec);
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.DECRYPT_MODE, privateKey);
            byte[] result = cipher.doFinal(Base64.decodeBase64(encryptData));
            decryptData = new String(result);
        } catch (NoSuchAlgorithmException | InvalidKeyException | NoSuchPaddingException | IllegalBlockSizeException |
                 InvalidKeySpecException | BadPaddingException e) {
            e.printStackTrace();
        }
        return decryptData;
    }

    private static String publicKeyEncrypt(String publicKeyBase64, String data) {
        X509EncodedKeySpec  x509EncodedKeySpec = new X509EncodedKeySpec(Base64.decodeBase64(publicKeyBase64));
        String encryptResult = null;
        try {
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            PublicKey publicKey = keyFactory.generatePublic(x509EncodedKeySpec);
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);
            byte[] result = cipher.doFinal(data.getBytes());
            encryptResult = Base64.encodeBase64String(result);
        } catch (NoSuchAlgorithmException | InvalidKeyException | NoSuchPaddingException | IllegalBlockSizeException |
                 InvalidKeySpecException | BadPaddingException e) {
            e.printStackTrace();
        }

        return encryptResult;
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
        PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(Base64.decodeBase64(privateKeyBase64));
        KeyFactory keyFactory;
        String encryptResult = null;
        try {
            keyFactory = KeyFactory.getInstance("RSA");
            PrivateKey privateKey = keyFactory.generatePrivate(pkcs8EncodedKeySpec);
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
