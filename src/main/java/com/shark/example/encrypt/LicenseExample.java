package com.shark.example.encrypt;

import com.google.gson.Gson;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class LicenseExample {
    public static void main(String argv[]) {
        LicenseContentDo licenseContent = generateLicenseContent();
        LicenseDo license = new LicenseFactory().generateLicense(licenseContent);
        System.out.println("license: " + new Gson().toJson(license));
        System.out.println("public key length: " + license.getPublicKey().length());
        System.out.println("license length: " + license.getLicense().length());
        LicenseWorker licenseWorker = new LicenseWorker();
        System.out.println("license content: " + new Gson().toJson(licenseWorker.decrypt(license.getLicense(), license.getPublicKey())));
    }

    private static LicenseContentDo generateLicenseContent() {
        LicenseContentDo licenseContentDo = new LicenseContentDo();
        licenseContentDo.setAlgorithmCorrExploration(true);
        licenseContentDo.setAlgorithmCorrVerification(true);
        licenseContentDo.setAlgorithmPrediction(true);
        licenseContentDo.setAlgorithmProportion(true);
        licenseContentDo.setAlgorithmRootCause(true);
        licenseContentDo.setMaxUsers(5);
        licenseContentDo.setMaxDataStorageSize(5);
        Calendar calendar = Calendar.getInstance();
        int year = 2020;
        int month = 12;
        int day = 31;
        calendar.set(year, month - 1, day, 23, 59, 59);
        Date expiredDate = calendar.getTime();
        System.out.println(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(expiredDate));
        licenseContentDo.setExpirationDate(expiredDate.getTime());
        return licenseContentDo;
    }
}
