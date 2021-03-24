
package com.chat.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;

import java.util.Date;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.tomcat.util.codec.binary.Base64;

public class JwtUtil {

    public JwtUtil() {
    }

    public static SecretKey generalKey(String secret) {
        byte[] encodedKey = Base64.decodeBase64(secret);
        SecretKey key = new SecretKeySpec(encodedKey, 0, encodedKey.length, "AES");
        return key;
    }

    public static String createJWT(String secret, String subject, long ttlSeconds) throws Exception {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS512;
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);
        SecretKey key = generalKey(secret);
        JwtBuilder builder = Jwts.builder().setIssuedAt(now).setSubject(subject).signWith(signatureAlgorithm, key);
        if (ttlSeconds >= 0L) {
            long expMillis = nowMillis + ttlSeconds * 1000L;
            Date exp = new Date(expMillis);
            builder.setExpiration(exp);
        }

        return builder.compact();
    }

    public static String createJWT(String secret, String subject, long ttlSeconds, String type) throws Exception {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS512;
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);
        SecretKey key = generalKey(secret);
        JwtBuilder builder = Jwts.builder().setIssuedAt(now).setSubject(subject).claim("type", type).signWith(signatureAlgorithm, key);
        if (ttlSeconds >= 0L) {
            long expMillis = nowMillis + ttlSeconds * 1000L;
            Date exp = new Date(expMillis);
            builder.setExpiration(exp);
        }

        return builder.compact();
    }

    public static String createJWT(String secret, String subject, String bodyDouble, long ttlSeconds) throws Exception {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS512;
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);
        SecretKey key = generalKey(secret);
        JwtBuilder builder = Jwts.builder().setIssuedAt(now).setSubject(subject).claim("bodyDouble", bodyDouble).signWith(signatureAlgorithm, key);
        if (ttlSeconds >= 0L) {
            long expMillis = nowMillis + ttlSeconds * 1000L;
            Date exp = new Date(expMillis);
            builder.setExpiration(exp);
        }

        return builder.compact();
    }

    public static String createJWT(String secret, String subject, String bodyDouble, long ttlSeconds, String type) throws Exception {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS512;
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);
        SecretKey key = generalKey(secret);
        JwtBuilder builder = Jwts.builder().setIssuedAt(now).setSubject(subject).claim("bodyDouble", bodyDouble).claim("type", type).signWith(signatureAlgorithm, key);
        if (ttlSeconds >= 0L) {
            long expMillis = nowMillis + ttlSeconds * 1000L;
            Date exp = new Date(expMillis);
            builder.setExpiration(exp);
        }

        return builder.compact();
    }

    public static Claims parseJWT(String secret, String jwt) throws Exception {
        SecretKey key = generalKey(secret);
        Claims claims = (Claims) (new CustomerJwtParser()).setSigningKey(key).parseClaimsJws(jwt).getBody();
        return claims;
    }
}
