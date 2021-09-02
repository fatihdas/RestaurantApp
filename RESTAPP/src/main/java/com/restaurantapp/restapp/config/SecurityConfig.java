package com.restaurantapp.restapp.config;

import com.restaurantapp.restapp.auth.CustomAuthenticationFilter;
import com.restaurantapp.restapp.auth.CustomAuthorizationFilter;
import com.restaurantapp.restapp.service.impl.UserServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userDetailsService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;


    public SecurityConfig( UserServiceImpl userService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userDetailsService = userService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        CustomAuthenticationFilter customAuthenticationFilter = new CustomAuthenticationFilter(authenticationManagerBean());
        customAuthenticationFilter.setFilterProcessesUrl("/login");

        http.authorizeRequests().antMatchers("/login/**","/token/refresh/**", "/h2/**","/home/**").permitAll();
        http.authorizeRequests().antMatchers(HttpMethod.POST, "/branch").hasAnyAuthority("SELLER");
        http.authorizeRequests().antMatchers(HttpMethod.GET, "/branch").hasAnyAuthority("BUYER");
        http.authorizeRequests().antMatchers(HttpMethod.GET, "/branch/branches/{countyName}").hasAnyAuthority("BUYER");
        http.authorizeRequests().antMatchers(HttpMethod.GET, "/branch/status/{value}").hasAnyAuthority("ADMIN");
        http.authorizeRequests().antMatchers(HttpMethod.GET, "/branch/menu").hasAnyAuthority("ADMIN");

        http.authorizeRequests().anyRequest().authenticated(); // Obligation of authentication of all endpoints

        http.addFilter(customAuthenticationFilter);
        http.addFilterBefore(new CustomAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);

        http.csrf().disable();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);


    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
