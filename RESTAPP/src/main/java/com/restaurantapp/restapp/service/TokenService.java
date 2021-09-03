package com.restaurantapp.restapp.service;

import com.restaurantapp.restapp.model.dto.UserDto;
import io.jsonwebtoken.Claims;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface TokenService {

    UserDto getUserByToken(HttpServletRequest httpServletRequest) throws Exception;

    String getTokenFromHeader(HttpServletRequest httpServletRequest) throws Exception;

    boolean isExpired(String token);

    Claims getClaims(String token);

    boolean tokenValidate(String token);

    boolean isOwnerBranch(long branchId, HttpServletRequest httpServletRequest) throws Exception;

    void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException;
}
