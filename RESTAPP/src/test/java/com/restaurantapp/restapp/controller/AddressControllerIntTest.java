package com.restaurantapp.restapp.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.restaurantapp.restapp.model.dto.AddressDto;
import com.restaurantapp.restapp.model.request.create.CreateAddressRequest;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.nio.charset.StandardCharsets;

import static org.junit.Assert.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(AddressController.class)
public class AddressControllerIntTest {

    @Autowired
    private MockMvc mockMvc;

    private String mapToJson(Object object) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(object);
    }


    @Test
    public void whenCreateAddressCalledByCreateAddressRequest_thenReturnSavedAddress() throws Exception {
//        AddressDto addressDto = this.generateAddress();
//        CreateAddressRequest request = CreateAddressRequest.builder()
//                .userId(USER_ID)
//                .content(CONTENT)
//                .countyId(COUNTY_ID)
//                .build();
//        String URI = "/address";
//        String inputJson = this.mapToJson(addressDto);
//
//        Mockito.when(addressService.createAddress(request)).thenReturn(addressDto);
//        RequestBuilder builder = MockMvcRequestBuilders
//                .post(URI)
//                .accept(MediaType.APPLICATION_JSON).content(inputJson)
//                .contentType(MediaType.APPLICATION_JSON_VALUE);
//        MvcResult result = mockMvc.perform(builder).andReturn();
//        MockHttpServletResponse response = result.getResponse();
//
//        String outputJson = response.getContentAsString(StandardCharsets.UTF_8);
//        Assertions.assertEquals(inputJson, outputJson);
    }

    @Test
    public void getUserAddresses() {
    }
}