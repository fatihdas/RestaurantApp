package com.restaurantapp.restapp.auth;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;

@Service
public class TokenManager {

    private final static int tokenTime = 5 * 60 * 1000;
    Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    public String generateToken(String userName) {
        return Jwts.builder()
                .setSubject(userName)
                .setIssuer("restapp")
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + tokenTime))
                .signWith(key)
                .compact();

    }

    public boolean tokenValidate(String token) {

        if (getUserNameFromToken(token) != null && isExpired(token)) {

            return true;
        }
        return false;

    }

    public String getUserNameFromToken(String token) {

        Claims claims = getClaims(token);
        return claims.getSubject();
    }

    public boolean isExpired(String token) {

        Claims claims = getClaims(token);
        return claims.getExpiration().after(new Date(System.currentTimeMillis()));
    }

    private Claims getClaims(String token) {
        return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
    }

//    public Collection<GrantedAuthority> getAuthorities(String token) {
//        Claims claims = getClaims(token);
//        claims.get
//    }
}
