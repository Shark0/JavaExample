package com.shark.example.encrypt;


import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

public class PemEncryptExample {
    public static void main(String[] argv) {
        java.security.Security.addProvider(
                new org.bouncycastle.jce.provider.BouncyCastleProvider()
        );
        String data = "this is test string, time: " + System.currentTimeMillis();
        System.out.println("data: " + data);
        String privateKeyBase64 = "MIIEpAIBAAKCAQEAmDrNYhyrlsNDjsonjJQ/ohFZj1cVCTEQEj/mGHLAnm2OGAE7D6OnhcnY2ElsRwjTf8RzYAccdrbp0i60yds17uYahZ2nie/QYp4Oq2fn9aT93ANjc2HLDeLmkEYvr91xYFEZxDIeQ0t4AAN3LYvpf6DzxjI8Uv+wVvCBTdhu1C3U+ueP2joq3NBdgwr9qxqjsEnFR//r0i+BDFOefYav/v6UgWdXu8eEKeanefiX3PzLEGM0dCwh9O1ifvn+aP+8lcbzHRaSlbkM4VWuIpGPej4Z4ipCgXASCZ1RIRm1jJQO1FZpRpw0xphLMvk+MmRuVWILsvqbZBt6D6Fvwl9ZAQIDAQABAoIBAAGkaMDNNGCs/JaWwbR3iPQzoba1JsnxmI3J2K6c1k0fO3Aw2zVA+7aqxccXi658fuOa9++qQ/lX4c/dpx+iWMOaAeE9ISiQyLu3nsrklREGVaLA+pqtDGa68ufNiggoqGd0mIWQUNlDACln4CpCnyM8VkTmF9aBYaKed5aiZhet3qLii/D4xeJJ3E4nTorsqoK1vUGrdUZPHgNpX5V4Iu8sVoap5JBzUiDfOW6sqtbQlBfroMscP3U8zvOFCd9xpnyrUHc52jYmMTkUGBesbsRBY4cLXovpRjC2zlOsf8yM8NdyPfvXWpHwoWxVOXm7xebTEVjIlRtRtyTnhfJDun0CgYEAx1+bJzDqr97KofD06EEPXhVgY9FiWIT7EiNhARwY7SqKW71+UOXRPtiIlwmstjoajv48nOF48KGvhJfEntzUsyCI9oMonEEAvil+TrHSRWpSsTZB6YomMN7FrwDUibS/Qa4wWjgqFkksUC0WOppsdmBc1EOEon0xPs8oDAsJZxsCgYEAw3dijze3dBcZbs0/eOf6GJHE0knRvKoe8/gD/zPoAKK/pWSHLdFL4JYJlhZ2D3KS+C3FSudqNMU0Yk9+vG5JelC+HnFCiJ23ctRU9RGwSLoME2w3bpcJY+MmMVdfEh3POlxurxJOfCpuBYl0SGgVEXMKOUy5uTWNutisf6yXNhMCgYEAsjZWhKioDz6xmCG/eN+28d1fxLnEE+YCWws+yXQpH84X0LtIbt2OtYNuTLv8IwM6/DhrS7wLk+Si9H2I5ov0oI1jgB5rLYi5NkH2wjcXAq9wbTFXp36ydpqaz7yJPvJOx1IBrJNpo3QS5TP4G7gvXXzzXVVg+LG5VDBt1p4C8KMCgYEAj614sAdnfRjho/WQu2HhDyBPCbgjSEfQWsqqvXM9DQKUVpGPX9lhqeL/huhZvb8dUvnp9MYn5kQy3jTK1t1Ge3dQWHYuTteHmguDq2RmLP88kyZPlKXGzDqSv2AWzO1GfwKkhWGyeUiC+OTxqauAGQRztmy0c/RuirJsEakeuSsCgYBE3JN7hBzPSBJ6kOjcNlKXQHe3Osy0e4z7/6ONJQPNGSuTxq5mXV7S+FUqIjD/YOkIBJp4PMN/XPfmpJOZsZPvrUeLT3EVG1UAJI8FUPSlNgpyAQsvSfYeilzXrIxUYi9OC5r8b3PzeyrnHeRtnmEV7+ldLIdJ4PuEBX3aLTqbPw==";
        System.out.println("private key base64: " + privateKeyBase64);
        String publicKeyBase64 = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAmDrNYhyrlsNDjsonjJQ/ohFZj1cVCTEQEj/mGHLAnm2OGAE7D6OnhcnY2ElsRwjTf8RzYAccdrbp0i60yds17uYahZ2nie/QYp4Oq2fn9aT93ANjc2HLDeLmkEYvr91xYFEZxDIeQ0t4AAN3LYvpf6DzxjI8Uv+wVvCBTdhu1C3U+ueP2joq3NBdgwr9qxqjsEnFR//r0i+BDFOefYav/v6UgWdXu8eEKeanefiX3PzLEGM0dCwh9O1ifvn+aP+8lcbzHRaSlbkM4VWuIpGPej4Z4ipCgXASCZ1RIRm1jJQO1FZpRpw0xphLMvk+MmRuVWILsvqbZBt6D6Fvwl9ZAQIDAQAB";
        System.out.println("public key: " + publicKeyBase64);

        //私鑰加密、公鑰解密——加密
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
        String decryptData = null;
        try {
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(Base64.getDecoder().decode(privateKeyBase64));
            PrivateKey privateKey = keyFactory.generatePrivate(keySpec);
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.DECRYPT_MODE, privateKey);
            byte[] result = cipher.doFinal(Base64.getDecoder().decode(encryptData));
            decryptData = new String(result);
        } catch (NoSuchAlgorithmException | InvalidKeyException | NoSuchPaddingException | IllegalBlockSizeException |
                 InvalidKeySpecException | BadPaddingException e) {
            e.printStackTrace();
        }
        return decryptData;
    }

    private static String publicKeyEncrypt(String publicKeyBase64, String data) {
        String encryptResult = null;
        try {
            X509EncodedKeySpec  keySpec = new X509EncodedKeySpec(Base64.getDecoder().decode(publicKeyBase64));
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            PublicKey publicKey = keyFactory.generatePublic(keySpec);
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);
            byte[] result = cipher.doFinal(data.getBytes());
            encryptResult = Base64.getEncoder().encodeToString(result);
        } catch (NoSuchAlgorithmException | InvalidKeyException | NoSuchPaddingException | IllegalBlockSizeException |
                 InvalidKeySpecException | BadPaddingException e) {
            e.printStackTrace();
        }

        return encryptResult;
    }

    private static String publicKeyDecrypt(String publicKeyBase64, String encryptData) {
        //私鑰加密、公鑰解密——解密
        String decryptResult = null;
        KeyFactory keyFactory;
        try {
            keyFactory = KeyFactory.getInstance("RSA");
            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(Base64.getDecoder().decode(publicKeyBase64));
            PublicKey publicKey = keyFactory.generatePublic(keySpec);
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.DECRYPT_MODE, publicKey);
            byte[] result = cipher.doFinal(Base64.getDecoder().decode(encryptData));
            decryptResult = new String(result);
        } catch (NoSuchAlgorithmException | InvalidKeyException | NoSuchPaddingException | IllegalBlockSizeException |
                 InvalidKeySpecException | BadPaddingException e) {
            e.printStackTrace();
        }
        return decryptResult;
    }


    private static String privateKeyEncrypt(String privateKeyBase64, String data) {
        String encryptResult = null;
        try {
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(Base64.getDecoder().decode(privateKeyBase64));
            PrivateKey privateKey = keyFactory.generatePrivate(keySpec);
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.ENCRYPT_MODE, privateKey);
            byte[] result = cipher.doFinal(data.getBytes());
            encryptResult = Base64.getEncoder().encodeToString(result);

        } catch (NoSuchAlgorithmException | NoSuchPaddingException | IllegalBlockSizeException |
                 InvalidKeySpecException | BadPaddingException | InvalidKeyException e) {
            e.printStackTrace();
        }
        return encryptResult;
    }
}