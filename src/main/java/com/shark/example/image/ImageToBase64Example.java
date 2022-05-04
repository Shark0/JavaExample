package com.shark.example.image;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.Base64;

public class ImageToBase64Example {
    public static void main(String argv[]) throws IOException {
        byte[] fileContent = FileUtils.readFileToByteArray(new File("file/person.png"));
        String encodedString = Base64.getEncoder().encodeToString(fileContent);
        System.out.println(encodedString);
    }
}
