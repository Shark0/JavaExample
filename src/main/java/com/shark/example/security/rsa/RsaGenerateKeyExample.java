package com.shark.example.security.rsa;

public class RsaGenerateKeyExample {
    public static void main(String[] argv) {
        RsaKeyDo rsaKeyDo = new RsaKeyFactory().generateKey();
        System.out.println("public: " + rsaKeyDo.getPublicKey());
        System.out.println("private: " + rsaKeyDo.getPrivateKey());
    }
}
