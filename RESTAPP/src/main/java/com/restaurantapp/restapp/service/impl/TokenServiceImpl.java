package com.restaurantapp.restapp.service.impl;


import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.restaurantapp.restapp.model.dto.UserDto;
import com.restaurantapp.restapp.model.entity.enumerated.UserRoles;
import com.restaurantapp.restapp.service.TokenService;
import io.jsonwebtoken.Claims;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Service
public class TokenServiceImpl implements TokenService {

    private final UserServiceImpl userService;

    private final BranchServiceImpl branchService;

    private final HttpServletRequest httpServletRequest;


    public TokenServiceImpl(UserServiceImpl userService, BranchServiceImpl branchService,
                            HttpServletRequest httpServletRequest) {
        this.userService = userService;
        this.branchService = branchService;
        this.httpServletRequest = httpServletRequest;
    }

    @Override
    public UserDto getUserByToken(HttpServletRequest httpServletRequest) throws Exception {
        String token = httpServletRequest.getHeader("Authorization");

        return null;
    }

    @Override
    public String getTokenFromHeader(HttpServletRequest httpServletRequest) throws Exception {
        String header = httpServletRequest.getHeader("Authorization");
        String token = null;

        if (header != null && header.startsWith("Bearer")) {
            token = header.substring(7);
        }

        if (isExpired(token)) {
            throw new Exception("expired jwt token!");
        }
        return token;
    }

    @Override
    public boolean isExpired(String token) {
        return false;
    }

    @Override
    public Claims getClaims(String token) {
        return null;
    }

    @Override
    public boolean tokenValidate(String token) {
        if (getTokenFromHeader(token) != null && isExpired(token)) {

            return true;
        }
        return false;
    }

    @Override
    public boolean isOwnerBranch(long branchId, HttpServletRequest httpServletRequest) throws Exception {
        return false;
    }

    @Override
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String authorizationHeader = request.getHeader(AUTHORIZATION);
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            try {
                String refresh_token = authorizationHeader.substring("Bearer ".length());
                Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
                JWTVerifier verifier = JWT.require(algorithm).build();
                DecodedJWT decodedJWT = verifier.verify(refresh_token);
                String username = decodedJWT.getSubject();
                UserDto user = userService.getUserByName(username);
                String access_token = JWT.create()
                        .withSubject(user.getName())
                        .withExpiresAt(new Date(System.currentTimeMillis() + 10 * 60 * 1000))
                        .withIssuer(request.getRequestURL().toString())
                        .withClaim("userRoles", user.getUserRolesList().stream().map(UserRoles::name)
                                .collect(Collectors.toList()))
                        .sign(algorithm);

                Map<String, String> tokens = new HashMap<>();
                tokens.put("access_token", access_token);
                tokens.put("refresh_token", refresh_token);
                response.setContentType(APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(), tokens);
            } catch (Exception exception) {
                response.setHeader("error", exception.getMessage());
                response.setStatus(FORBIDDEN.value());
                Map<String, String> error = new HashMap<>();
                error.put("error_message", exception.getMessage());
                response.setContentType(APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(), error);
            }

        } else {
            throw new RuntimeException("Refresh token is missing");
        }
    }
}
