package com.restaurantapp.restapp.service;

import com.restaurantapp.restapp.model.dto.UserDto;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface TokenService {

    UserDto getUserByToken(HttpServletRequest httpServletRequest) throws Exception;

    boolean isOwnerBranch(long branchId, HttpServletRequest httpServletRequest) throws Exception;

    void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException;
}
