package com.shark.example.security.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.bouncycastle.util.io.pem.PemObject;
import org.bouncycastle.util.io.pem.PemReader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.spec.EncodedKeySpec;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.text.ParseException;
import java.util.Base64;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

public class JwtExample {
    public static void main(String[] argv) throws IOException, ParseException {
        String key = "MIIEpAIBAAKCAQEAxujmi+Df8mldeI06a23luII4vYBNgAVM00qcB/2gKXZ2fRz5sbar0axKbSaNoqe9bwy4MPzVk2e0jytYPvkWfi1OhCafeSECtv56g5RAGG3Yq4gAhDIJHJIJcsF2sinw3pqZ/WqSGgFk0xFF4TVIaOjL4RWAO3o6/wZfuiaIVkvZmVvx2Q1QTjG2Bl+PiJtjcrzPRiDQTBQCc5KIRsyxtk8sO2SZkA+F7dUDhAKCHQnuOyV3vF59Q8YqLlZsqjfJ0FZojO2cc/6PSvn/YKzBs6qadCYxTJtT9OUe4VoIwj9FGMQvyjFpGft9M0E7GxRX8yfuJqDZwt0EeEshX4DfpwIDAQABAoIBAQC6cp+AaGai/J7wHDmxb48QV4hGIVkD8nPj7qC5YMTbeL5yYlGvglImNQCCtcu3ttH7oQmaabTs+h2mwfNy+9tYpLFfcDC1wvqfe4Rth+5Yt8aMauxI/fnKfR08IxpWwLeWgYZso5OVqs7OhfdhtadNJ0HMl3HezqE0Yc4jWHEdqUxpuhGvkegaxR5Wm3O+wlGQ2EQYSpuRBu3IfoMm5TRT4V/6Rjv6W17p8Kh5el3o3v32UkxKEfEnJcvZyCw/B5IosWCyCYS4HtreUZxL1jCalkeyDp8R61NDs9t8wbLXH0T55cCOWY6Tmmt4IaxRh4hVSTwAgiEkTfJkNjiDjk0hAoGBAPn0Ygvh0xnPGJMuy7Vo1BIFGTcDc6aQoieUTAzcQbGvjckW9UV3D/zWA/ZBliUinQkG0z7VrALth7cxFk+EoYAZ7oTQOIvuEVyOqX1wjVrUlOiaguYkYtgLkT3TegQ2RV+G9tYtaN4dXaqVLr9p7XfRnT/BBatMQYCi2oHJgPj3AoGBAMu4d/tSA2Knf78bmMJ3Mw/J78TIBJ0pw1SHpyXRkJ3dppAnkElUw4nBHB+h5oNHV1noXPaE4cNWwKffrvrf0bu9mbhkPUCOg07Gx9sXQWrgiiPIN6K3uj5yuffhRq4o7YgzdMS9fepmbHYFvsCa/Jps/Oe01GmEF6jQQejLHdLRAoGBAMCvNLoGLu8RZ9dmVqEVG458obB4F7pKesZaGro7POVV+M4Qxaj0HL4C2XEHpFAiO75OpaAlpYFoTJva2mEYVEtlMiruPPkZ4AZi76OElWN0xwn8unXhjNURLbjkgoYmtBns9/GpddLr/l3WvP/QD8CzNS6FhPJFSaV5ZUpx5NuHAoGAOqKirgyB+vA4/ZmE01wZr06xG1NyuIWfugG9Y045MKbuXQl2JxUlns2dOaGQxnJPOwp9T1sZ2+5pTyg7peur4zyENSVs19rtAkyGk5HS+18bg4cKNGRrT7QXwLfvbwtKquwcjM/5oPDQAPxQVgGF+TCrbkmG5cPmR2mBvu5PbDECgYBlb8IpuGJ7DihuJ/tnxYLsc1Hfq4MpTlU49nfCB1myf8khH3iwZAwqynNKixPG2PVoAqhiLyC6oshAhuBIZ2+Gn4VHFaDJPg6DynHG+BSanVcggpYe+kA1r1KXBbjwXHuYAHaOno/OPemjRZyh27b/+MxvH+RLutcrR7fq+OTOkw==";
        File file = new File("mmsFile/jwt.pem");


        byte[] bytes = parsePEMFile(file);
        Date issueAtDate = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, 1);
        Date expirationDate = calendar.getTime();

        HashMap<String, Object> claims = new HashMap<>();
        claims.put("username", "shark");
        claims.put("clientid", "shark");

        String jwt = Jwts.builder()
                .setClaims(claims)
                .setHeaderParam("alg", "RS256")
                .setHeaderParam("typ", "JWT")
                .setIssuedAt(issueAtDate)
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.RS256, generatePrivateKey(key, "RSA"))
                .compact();

        System.out.println("jwt = " + jwt);
    }

    public static PrivateKey generatePrivateKeyFromFile(String filepath, String algorithm) throws IOException {
        byte[] bytes = parsePEMFile(new File(filepath));
        return generatePrivateKey(bytes, algorithm);
    }

    private static byte[] parsePEMFile(File pemFile) throws IOException {
        if (!pemFile.isFile() || !pemFile.exists()) {
            throw new FileNotFoundException(String.format("The file '%s' doesn't exist.", pemFile.getAbsolutePath()));
        }
        PemReader reader = new PemReader(new FileReader(pemFile));
        PemObject pemObject = reader.readPemObject();
        byte[] content = pemObject.getContent();
        reader.close();
        return content;
    }

    private static PrivateKey generatePrivateKey(byte[] keyBytes, String algorithm) {
        PrivateKey privateKey = null;
        try {
            KeyFactory kf = KeyFactory.getInstance(algorithm);
            EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
            privateKey = kf.generatePrivate(keySpec);
        } catch (NoSuchAlgorithmException e) {
            System.out.println("Could not reconstruct the private key, the given algorithm could not be found.");
        } catch (InvalidKeySpecException e) {
            System.out.println("Could not reconstruct the private key");
        }

        return privateKey;
    }

    public static PrivateKey generatePrivateKey(String privateKeyString, String algorithms) {
        try {
            byte[] keyBytes = Base64.getMimeDecoder().decode(privateKeyString);
            PKCS8EncodedKeySpec spec =
                    new PKCS8EncodedKeySpec(keyBytes);
            KeyFactory kf = KeyFactory.getInstance(algorithms);
            return kf.generatePrivate(spec);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            e.printStackTrace();
        }
        return null;
    }
}
