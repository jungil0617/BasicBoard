package jungil0617.BasicBoard.global.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtTokenProvider {

    private final Key key;
    private final long ACCESS_TOKEN_VALIDITY_SECONDS = 1000 * 60 * 30;
    private final long REFRESH_TOKEN_VALIDITY_SECONDS = 1000 * 60 * 60 * 7 * 24;

    public JwtTokenProvider(@Value("${jwt.secret}") String secretKey) {
        this.key = Keys.hmacShaKeyFor(secretKey.getBytes());
    }

    public String generateAccessToken(String username) {
        return createToken(username, ACCESS_TOKEN_VALIDITY_SECONDS);
    }

    public String generateRefreshToken(String username) {
        return createToken(username, REFRESH_TOKEN_VALIDITY_SECONDS);
    }

    private String createToken(String username, long expiration) {
        Date now = new Date();
        Date expirationDate = new Date(now.getTime() + expiration);

        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(now)
                .setExpiration(expirationDate)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

}