package com.shark.example.encrypt;

import org.apache.commons.net.util.Base64;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

public class RsaKeyFactory {

    public RsaKeyDo generateKey() {
        try {
            //初始化金鑰
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
            keyPairGenerator.initialize(512);
            KeyPair keyPair = keyPairGenerator.generateKeyPair();
            RSAPublicKey rsaPublicKey = (RSAPublicKey) keyPair.getPublic();
            RSAPrivateKey rsaPrivateKey = (RSAPrivateKey) keyPair.getPrivate();
            RsaKeyDo rsaKeyDo = new RsaKeyDo();
            rsaKeyDo.setPublicKey(Base64.encodeBase64String(rsaPublicKey.getEncoded()));
            rsaKeyDo.setPrivateKey(Base64.encodeBase64String(rsaPrivateKey.getEncoded()));
            return rsaKeyDo;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
