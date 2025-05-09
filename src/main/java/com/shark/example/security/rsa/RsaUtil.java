package com.shark.example.security.rsa;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

public class RsaUtil {


    public static RsaKeyBase64Do generateKeys() {
        KeyPairGenerator keyPairGenerator = null;
        try {
            keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        keyPairGenerator.initialize(2048);
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        PrivateKey privateKey = keyPair.getPrivate();
        PublicKey publicKey = keyPair.getPublic();

        Base64.Encoder encoder = Base64.getEncoder();
        String base64PrivateKey = encoder.encodeToString(privateKey.getEncoded());
        String base64PublicKey = encoder.encodeToString(publicKey.getEncoded());

        RsaKeyBase64Do base64Do = new RsaKeyBase64Do();
        base64Do.setPrivateKey(base64PrivateKey);
        base64Do.setPublicKey(base64PublicKey);
        return base64Do;
    }

    public static PrivateKey generatePrivateKey(String base64privateKey) {
        Base64.Decoder decoder = Base64.getDecoder();
        PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(decoder.decode(base64privateKey));
        KeyFactory keyFactory = null;
        try {
            keyFactory = KeyFactory.getInstance("RSA");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        PrivateKey privateKey = null;
        try {
            privateKey = keyFactory.generatePrivate(spec);
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        }
        return privateKey;
    }

    public static PublicKey generatePublicKey(String base64PublicKey){
        Base64.Decoder decoder = Base64.getDecoder();
        X509EncodedKeySpec spec = new X509EncodedKeySpec(decoder.decode(base64PublicKey));
        KeyFactory keyFactory = null;
        try {
            keyFactory = KeyFactory.getInstance("RSA");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        PublicKey publicKey = null;
        try {
            publicKey = keyFactory.generatePublic(spec);
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        }
        return publicKey;
    }

    public static String encryptByPublicKey(String text, String publicKey) {
        Cipher cipher = null;
        try {
            cipher = Cipher.getInstance("RSA");
        } catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
            e.printStackTrace();
        }
        try {
            PublicKey key = generatePublicKey(publicKey);
            cipher.init(Cipher.ENCRYPT_MODE, key);
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        }
        try {
            Base64.Encoder encoder = Base64.getEncoder();
            return encoder.encodeToString(cipher.doFinal(text.getBytes()));
        } catch (IllegalBlockSizeException | BadPaddingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String decryptByPrivateKey(String text, String privateKey) {
        Cipher cipher = null;
        try {
            cipher = Cipher.getInstance("RSA");
        } catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
            e.printStackTrace();
        }
        try {
            PrivateKey key = generatePrivateKey(privateKey);
            cipher.init(Cipher.DECRYPT_MODE, key);
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        }
        try {
            Base64.Decoder decoder = Base64.getDecoder();
            return new String(cipher.doFinal(decoder.decode(text)));
        } catch (IllegalBlockSizeException | BadPaddingException e) {
            e.printStackTrace();
        }
        return null;
    }


    public static String encryptByPrivateKey(String text,PrivateKey privateKey) {
        Cipher cipher = null;
        try {
            cipher = Cipher.getInstance("RSA");
        } catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
            e.printStackTrace();
        }
        try {
            cipher.init(Cipher.ENCRYPT_MODE, privateKey);
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        }
        try {
            Base64.Encoder encoder = Base64.getEncoder();
            return encoder.encodeToString(cipher.doFinal(text.getBytes()));
        } catch (IllegalBlockSizeException | BadPaddingException e) {
            e.printStackTrace();
        }
        return null;
    }


    public static String encryptByPrivateKey(String text, String privateKey) {
        Cipher cipher = null;
        try {
            cipher = Cipher.getInstance("RSA");
        } catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
            e.printStackTrace();
        }
        try {
            PrivateKey key = generatePrivateKey(privateKey);
            cipher.init(Cipher.ENCRYPT_MODE, key);
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        }
        ;
        try {
            Base64.Encoder encoder = Base64.getEncoder();
            return encoder.encodeToString(cipher.doFinal(text.getBytes()));
        } catch (IllegalBlockSizeException | BadPaddingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String decryptByPublicKey(String encryptText, PublicKey publicKey) {
        Cipher cipher = null;
        try {
            cipher = Cipher.getInstance("RSA");
        } catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
            e.printStackTrace();
        }
        try {
            cipher.init(Cipher.DECRYPT_MODE, publicKey);
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        }
        String text = null;
        try {
            Base64.Decoder decoder = Base64.getDecoder();
            text = new String(cipher.doFinal(decoder.decode(encryptText)));
        } catch (IllegalBlockSizeException | BadPaddingException e) {
            e.printStackTrace();
        }
        return text;
    }

    public static String decryptByPublicKey(String encryptText, String publicKey) {
        Cipher cipher = null;
        try {
            cipher = Cipher.getInstance("RSA");
        } catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
            e.printStackTrace();
        }
        try {
            PublicKey key = generatePublicKey(publicKey);
            cipher.init(Cipher.DECRYPT_MODE, key);
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        }
        String text = null;
        try {
            Base64.Decoder decoder = Base64.getDecoder();
            text = new String(cipher.doFinal(decoder.decode(encryptText)));
        } catch (IllegalBlockSizeException | BadPaddingException e) {
            e.printStackTrace();
        }
        return text;
    }

}
