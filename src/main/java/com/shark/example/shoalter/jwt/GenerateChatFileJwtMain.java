package com.shark.example.shoalter.jwt;

import com.google.gson.Gson;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Calendar;
import java.util.Date;

public class GenerateChatFileJwtMain {

    public static void main(String[] args) throws IOException {
        String filePath = "example/shark.jpg";
        GenerateChatFileJwtMain generateMmsJwtMain = new GenerateChatFileJwtMain();
        String jwt = generateMmsJwtMain.generateJwt(filePath);
        System.out.println(jwt);
    }

    private String generateJwt(String filePath) throws IOException {
        String secret = loadJwtSecret();

        Date issueAtDate = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, 1);
        Date expirationDate = calendar.getTime();
        return Jwts.builder()
                .claim("filePath", filePath)
                .setHeaderParam("alg", "HS256")
                .setHeaderParam("typ", "JWT")
                .setIssuedAt(issueAtDate)
                .setExpiration(expirationDate)
                .signWith(Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8)), SignatureAlgorithm.HS256)
                .compact();
    }



    private String loadJwtSecret() {
        String path = "mmsFile/chat_file_jwt_key.txt";
        try {
            return Files.readString(Paths.get(path));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
