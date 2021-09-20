package com.restaurantapp.restapp.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.restaurantapp.restapp.model.request.create.CreateMealRequest;
import com.restaurantapp.restapp.security.JwtFilter;
import com.restaurantapp.restapp.service.impl.MealServiceImpl;
import com.restaurantapp.restapp.service.impl.UserServiceImpl;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@RunWith(SpringRunner.class)
@WebMvcTest(MealController.class)
@AutoConfigureMockMvc(addFilters = false)
public class MealControllerIntTest {

    private static final long ID = 11L;
    private static final long MENU_ID = 1L;
    private static final long PRICE = 22L;
    private static final String NAME = "kebap";
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MealServiceImpl mealService;

    @MockBean
    private JwtFilter jwtFilter;

    @MockBean
    private UserServiceImpl userService;

    private String maptoJson(Object o) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(o);
    }

    @Test
    public void whenCreateMealCalled_thenReturnSavedMeal() throws Exception {
        String URI = "/meal";
        CreateMealRequest request = CreateMealRequest.builder()
                .id(ID)
                .menuId(MENU_ID)
                .price(PRICE)
                .name(NAME)
                .build();
        String inputJson = this.maptoJson(request);
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post(URI)
                .accept(MediaType.APPLICATION_JSON).content(inputJson)
                .contentType(MediaType.APPLICATION_JSON_VALUE);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();
        Assertions.assertEquals(HttpStatus.OK.value(), response.getStatus());
    }

    @Test
    public void whenGetMealByBranchIdCalled_thenReturnBranchMealList() throws Exception {
        String URI = "/meal/1";
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get(URI)
                .accept(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();
        Assertions.assertEquals(HttpStatus.OK.value(), response.getStatus());
    }
}