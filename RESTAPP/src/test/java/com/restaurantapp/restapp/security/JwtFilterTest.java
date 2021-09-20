//package com.restaurantapp.restapp.security;
//
//import com.restaurantapp.restapp.service.impl.UserServiceImpl;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.mock.web.MockFilterChain;
//import org.springframework.mock.web.MockHttpServletRequest;
//import org.springframework.mock.web.MockHttpServletResponse;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.util.ArrayList;
//
//@RunWith(SpringRunner.class)
//public class JwtFilterTest {
//    private static final String AUTHORIZATION = "Authorization";
//    private static final String BEARER = "Bearer";
//
//    @Autowired
//    private UserServiceImpl userService;
//
//    @Autowired
//    private JwtUtil jwtUtil;
//
//    @MockBean
//    private JwtFilter jwtFilter;
//
//    private MockHttpServletRequest request;
//
//    private MockHttpServletResponse response;
//
//    private MockFilterChain filterChain;
//
//
//    @Before
//    public void init(){
//
//        request = new MockHttpServletRequest("POST","/login");
//        response = new MockHttpServletResponse();
//        UserDetails userDetails = new User("testname","testpass",new ArrayList<>());
//        request.addHeader(AUTHORIZATION,jwtUtil.generateToken(userDetails));
//
//
//
//    }
//    @Test
//    public void doFilterInternal() {
//    }
//}