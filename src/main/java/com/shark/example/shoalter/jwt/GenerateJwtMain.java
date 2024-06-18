package com.shark.example.shoalter.jwt;

import com.google.gson.Gson;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Calendar;
import java.util.Date;

public class GenerateJwtMain {

    public static void main(String[] args) throws IOException {
        String userFile = "user_steveng.json";
        GenerateJwtMain generateJwtMain = new GenerateJwtMain();
        String jwt = generateJwtMain.generateJwt(userFile);
        System.out.println(jwt);
    }

    private String generateJwt(String userFile) throws IOException {
        String secret = loadJwtSecret();
        UserDto userDto = loadUserFromFile(userFile);
        Date issueAtDate = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, 1);
        Date expirationDate = calendar.getTime();
        return Jwts.builder()
                .setSubject("mms")
                .claim("typ", "Bearer")
                .claim("userId", userDto.getUserId())
                .claim("username", userDto.getUserName())
                .claim("userCode", userDto.getUserCode())
                .claim("email", userDto.getEmail())
                .claim("merchantId", userDto.getMerchantId())
                .claim("merchantName", userDto.getMerchantName())
                .claim("ssoUserId", userDto.getSsoUserId())
                .claim("roleId", userDto.getRoleId())
                .claim("roleCode", userDto.getRoleCode())
                .claim("roleName", userDto.getRoleName())
                .claim("remark", userDto.getRemark())
                .claim("roleType", userDto.getRoleType())
                .claim("buCodes", userDto.getBuCodes())
                .claim("platformCodes", userDto.getPlatformCodes())
                .claim("system_code",userDto.getSystemCode())
                .setHeaderParam("alg", "HS256")
                .setHeaderParam("typ", "JWT")
                .setIssuedAt(issueAtDate)
                .setExpiration(expirationDate)
                .signWith(Keys.hmacShaKeyFor(Decoders.BASE64.decode(secret)), SignatureAlgorithm.HS256)
                .compact();
    }

    private UserDto loadUserFromFile(String userFile) {
        String path = "mmsFile/" + userFile;
        try {
            String json = Files.readString(Paths.get(path));
            return new Gson().fromJson(json, UserDto.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String loadJwtSecret() {
        String path = "mmsFile/jwt_key.txt";
        try {
            return Files.readString(Paths.get(path));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
