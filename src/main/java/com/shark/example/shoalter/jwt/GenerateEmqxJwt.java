package com.shark.example.shoalter.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.Security;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.*;

public class GenerateEmqxJwt {


    public String handle() throws NoSuchAlgorithmException, InvalidKeySpecException {
        Security.addProvider(new BouncyCastleProvider());
        String base64PrivateKey = "MIIEogIBAAKCAQEAk5bByJh89BeS8kun6w1czlzV9iMsescnDeSRFXQbOo/e6XpZLtYGSHyDHsgEK2IbUtaMTvYe4h0Ow5bd/YIE4DksNwVsCW4FslupDt++FrcyauVK8yaK5l8Fj6BJFhbS9rDMLJ8RqgUn7/+CvxZonHIGq7xYE02/xljtjIanKd/Pzix9OIYBZzymqtBNaW2lB/JmbHzrHZX4BnoYm5TmX505lJAR62E7iso6fCO8oEZw2AdhWgtrbRY3WGKp6pga5Y7zYR8FT1HpP5nUw7GoyL0D6zcldwD5IGa1la7KfVJ5sCUIHcGs3KtkDadMaIXoppxSy1gDs1bLwEpI+2hWWwIDAQABAoIBADm/dEVz/PhZPQMXPTXIfba1NnbfnmjQAeT6uRYG3xyT7Km4WKrtzXmRVpC1vIPpGD3aev3uaXkZ24wGPQgTVqsRU86XjYVHCm2WVdjfDPbAabbPzvrC6vhm17syAUbmWVdzKACRoQzAhbbvaA4yy23jY97QjDnJYy9N3mzsxv2n3/yigSG04Tft5/ogDqy+ElvPCT/FmjjW7TqYJlGmAVBA39jGTBTBgtN67JumFwf5fOZox2SoO2mwFSL85Mg1apjbukh4J5LlYBJJvEMbYHnaxYD9O7LS/6M9auhqvTEoW6PvuCvI5j/uSRgXRyHqmiz2aK8TVnOSOf4FuC63gWkCgYEAv/T0vcUY880dAc8u0yv0BeMYtpXBJXZFmLWfHausj2vMNSATvA1qSFTwjmkvQROQPcjjWFKkhTxy8iSbt/U7FExiK9pZeBiVrB+wxewKvLlcDi4v4su+FmdbkFwi+YsdDbwrnvsy1QmcYJLMyliajY3hsA/RL7f6QXDU6Q83ZL8CgYEAxNRUwGCRSwLEP5kDJ8pmoe4yvJlq/3EsZF9EdDSZVS7Lp8jAsSnFejGXBfKFwwTazyhxBJeNtpnJRKwlB4f8MLwc7LO4yFqdlKGLaOcW99P3UFQgj6EPuhlC6QcN85bG2XTGOb/iDY5PC7jn1Vw+alhn/33rgPE4w8V5IFhVKWUCgYA3kNYD1PkrIdXivzGjyVZjGeD+gY0epgTxdqmvshFKlv7O5hEwX4XDmGrmHoaiROJ9MBi3aXzcYKo5c+Vrs949Ovs0DInXnO4+nBHUyUY2aUW/iRktgqLs41B7EmDBccagQjcyVy7YwmZ1Aimr7UgX79lD2OBJ+uzDYsEPEeuMCwKBgBL+Oj9mEh8e4TkR1tAcI9M0XQj99Ok5ToNQ/hYIjVM+9L/4C0ANO9USu/QC9w1eho6+5EkBJteb92P9CIW1hYxeWpwsq4QJ2nLhM8anpfu8JH1H4Ox7Kj1W+rHp0FrVDdMmBYrFDVcpiE8lRJm+EPfKGB77U6JZpJSflZSdcXpRAoGAAqbR8kICCOalvtFJgowH5UTuTRhcL1+aT8qxQRXFbRHzhGa9m/Oe5PANANUTAOSDvbAaQ5Txdf2eUj1UVqW5B68prdDCRbj/7icbw1xQWtVSQuYX1ZsRwPovoo9zJrpWbEYiAXGmEKqJlKOASMEXAPayMmmCNQnD/NT6RSa1e+o=";
        PrivateKey privateKey = generatePrivateKey(base64PrivateKey);

        Date issueAtDate = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.YEAR, 1);
        Date expirationDate = calendar.getTime();
        Map<String, Object> aclMap = new HashMap<>();
        aclMap.put("sub", List.of("123"));
        HashMap<String, Object> claims = new HashMap<>();
        claims.put("acl", aclMap);
        return Jwts.builder()
                .setClaims(claims)
                .setHeaderParam("alg", "RS256")
                .setHeaderParam("typ", "JWT")
                .setIssuedAt(issueAtDate)
                .setExpiration(expirationDate)
                .signWith(privateKey, SignatureAlgorithm.RS256)
                .compact();
    }


    public PrivateKey generatePrivateKey(String base64Key) {


        try {
            byte[] keyBytes = Base64.getDecoder().decode(base64Key);
            PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);

            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            PrivateKey privateKey = keyFactory.generatePrivate(keySpec);
            System.out.println("成功轉換為 RSA 私鑰！");
            System.out.println("私鑰算法: " + privateKey.getAlgorithm());
            System.out.println("私鑰格式: " + privateKey.getFormat());
            return privateKey;
        } catch (Exception e) {
            System.err.println("轉換失敗，錯誤信息: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }


    public static void main(String[] args) throws NoSuchAlgorithmException, InvalidKeySpecException {
        String jwt = new GenerateEmqxJwt().handle();
        System.out.println("jwt: " + jwt);
    }
}
