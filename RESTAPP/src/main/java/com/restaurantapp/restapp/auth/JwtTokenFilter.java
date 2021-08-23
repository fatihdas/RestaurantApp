package com.restaurantapp.restapp.auth;

import com.restaurantapp.restapp.model.entity.enumerated.Roles;
import com.restaurantapp.restapp.service.impl.UserServiceImpl;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Component
public class JwtTokenFilter extends OncePerRequestFilter {

    private final TokenManager tokenManager;
    private final UserServiceImpl userService;

    public JwtTokenFilter(TokenManager tokenManager, UserServiceImpl userService) {
        this.tokenManager = tokenManager;
        this.userService = userService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest,
                                    HttpServletResponse httpServletResponse,
                                    FilterChain filterChain) throws ServletException, IOException {

        final String authHeader = httpServletRequest.getHeader("Authorization");
        String username = null;
        String token = null;

        if (authHeader != null && authHeader.startsWith("Bearer")) {
            token = authHeader.substring(7);

            try {
                username = tokenManager.getUserNameFromToken(token);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

        if (username != null && token != null
                && SecurityContextHolder.getContext().getAuthentication() == null) {

            if (tokenManager.tokenValidate(token)) {

                List<Roles> rolesList = userService.getUserByName(tokenManager.getUserNameFromToken(token)).getRolesList();
                Collection<GrantedAuthority> authorities = new ArrayList<>();
                rolesList.forEach(iter -> {
                    authorities.add(new SimpleGrantedAuthority(iter.toString()));
                });
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                        new UsernamePasswordAuthenticationToken(username, null, authorities);
                usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource()
                        .buildDetails(httpServletRequest));
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }

        }

        filterChain.doFilter(httpServletRequest, httpServletResponse);

    }
}
