package com.shark.example.encrypt;

public class RsaGenerateKeyExample {
    public static void main(String[] argv) {
        RsaKeyDo rsaKeyDo = new RsaKeyFactory().generateKey();
        System.out.println("public: " + rsaKeyDo.getPublicKey());
        System.out.println("private: " + rsaKeyDo.getPrivateKey());
    }
}
