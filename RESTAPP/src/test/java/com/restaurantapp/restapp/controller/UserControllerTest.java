package com.restaurantapp.restapp.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.restaurantapp.restapp.model.User;
import com.restaurantapp.restapp.repository.UserRepository;
import com.restaurantapp.restapp.service.UserService;
import com.restaurantapp.restapp.testmodel.TestUsers;
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

import java.util.List;


@RunWith(SpringJUnit4ClassRunner.class)
@WebMvcTest(value = UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    private TestUsers testUsers = new TestUsers();

    @MockBean
    private UserRepository userRepository;

    public UserControllerTest(){

    }

    private String mapToJson(Object object) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(object);
    }

    @org.junit.Test
    public void addUser() throws Exception {

        User user1 = testUsers.userList().get(0);

        String inputInJson = this.mapToJson(user1);

        String URI = "/users";

        Mockito.when(userService.save(Mockito.any(User.class))).thenReturn(user1);

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
    public void getAllUsers() throws Exception{

        List<User> userList = testUsers.userList();

        String inputInJson = this.mapToJson(userList);

        String URI = "/users";

        Mockito.when(userService.getAll()).thenReturn(userList);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get(URI)
                .accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();

        String outputInJson = response.getContentAsString();

        Assertions.assertThat(outputInJson).isEqualTo(inputInJson);



    }

    @org.junit.Test
    public void getUserById() throws Exception{

        User user = testUsers.userList().get(0);

        Mockito.when(userService.getById(Mockito.anyLong())).thenReturn(user);

        String URI = "/users/1";
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get(URI)
                .accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        String expextedJsonValue = this.mapToJson(user);
        String outputJsonValue = result.getResponse().getContentAsString();
        Assertions.assertThat(expextedJsonValue).isEqualTo(outputJsonValue);


    }

    @org.junit.Test
    public void getUserByName() throws Exception{

        User user = testUsers.userList().get(0);

        Mockito.when(userService.getUserByName(Mockito.anyString())).thenReturn(user);

        String URI = "/users/name/test";
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get(URI)
                .accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        String expextedJsonValue = this.mapToJson(user);
        String outputJsonValue = result.getResponse().getContentAsString();

        Assertions.assertThat(expextedJsonValue).isEqualTo(outputJsonValue);
    }

    @org.junit.Test
    public void updateUser() throws Exception{

        User user = testUsers.userList().get(0);

        Mockito.when(userService.update(Mockito.any(User.class))).thenReturn(user);

        String URI = "/users";
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .put(URI)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON).content(this.mapToJson(user));

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();

        String expectedJsonValue = this.mapToJson(user);
        String outputJsonValue = response.getContentAsString();

        Assertions.assertThat(expectedJsonValue).isEqualTo(outputJsonValue);
    }

    @org.junit.Test
    public void deleteUser() throws  Exception{

        User user = testUsers.userList().get(0);

        Mockito.when(userService.delete(Mockito.anyLong())).thenReturn(user);

        String URI = "/users/1";

        RequestBuilder request = MockMvcRequestBuilders
                .delete(URI)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(request).andReturn();
        MockHttpServletResponse response = result.getResponse();

        String expectedJsonValue = this.mapToJson(user);
        String outputJsonValue = response.getContentAsString();

        Assertions.assertThat(expectedJsonValue).isEqualTo(outputJsonValue);
    }
}