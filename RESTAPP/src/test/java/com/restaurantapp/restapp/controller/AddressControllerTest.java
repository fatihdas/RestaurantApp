package com.restaurantapp.restapp.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.restaurantapp.restapp.model.Address;
import com.restaurantapp.restapp.model.City;
import com.restaurantapp.restapp.model.County;
import com.restaurantapp.restapp.model.User;
import com.restaurantapp.restapp.repository.AddressRepository;
import com.restaurantapp.restapp.service.AddressService;
import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

public class AddressControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private AddressService addressService;
    private AddressRepository addressRepository;

    @Before
    public void setUp() throws Exception {
        addressRepository = Mockito.mock(AddressRepository.class);
        addressService = Mockito.mock(AddressService.class);
    }

    private String mapToJson(Object object) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(object);
    }

    @Test
    public void getAll() throws Exception {

        List<Address> addressList = new ArrayList<>();
        addressList.add(generateAddress());

        Mockito.when(addressService.getAll()).thenReturn(addressList);

        String URI = "/address";
        String inputJson = this.mapToJson(addressList);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get(URI)
                .accept(MediaType.APPLICATION_JSON).content(inputJson);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();

        String outputJson = response.getContentAsString();

        Assertions.assertThat(inputJson).isEqualTo(outputJson);
        Assert.assertEquals(HttpStatus.OK, response.getStatus());

    }

    @Test
    public void getAddress() {
    }

    @Test
    public void addAddress() {
    }

    @Test
    public void updateAddress() {
    }

    @Test
    public void deleteAddress() {
    }

    private Address generateAddress() {

        return Address.builder()
                .city(City.builder().name("İstanbul").build())
                .county(County.builder().name("Pendik").build())
                .content("Güzelyalı mah.")
                .user(User.builder().name("test").build())
                .build();

    }
}