package com.shark.example.encrypt;

import com.google.gson.Gson;

public class LicenseWorker {
    public LicenseContentDo decrypt(String license, String publicKey) {
        String json = RsaUtil.decryptByPublicKey(license, publicKey);
        return new Gson().fromJson(json, LicenseContentDo.class);
    }
}
