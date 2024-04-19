package com.shark.example.security.md5;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Md5Util {
    public static String encrypt(String value) {
        try {
            // Create MD5 Hash
            MessageDigest digest = java.security.MessageDigest.getInstance("MD5");
            digest.update(value.getBytes());
            byte[] messageDigest = digest.digest();
            // Create Hex String
            StringBuilder stringBuilder = new StringBuilder();
            for (byte b : messageDigest) {
                stringBuilder.append(Integer.toHexString(0xFF & b));
            }
            return stringBuilder.toString();
        }
        catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }
}
