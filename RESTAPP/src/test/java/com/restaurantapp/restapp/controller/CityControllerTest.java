package com.restaurantapp.restapp.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.restaurantapp.restapp.model.dto.CityDto;
import com.restaurantapp.restapp.model.request.create.CreateCityRequest;
import com.restaurantapp.restapp.model.request.update.UpdateCityRequest;
import com.restaurantapp.restapp.service.impl.CityServiceImpl;
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
@WebMvcTest(value = CityController.class)
public class CityControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    CityServiceImpl cityServiceImpl;

    String mapToJson(Object o) throws JsonProcessingException {

        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(o);
    }

    @Test
    public void add() throws Exception {

        CityDto city = this.generateCity();

        String URI = "/city";
        String inputJson = this.mapToJson(city);

        Mockito.when(cityServiceImpl.createCity(Mockito.any(CreateCityRequest.class))).thenReturn(city);

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

        List<CityDto> cityList = new ArrayList<>();
        cityList.add(this.generateCity());

        String URI = "/city";
        String inputJson = this.mapToJson(cityList);

        Mockito.when(cityServiceImpl.getAllCities()).thenReturn(cityList);

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

        CityDto city = this.generateCity();

        String URI = "/city/13";
        String inputJson = this.mapToJson(city);

        Mockito.when(cityServiceImpl.getCity(Mockito.anyLong())).thenReturn(city);

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

        CityDto city = this.generateCity();

        String URI = "/city/6";
        String inputJson = this.mapToJson(city);

        Mockito.when(cityServiceImpl.updateCity(Mockito.any(UpdateCityRequest.class),Mockito.anyLong())).thenReturn(city);

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

        cityServiceImpl.deleteCity(Mockito.anyLong());

        Mockito.verify(cityServiceImpl).deleteCity(Mockito.anyLong());

    }

    private CityDto generateCity() {

        return CityDto.builder()
                .name("İstanbul")
                .build();
    }
}