package com.restaurantapp.restapp.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.restaurantapp.restapp.model.dto.UserDto;
import com.restaurantapp.restapp.model.entity.enumerated.Roles;
import com.restaurantapp.restapp.model.request.create.CreateUserRequest;
import com.restaurantapp.restapp.model.request.update.UpdateUserRequest;
import com.restaurantapp.restapp.service.impl.UserServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;


@RunWith(SpringJUnit4ClassRunner.class)
@WebMvcTest(value = UserController.class)
public class UserDtoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserServiceImpl userServiceImpl;

    private String mapToJson(Object object) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(object);
    }

    @org.junit.Test
    public void addUser() throws Exception {

        UserDto user = this.generateUser();

        String URI = "/users";
        String inputInJson = this.mapToJson(user);

        Mockito.when(userServiceImpl.createUser(Mockito.any(CreateUserRequest.class))).thenReturn(user);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post(URI)
                .accept(MediaType.APPLICATION_JSON).content(inputInJson)
                .contentType(MediaType.APPLICATION_JSON_VALUE);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();

        String outputInJson = response.getContentAsString();

        Assertions.assertThat(outputInJson).isEqualTo(inputInJson);
        Assert.assertEquals(HttpStatus.CREATED.value(), response.getStatus());

    }

    @Test
    public void getAllUsers() throws Exception {

        List<UserDto> userList = new ArrayList<>();
        userList.add(this.generateUser());

        String URI = "/users";
        String inputInJson = this.mapToJson(userList);

        Mockito.when(userServiceImpl.getAllUsers()).thenReturn(userList);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get(URI)
                .accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();

        String outputInJson = response.getContentAsString();

        Assertions.assertThat(outputInJson).isEqualTo(inputInJson);


    }

    @org.junit.Test
    public void getUserById() throws Exception {

        UserDto user = this.generateUser();

        String URI = "/users/1";
        String inputJson = this.mapToJson(user);

        Mockito.when(userServiceImpl.getUser(Mockito.anyLong())).thenReturn(user);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get(URI)
                .accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        String outputJsonValue = result.getResponse().getContentAsString();

        Assertions.assertThat(inputJson).isEqualTo(outputJsonValue);


    }

    @org.junit.Test
    public void getUserByName() throws Exception {

        UserDto user = this.generateUser();

        String URI = "/users/name/test";
        String inputJson = this.mapToJson(user);

        Mockito.when(userServiceImpl.getUserByName(Mockito.anyString())).thenReturn(user);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get(URI)
                .accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();

        String outputJsonValue = response.getContentAsString();

        Assertions.assertThat(inputJson).isEqualTo(outputJsonValue);
    }

    @org.junit.Test
    public void updateUser() throws Exception {

        UserDto user = this.generateUser();

        String URI = "/users/14";
        String inputJson = this.mapToJson(user);

        Mockito.when(userServiceImpl.updateUser(Mockito.any(UpdateUserRequest.class), Mockito.anyLong())).thenReturn(user);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .put(URI)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON).content(this.mapToJson(user));

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();

        String outputJsonValue = response.getContentAsString();

        Assertions.assertThat(inputJson).isEqualTo(outputJsonValue);
    }

    @org.junit.Test
    public void deleteUser() throws Exception {

        userServiceImpl.deleteUser(Mockito.anyLong());

        Mockito.verify(userServiceImpl).deleteUser(Mockito.anyLong());

    }

    private UserDto generateUser() {
        return UserDto.builder()
                .name("testname")
                .email("test@mail.com")
                .roles(Roles.ADMIN)
                .build();
    }
}