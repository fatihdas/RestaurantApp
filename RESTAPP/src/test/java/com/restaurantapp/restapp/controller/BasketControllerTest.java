package com.restaurantapp.restapp.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.restaurantapp.restapp.model.Basket;
import com.restaurantapp.restapp.model.User;
import com.restaurantapp.restapp.repository.BasketRepository;
import com.restaurantapp.restapp.service.BasketService;
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
@WebMvcTest(value = BasketController.class)
public class BasketControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    private BasketService basketService;

    @MockBean
    private BasketRepository basketRepository;

    private String mapToJson(Object object) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(object);
    }

    @Test
    public void addBasket() throws Exception {

        Basket basket = this.generateBasket();

        String URI = "/basket";
        String inputJson = this.mapToJson(basket);

        Mockito.when(basketService.save(Mockito.any(Basket.class))).thenReturn(basket);

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
    public void getAllBasket() throws Exception {

        List<Basket> basketList = new ArrayList<>();
        basketList.add(this.generateBasket());

        String URI = "/basket";
        String inputJson = this.mapToJson(basketList);

        Mockito.when(basketService.getAll()).thenReturn(basketList);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get(URI)
                .accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();

        String outputJson = response.getContentAsString(StandardCharsets.UTF_8);

        Assertions.assertThat(inputJson).isEqualTo(outputJson);

    }

    @Test
    public void getBasketById() throws Exception {

        Basket basket = this.generateBasket();

        String URI = "/basket/2";
        String inputJson = this.mapToJson(basket);

        Mockito.when(basketService.getById(Mockito.anyLong())).thenReturn(basket);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get(URI)
                .accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();

        String outputJson = response.getContentAsString(StandardCharsets.UTF_8);

        Assertions.assertThat(inputJson).isEqualTo(inputJson);
    }

    @Test
    public void updateBasket() throws Exception {

        Basket basket = this.generateBasket();

        String URI = "/basket";
        String inputJson = this.mapToJson(basket);

        Mockito.when(basketService.update(Mockito.any(Basket.class))).thenReturn(basket);

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
    public void deleteBasket() throws Exception {

        Basket basket = this.generateBasket();

        String URI = "/basket/4";

        Mockito.when(basketService.delete(Mockito.anyLong())).thenReturn("success");

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .delete(URI)
                .accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();

        String outputJson = response.getContentAsString(StandardCharsets.UTF_8);

        Assertions.assertThat("success").isEqualTo(outputJson);
    }

    private Basket generateBasket() {

        return Basket.builder()
                .user(User.builder().name("testname").build())
                .count(4)
                .totalPrice(15.25f)
                .build();

    }
}