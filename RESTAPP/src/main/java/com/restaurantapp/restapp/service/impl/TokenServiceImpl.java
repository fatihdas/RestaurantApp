package com.restaurantapp.restapp.service.impl;


import com.restaurantapp.restapp.auth.TokenManager;
import com.restaurantapp.restapp.model.dto.BranchDto;
import com.restaurantapp.restapp.model.dto.UserDto;
import com.restaurantapp.restapp.service.TokenService;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
public class TokenServiceImpl implements TokenService {

    private final UserServiceImpl userService;

    private final BranchServiceImpl branchService;

    private final TokenManager tokenManager;

    private final HttpServletRequest httpServletRequest;

    public TokenServiceImpl(UserServiceImpl userService, BranchServiceImpl branchService, TokenManager tokenManager,
                            HttpServletRequest httpServletRequest) {
        this.userService = userService;
        this.branchService = branchService;
        this.tokenManager = tokenManager;
        this.httpServletRequest = httpServletRequest;
    }

    @Override
    public UserDto getUserByToken(HttpServletRequest httpServletRequest) throws Exception {

        String token = tokenManager.getTokenFromHeader(httpServletRequest);
        return userService.getUserByName(tokenManager.getUserNameFromToken(token));
    }

    @Override
    public boolean isOwnerBranch(long branchId, HttpServletRequest httpServletRequest) throws Exception {
        String token = tokenManager.getTokenFromHeader(httpServletRequest);
        UserDto userDto = userService.getUserByName(tokenManager.getUserNameFromToken(token));
        BranchDto branchDto = branchService.getBranchDto(branchId);

        if (userDto.getId() == userId) {
            return true;
        } else{
            throw new Exception("invalid user request. User is not owner!");
        }
    }
}
