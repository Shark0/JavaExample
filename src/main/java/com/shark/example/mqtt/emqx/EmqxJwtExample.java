package com.shark.example.mqtt.emqx;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.io.IOException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.text.ParseException;
import java.util.*;

public class EmqxJwtExample {
    public static void main(String[] argv) throws IOException, ParseException {
        EmqxJwtExample emqxJwtExample = new EmqxJwtExample();
        System.out.println(emqxJwtExample.generate());
    }

    public String generate() {
        String privateKey = "MIIEpAIBAAKCAQEAxujmi+Df8mldeI06a23luII4vYBNgAVM00qcB/2gKXZ2fRz5sbar0axKbSaNoqe9bwy4MPzVk2e0jytYPvkWfi1OhCafeSECtv56g5RAGG3Yq4gAhDIJHJIJcsF2sinw3pqZ/WqSGgFk0xFF4TVIaOjL4RWAO3o6/wZfuiaIVkvZmVvx2Q1QTjG2Bl+PiJtjcrzPRiDQTBQCc5KIRsyxtk8sO2SZkA+F7dUDhAKCHQnuOyV3vF59Q8YqLlZsqjfJ0FZojO2cc/6PSvn/YKzBs6qadCYxTJtT9OUe4VoIwj9FGMQvyjFpGft9M0E7GxRX8yfuJqDZwt0EeEshX4DfpwIDAQABAoIBAQC6cp+AaGai/J7wHDmxb48QV4hGIVkD8nPj7qC5YMTbeL5yYlGvglImNQCCtcu3ttH7oQmaabTs+h2mwfNy+9tYpLFfcDC1wvqfe4Rth+5Yt8aMauxI/fnKfR08IxpWwLeWgYZso5OVqs7OhfdhtadNJ0HMl3HezqE0Yc4jWHEdqUxpuhGvkegaxR5Wm3O+wlGQ2EQYSpuRBu3IfoMm5TRT4V/6Rjv6W17p8Kh5el3o3v32UkxKEfEnJcvZyCw/B5IosWCyCYS4HtreUZxL1jCalkeyDp8R61NDs9t8wbLXH0T55cCOWY6Tmmt4IaxRh4hVSTwAgiEkTfJkNjiDjk0hAoGBAPn0Ygvh0xnPGJMuy7Vo1BIFGTcDc6aQoieUTAzcQbGvjckW9UV3D/zWA/ZBliUinQkG0z7VrALth7cxFk+EoYAZ7oTQOIvuEVyOqX1wjVrUlOiaguYkYtgLkT3TegQ2RV+G9tYtaN4dXaqVLr9p7XfRnT/BBatMQYCi2oHJgPj3AoGBAMu4d/tSA2Knf78bmMJ3Mw/J78TIBJ0pw1SHpyXRkJ3dppAnkElUw4nBHB+h5oNHV1noXPaE4cNWwKffrvrf0bu9mbhkPUCOg07Gx9sXQWrgiiPIN6K3uj5yuffhRq4o7YgzdMS9fepmbHYFvsCa/Jps/Oe01GmEF6jQQejLHdLRAoGBAMCvNLoGLu8RZ9dmVqEVG458obB4F7pKesZaGro7POVV+M4Qxaj0HL4C2XEHpFAiO75OpaAlpYFoTJva2mEYVEtlMiruPPkZ4AZi76OElWN0xwn8unXhjNURLbjkgoYmtBns9/GpddLr/l3WvP/QD8CzNS6FhPJFSaV5ZUpx5NuHAoGAOqKirgyB+vA4/ZmE01wZr06xG1NyuIWfugG9Y045MKbuXQl2JxUlns2dOaGQxnJPOwp9T1sZ2+5pTyg7peur4zyENSVs19rtAkyGk5HS+18bg4cKNGRrT7QXwLfvbwtKquwcjM/5oPDQAPxQVgGF+TCrbkmG5cPmR2mBvu5PbDECgYBlb8IpuGJ7DihuJ/tnxYLsc1Hfq4MpTlU49nfCB1myf8khH3iwZAwqynNKixPG2PVoAqhiLyC6oshAhuBIZ2+Gn4VHFaDJPg6DynHG+BSanVcggpYe+kA1r1KXBbjwXHuYAHaOno/OPemjRZyh27b/+MxvH+RLutcrR7fq+OTOkw==";
        Date issueAtDate = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, 1);
        Date expirationDate = calendar.getTime();
        Map<String, Object> aclMap = new HashMap<>();
        String user = "stress_test";
        aclMap.put("sub", List.of("hktv/group_chat/user/" + user, "hktv/group_chat/chatroom/#"));
        HashMap<String, Object> claims = new HashMap<>();
        claims.put("acl", aclMap);
        return Jwts.builder()
                .setClaims(claims)
                .setHeaderParam("alg", "RS256")
                .setHeaderParam("typ", "JWT")
                .setIssuedAt(issueAtDate)
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.RS256, generatePrivateKey(privateKey))
                .compact();
    }

    private PrivateKey generatePrivateKey(String privateKeyString) {
        try {
            byte[] keyBytes = Base64.getMimeDecoder().decode(privateKeyString);
            PKCS8EncodedKeySpec spec =
                    new PKCS8EncodedKeySpec(keyBytes);
            KeyFactory kf = KeyFactory.getInstance("RSA");
            return kf.generatePrivate(spec);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            e.printStackTrace();
        }
        return null;
    }
}
