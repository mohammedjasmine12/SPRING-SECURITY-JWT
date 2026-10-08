package com.example.spring_security_jwt.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;
import java.util.Date;
import javax.crypto.SecretKey;

@Service
public class JwtService {
    private final String SECRET = "mySuperSecretKeyForJwtTokenGenerationThatIsVeryLong123456";
    private SecretKey getKey(){ return Keys.hmacShaKeyFor(SECRET.getBytes()); }
    
    public String generateToken(String username){
        return Jwts.builder().subject(username)
                .issuedAt(new Date()).expiration(new Date(System.currentTimeMillis()+1000*60*60*24))
                .signWith(getKey()).compact();
    }
    public String extractUsername(String token){
        return Jwts.parser().verifyWith(getKey()).build().parseSignedClaims(token).getPayload().getSubject();
    }
    public boolean isTokenValid(String token, String username){
        return extractUsername(token).equals(username);
    }
}