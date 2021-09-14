package com.restaurantapp.restapp.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.restaurantapp.restapp.model.dto.AddressDto;
import com.restaurantapp.restapp.model.dto.BranchDto;
import com.restaurantapp.restapp.model.request.create.CreateAddressRequest;
import com.restaurantapp.restapp.service.impl.AddressServiceImpl;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class AddressControllerTest {

    private static final String CONTENT = "İstiklal Caddesi";
    private static final String COUNTY_NAME = "Tuzla";
    private static final long USER_ID = 1L;
    private static final long COUNTY_ID = 896L;

    @Mock
    private AddressServiceImpl addressService;

    @InjectMocks
    private AddressController addressController;


    @Test
    public void whenCreateAddressCalledByCreateAddressRequest_thenReturnSavedAddress() throws Exception {
        AddressDto addressDto = this.generateAddress();
        CreateAddressRequest request = CreateAddressRequest.builder()
                .userId(USER_ID)
                .content(CONTENT)
                .countyId(COUNTY_ID)
                .build();
        Mockito.when(addressService.createAddress(request)).thenReturn(addressDto);
        ResponseEntity<AddressDto> responseEntity = addressController.createAddress(request);
        Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        Assertions.assertEquals(CONTENT, responseEntity.getBody().getContent());
    }

    @Test
    public void whenGetUserAddressesCalledByUserId_thenReturnUserAddressDtoList() {
        List<AddressDto> addressDtoList = Collections.singletonList(this.generateAddress());
        Mockito.when(addressService.getUserAdresses(USER_ID)).thenReturn(addressDtoList);
        ResponseEntity<List<AddressDto>> responseEntity = addressController.getUserAddresses(USER_ID);
        Assertions.assertEquals(HttpStatus.OK,responseEntity.getStatusCode());
        Assertions.assertEquals(CONTENT,responseEntity.getBody().get(0).getContent());
    }

    private AddressDto generateAddress(){
        return AddressDto.builder()
                .content(CONTENT)
                .countyName(COUNTY_NAME)
                .userId(USER_ID)
                .build();
    }
}