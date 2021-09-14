package com.restaurantapp.restapp.security;

import com.restaurantapp.restapp.model.entity.enumerated.UserRoles;
import com.restaurantapp.restapp.service.impl.UserServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final UserServiceImpl userService;
    private final JwtFilter JwtFilter;

    public SecurityConfig(UserServiceImpl userService,
                          com.restaurantapp.restapp.security.JwtFilter jwtFilter) {
        this.userService = userService;
        JwtFilter = jwtFilter;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests().antMatchers("/login/**", "/h2/**").permitAll()
                .antMatchers(HttpMethod.POST, "/user").permitAll()
                .antMatchers(HttpMethod.PUT, "/user/{id}/{role}").hasAnyAuthority(UserRoles.BUYER.toString())
                .antMatchers(HttpMethod.POST, "/restaurant/**").hasAnyAuthority(UserRoles.SELLER.toString())
                .antMatchers(HttpMethod.POST, "/branch/**").hasAnyAuthority(UserRoles.SELLER.toString())
                .antMatchers(HttpMethod.GET, "/branch/status/{value}/**").hasAnyAuthority(UserRoles.SELLER.toString())
                .antMatchers(HttpMethod.PUT, "/branch/{branchId}/{status}/**").hasAnyAuthority(UserRoles.ADMIN.toString())
                .antMatchers(HttpMethod.POST, "/menu/**").hasAnyAuthority(UserRoles.SELLER.toString())
                .antMatchers(HttpMethod.POST, "/meal/**").hasAnyAuthority(UserRoles.SELLER.toString())
                .antMatchers(HttpMethod.GET, "/address/{userId}/**").hasAnyAuthority(UserRoles.BUYER.toString())
                .antMatchers(HttpMethod.POST, "/comment/**").hasAnyAuthority(UserRoles.BUYER.toString())
                .anyRequest().authenticated()
                .and().sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.addFilterBefore(JwtFilter, UsernamePasswordAuthenticationFilter.class);
    }


    @Bean(name = BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public PasswordEncoder encoder(){
        return NoOpPasswordEncoder.getInstance();
    }
}
