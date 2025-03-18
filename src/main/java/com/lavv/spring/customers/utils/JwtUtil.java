package com.lavv.spring.customers.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.lavv.spring.customers.entities.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtil {

    private static String SECRET_KEY;
    private static Algorithm algorithm;
    private static final String issuer = "LeaJava";

    @Value("${customers.app.env}")
    public void setSecretKey(String secretKey) {
        SECRET_KEY = secretKey;
    }

    public static String generateToken(User user) {
        algorithm = Algorithm.HMAC256(JwtUtil.SECRET_KEY);
        return JWT.create()
                .withIssuer(issuer)
                .withClaim("userId", user.getId())
                .withIssuedAt(new Date())
                .withExpiresAt(getExpiresDate())
                .sign(algorithm);
    }

    private static Date getExpiresDate() {
        return new Date(System.currentTimeMillis() + (1000L * 60 * 60 * 24 * 14));
    }

    public static String getUserIdByToken(String token) {
        algorithm = Algorithm.HMAC256(JwtUtil.SECRET_KEY);
        JWTVerifier verifier = JWT.require(algorithm).withIssuer(issuer).build();
        DecodedJWT decodedJWT = verifier.verify(token);
        return decodedJWT.getClaim("userId").toString();
    }
}
