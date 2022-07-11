package com.shark.example.google;

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.translate.Translate;
import com.google.api.services.translate.model.LanguagesListResponse;
import com.google.api.services.translate.model.LanguagesResource;
import com.google.api.services.translate.model.TranslationsListResponse;
import com.google.api.services.translate.model.TranslationsResource;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Arrays;

public class TranslateExample {

    public static void main(String[] argv) throws GeneralSecurityException, IOException {
        String apiKey = "your-api-key";
        Translate translate = new Translate.Builder(
                GoogleNetHttpTransport.newTrustedTransport()
                , GsonFactory.getDefaultInstance(), null)
                .setApplicationName("TranslationSample")
                .build();
        Translate.Languages.List languageList = translate.languages().list();
        languageList.setKey(apiKey);
        LanguagesListResponse languagesListResponse = languageList.execute();
        for(LanguagesResource resource: languagesListResponse.getLanguages()) {
            System.out.println("language =  " + resource.getLanguage());
        }

        Translate.Translations.List translationsList = translate.new Translations().list(
                Arrays.asList(
                        "花飛花落花滿天",
                        "江風雨火對愁眠",
                        "兩岸猿聲啼不住",
                        "只羨鴛鴦不羨仙"),
                "en");
        translationsList.setKey(apiKey);
        TranslationsListResponse response = translationsList.execute();
        for (TranslationsResource translationsResource : response.getTranslations())
        {
            System.out.println(translationsResource.getTranslatedText());
        }
    }
}
