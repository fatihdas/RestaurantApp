package com.restaurantapp.restapp.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.restaurantapp.restapp.model.dto.RestaurantDto;
import com.restaurantapp.restapp.model.dto.UserDto;
import com.restaurantapp.restapp.model.request.create.CreateRestaurantRequest;
import com.restaurantapp.restapp.model.request.update.UpdateRestaurantRequest;
import com.restaurantapp.restapp.service.impl.RestaurantServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@WebMvcTest(value = RestaurantController.class)
public class RestaurantControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    RestaurantServiceImpl restaurantServiceImpl;

    String mapToJson(Object o) throws JsonProcessingException {

        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(o);
    }

    @Test
    public void add() throws Exception {

        RestaurantDto restaurant = this.generateRestaurant();

        String URI = "/restaurant";
        String inputJson = this.mapToJson(restaurant);

        Mockito.when(restaurantServiceImpl.createRestaurant(Mockito.any(CreateRestaurantRequest.class))).thenReturn(restaurant);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post(URI)
                .accept(MediaType.APPLICATION_JSON).content(inputJson)
                .contentType(MediaType.APPLICATION_JSON_VALUE);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();

        String outputJson = response.getContentAsString(StandardCharsets.UTF_8);

        Assertions.assertThat(inputJson).isEqualTo(outputJson);
    }

    @Test
    public void getAll() throws Exception {

        List<RestaurantDto> restaurantList = new ArrayList<>();
        restaurantList.add(this.generateRestaurant());

        String URI = "/restaurant";
        String inputJson = this.mapToJson(restaurantList);

        Mockito.when(restaurantServiceImpl.getAllRestaurants()).thenReturn(restaurantList);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get(URI)
                .accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();

        String outputJson = response.getContentAsString(StandardCharsets.UTF_8);

        Assertions.assertThat(inputJson).isEqualTo(outputJson);
    }

    @Test
    public void getById() throws Exception {

        RestaurantDto restaurant = this.generateRestaurant();

        String URI = "/restaurant/25";
        String inputJson = this.mapToJson(restaurant);

        Mockito.when(restaurantServiceImpl.getRestaurant(Mockito.anyLong())).thenReturn(restaurant);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get(URI)
                .accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();

        String outputJson = response.getContentAsString(StandardCharsets.UTF_8);

        Assertions.assertThat(inputJson).isEqualTo(outputJson);
    }

    @Test
    public void update() throws Exception {

        RestaurantDto restaurant = this.generateRestaurant();

        String URI = "/restaurant/13";
        String inputJson = this.mapToJson(restaurant);

        Mockito.when(restaurantServiceImpl.updateRestaurant(Mockito.any(UpdateRestaurantRequest.class)
                ,Mockito.anyLong())).thenReturn(restaurant);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .put(URI)
                .accept(MediaType.APPLICATION_JSON).content(inputJson)
                .contentType(MediaType.APPLICATION_JSON_VALUE);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();

        String outputJson = response.getContentAsString(StandardCharsets.UTF_8);

        Assertions.assertThat(inputJson).isEqualTo(outputJson);
    }

    @Test
    public void delete() throws Exception {

        restaurantServiceImpl.deleteRestaurant(Mockito.anyLong());

        Mockito.verify(restaurantServiceImpl).deleteRestaurant(Mockito.anyLong());

    }

    private RestaurantDto generateRestaurant() {
        return RestaurantDto.builder()
                .name("Hatay Medeniyetler Sofrası")
                .userDto(UserDto.builder().name("CZN Burak").build())
                .build();
    }
}