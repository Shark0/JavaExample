package com.shark.example.data.locale;

import java.util.Locale;

public class LocaleExample {
    public static void main(String[] argv) {
        String localeString = "en_US";
        Locale locale = new Locale(localeString);
        System.out.println("locale: " + locale.toString());
    }
}
