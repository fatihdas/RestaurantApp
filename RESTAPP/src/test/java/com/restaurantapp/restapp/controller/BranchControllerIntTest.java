package com.restaurantapp.restapp.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.restaurantapp.restapp.model.request.create.CreateAddressRequest;
import com.restaurantapp.restapp.model.request.create.CreateBranchRequest;
import com.restaurantapp.restapp.model.request.get.BranchPageGetRequest;
import com.restaurantapp.restapp.security.JwtFilter;
import com.restaurantapp.restapp.service.impl.BranchServiceImpl;
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
@WebMvcTest(BranchController.class)
@AutoConfigureMockMvc(addFilters = false)
public class BranchControllerIntTest {

    private static final String NAME = "Branch Test";
    private static final long ID = 1L;
    private static final long RESTAURANT_ID = 1L;
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BranchServiceImpl branchService;

    @MockBean
    private JwtFilter jwtFilter;

    @MockBean
    private UserServiceImpl userService;

    private String mapToJson(Object object) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(object);
    }

    @Test
    public void whenCreateBranchCalled_thenReturnSavedBranch() throws Exception {
        String URI = "/branch";
        CreateBranchRequest request = CreateBranchRequest.builder()
                .name(NAME)
                .id(ID)
                .restaurantId(RESTAURANT_ID)
                .createAddressRequest(new CreateAddressRequest())
                .build();
        String inputJson = this.mapToJson(request);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post(URI)
                .accept(MediaType.APPLICATION_JSON).content(inputJson)
                .contentType(MediaType.APPLICATION_JSON_VALUE);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();
        Assertions.assertEquals(HttpStatus.CREATED.value(), response.getStatus());
    }

    @Test
    public void whenGetNearBranchesCalled_thenReturnNearBranchList() throws Exception {
        String URI = "/branch";
        BranchPageGetRequest request = BranchPageGetRequest.builder()
                .pageNo(1)
                .pageSize(1)
                .countyId(12L)
                .build();
        String inputJson = this.mapToJson(request);
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get(URI)
                .accept(MediaType.APPLICATION_JSON).content(inputJson)
                .contentType(MediaType.APPLICATION_JSON_VALUE);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();
        Assertions.assertEquals(HttpStatus.OK.value(), response.getStatus());
    }

    @Test
    public void whenGetByStatusCalledByStatus_thenReturnBranchList() throws Exception {
        String URI = "/branch/status/waiting";
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get(URI)
                .accept(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();
        Assertions.assertEquals(HttpStatus.OK.value(), response.getStatus());
    }

    @Test
    public void whenUpdateStatusCalledByBranchIdAndStatus_thenReturnUpdatedBranch() throws Exception {
        String URI = "/branch/1/approved";
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .put(URI)
                .accept(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();
        Assertions.assertEquals(HttpStatus.OK.value(), response.getStatus());
    }
}