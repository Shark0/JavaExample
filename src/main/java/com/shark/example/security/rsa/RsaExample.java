package com.shark.example.security.rsa;

import java.security.PrivateKey;
import java.security.PublicKey;

public class RsaExample {
    public static void main(String[] argv) {
        RsaKeyBase64Do rsaKeyBase64Do = RsaUtil.generateKeys();
        System.out.println("public: " + rsaKeyBase64Do.getPublicKey());
        System.out.println("private: " + rsaKeyBase64Do.getPrivateKey());

        PrivateKey privateKey = RsaUtil.generatePrivateKey(rsaKeyBase64Do.getPrivateKey());
        PublicKey publicKey = RsaUtil.generatePublicKey(rsaKeyBase64Do.getPublicKey());
        String message = "Hello World!";

        String encryptMessage = RsaUtil.encryptByPrivateKey(message, privateKey);
        System.out.println("encryptMessage: " + encryptMessage);
        String decryptMessage = RsaUtil.decryptByPublicKey(encryptMessage, publicKey);
        System.out.println("decryptMessage: " + decryptMessage);
    }
}
