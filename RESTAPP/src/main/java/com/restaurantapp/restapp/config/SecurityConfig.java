package com.restaurantapp.restapp.config;

import com.restaurantapp.restapp.auth.JwtTokenFilter;
import com.restaurantapp.restapp.service.impl.UserServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final JwtTokenFilter jwtTokenFilter;
    private final UserServiceImpl userService;

    @Bean
    public AuthenticationManager getAuthenticationManager() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(getBCryptPasswordEncoder());
    }

    @Bean
    public BCryptPasswordEncoder getBCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    public SecurityConfig(JwtTokenFilter jwtTokenFilter, UserServiceImpl userService) {
        this.jwtTokenFilter = jwtTokenFilter;
        this.userService = userService;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.authorizeRequests().antMatchers("/login", "/h2/**").permitAll();
        http.authorizeRequests().antMatchers(HttpMethod.POST,"branch").hasAnyAuthority("SELLER");
        http.authorizeRequests().antMatchers(HttpMethod.POST,"branch").hasAnyAuthority("BUYER");
        http.authorizeRequests().antMatchers(HttpMethod.GET,"branch/branches/{countyName}").hasAnyAuthority("BUYER");
        http.authorizeRequests().antMatchers(HttpMethod.GET,"branch/status/{value}").hasAnyAuthority("ADMIN");
        http.authorizeRequests().antMatchers(HttpMethod.GET,"branch/menu").hasAnyAuthority("ADMIN");
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class);

    }
}
