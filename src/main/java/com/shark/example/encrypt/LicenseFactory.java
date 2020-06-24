package com.shark.example.encrypt;

import com.google.gson.Gson;

import java.util.HashMap;

public class LicenseFactory {

    public LicenseDo generateLicense(LicenseContentDo licenseContentDo) {
        LicenseDo licenseDo = new LicenseDo();
        HashMap<String, String> keyMap = RsaUtil.generateKeys();
        String publicKey = keyMap.get(RsaUtil.OUTPUT_STRING_BASE64_PUBLIC_KEY);
        String privateKey = keyMap.get(RsaUtil.OUTPUT_STRING_BASE64_PRIVATE_KEY);
        String json = new Gson().toJson(licenseContentDo);
        String license = RsaUtil.encryptByPrivateKey(json, privateKey);
        licenseDo.setPublicKey(publicKey);
        licenseDo.setPrivateKey(privateKey);
        licenseDo.setLicense(license);
        return licenseDo;
    }
}
