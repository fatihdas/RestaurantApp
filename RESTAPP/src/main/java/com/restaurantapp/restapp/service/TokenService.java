package com.restaurantapp.restapp.service;

import com.restaurantapp.restapp.model.dto.UserDto;

import javax.servlet.http.HttpServletRequest;

public interface TokenService {

    UserDto getUserByToken(HttpServletRequest httpServletRequest) throws Exception;

    boolean isOwnerBranch(long branchId, HttpServletRequest httpServletRequest) throws Exception;
}
