//package com.restaurantapp.restapp.auth;
//
//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.ExpiredJwtException;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.SignatureAlgorithm;
//import io.jsonwebtoken.security.Keys;
//import org.springframework.stereotype.Service;
//
//import javax.servlet.http.HttpServletRequest;
//import java.security.Key;
//import java.util.Date;
//
//@Service
//public class TokenManager {
//
//    private final HttpServletRequest httpServletRequest;
//
//    private final static int tokenTime = 5 * 60 * 1000;
//    Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
//
//    public TokenManager(HttpServletRequest httpServletRequest) {
//        this.httpServletRequest = httpServletRequest;
//    }
//
//    public String generateToken(String userName) {
//        return Jwts.builder()
//                .setSubject(userName)
//                .setIssuer("restapp")
//                .setIssuedAt(new Date(System.currentTimeMillis()))
//                .setExpiration(new Date(System.currentTimeMillis() + tokenTime))
//                .signWith(key)
//                .compact();
//
//    }
//
//    public boolean tokenValidate(String token) {    //tokenValidate(String token, User user)
//
//        if (getUserNameFromToken(token) != null && isExpired(token)) {
//
//            return true;
//        }
//        return false;
//
//    }
//
//    public String getTokenFromHeader(HttpServletRequest httpServletRequest) throws Exception {
//
//        String header = httpServletRequest.getHeader("Authorization");
//        String token = null;
//
//        if (header != null && header.startsWith("Bearer")) {
//            token = header.substring(7);
//        }
//
//        if(isExpired(token)){
//            throw new Exception("expired jwt token!");
//        }
//        return token;
//    }
//
//    public String getUserNameFromToken(String token) {
//
//        Claims claims = getClaims(token);
//        return claims.getSubject();
//    }
//
//    public boolean isExpired(String token) {
//
//        Claims claims = getClaims(token);
//        return claims.getExpiration().after(new Date(System.currentTimeMillis()));
//    }
//
//    private Claims getClaims(String token) {
//        return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
//    }
//
////    public Collection<GrantedAuthority> getAuthorities(String token) {
////        Claims claims = getClaims(token);
////        claims.get
////    }
//}
