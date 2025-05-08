package com.shark.example.security.rsa;

import lombok.Data;

import java.security.PrivateKey;
import java.security.PublicKey;

@Data
public class RsaKeyDo {
    private PublicKey publicKey;
    private PrivateKey privateKey;
}
