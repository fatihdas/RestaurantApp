package com.restaurantapp.restapp.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.restaurantapp.restapp.model.dto.AddressDto;
import com.restaurantapp.restapp.model.dto.CityDto;
import com.restaurantapp.restapp.model.dto.CountyDto;
import com.restaurantapp.restapp.model.dto.UserDto;
import com.restaurantapp.restapp.model.request.create.CreateAddressRequest;
import com.restaurantapp.restapp.model.request.update.UpdateAddressRequest;
import com.restaurantapp.restapp.service.impl.AddressServiceImpl;
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
@WebMvcTest(value = AddressController.class)
public class AddressControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AddressServiceImpl addressServiceImpl;

    private String mapToJson(Object object) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(object);
    }

    @Test
    public void getAll() throws Exception {

        List<AddressDto> addressList = new ArrayList<>();
        addressList.add(this.generateAddress());

        Mockito.when(addressServiceImpl.getAllAddresses()).thenReturn(addressList);

        String URI = "/address";
        String inputJson = this.mapToJson(addressList);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get(URI)
                .accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();

        String outputJson = response.getContentAsString(StandardCharsets.UTF_8);

        Assertions.assertThat(inputJson).isEqualTo(outputJson);

    }

    @Test
    public void getAddress() throws Exception {

        AddressDto address = this.generateAddress();

        Mockito.when(addressServiceImpl.getAddress(Mockito.anyLong())).thenReturn(address);

        String URI = "/address/2";
        String inputJson = this.mapToJson(address);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get(URI)
                .accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();

        String outputJson = response.getContentAsString(StandardCharsets.UTF_8);

        Assertions.assertThat(inputJson).isEqualTo(outputJson);
    }

    @Test
    public void addAddress() throws Exception {

        AddressDto address = this.generateAddress();

        String URI = "/address";
        String inputJson = this.mapToJson(address);

        Mockito.when(addressServiceImpl.createAddress(Mockito.any(CreateAddressRequest.class))).thenReturn(address);

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
    public void updateAddress() throws Exception {

        AddressDto address = this.generateAddress();

        String URI = "/address/3";
        String inputJson = this.mapToJson(address);

        Mockito.when(addressServiceImpl.updateAddress(Mockito.any(UpdateAddressRequest.class),Mockito.anyLong())).thenReturn(address);

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
    public void deleteAddress() throws Exception {

        String URI = "/address/3";

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .delete(URI)
                .accept(MediaType.APPLICATION_JSON);

        mockMvc.perform(requestBuilder).andReturn();

        Mockito.verify(addressServiceImpl).deleteAddress(Mockito.anyLong());

    }

    private AddressDto generateAddress() {

        return AddressDto.builder()
                .cityDto(CityDto.builder().name("İstanbul").build())
                .countyDto(CountyDto.builder().name("Pendik").build())
                .content("Güzelyalı mah.")
                .build();

    }
}