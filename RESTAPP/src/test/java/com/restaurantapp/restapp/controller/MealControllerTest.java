package com.restaurantapp.restapp.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.restaurantapp.restapp.model.dto.MealDto;
import com.restaurantapp.restapp.model.request.create.CreateMealRequest;
import com.restaurantapp.restapp.model.request.update.UpdateMealRequest;
import com.restaurantapp.restapp.service.impl.MealServiceImpl;
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
@WebMvcTest(value = MealController.class)
public class MealControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    MealServiceImpl mealServiceImpl;

    String mapToJson(Object o) throws JsonProcessingException {

        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(o);
    }

    @Test
    public void add() throws Exception {

        MealDto meal = this.generateMeal();

        String URI = "/meal";
        String inputJson = this.mapToJson(meal);

        Mockito.when(mealServiceImpl.createMeal(Mockito.any(CreateMealRequest.class))).thenReturn(meal);

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

        List<MealDto> mealList = new ArrayList<>();
        mealList.add(this.generateMeal());

        String URI = "/meal";
        String inputJson = this.mapToJson(mealList);

        Mockito.when(mealServiceImpl.getAllMeals()).thenReturn(mealList);

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

        MealDto meal = this.generateMeal();

        String URI = "/meal/11";
        String inputJson = this.mapToJson(meal);

        Mockito.when(mealServiceImpl.getMeal(Mockito.anyLong())).thenReturn(meal);

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

        MealDto meal = this.generateMeal();

        String URI = "/meal/11";
        String inputJson = this.mapToJson(meal);

        Mockito.when(mealServiceImpl.updateMeal(Mockito.any(UpdateMealRequest.class), Mockito.anyLong())).thenReturn(meal);

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

        mealServiceImpl.deleteMeal(Mockito.anyLong());

        Mockito.verify(mealServiceImpl).deleteMeal(Mockito.anyLong());

    }

    private MealDto generateMeal() {
        return MealDto.builder()
                .name("Kumpir + Kola")
                .price(40)
                .build();
    }
}