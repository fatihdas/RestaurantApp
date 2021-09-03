//package com.restaurantapp.restapp.controller;
//
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.restaurantapp.restapp.model.dto.BranchDto;
//import com.restaurantapp.restapp.model.dto.MenuDto;
//import com.restaurantapp.restapp.model.request.create.CreateBranchRequest;
//import com.restaurantapp.restapp.model.request.update.UpdateBranchRequest;
//import com.restaurantapp.restapp.service.impl.BranchServiceImpl;
//import org.assertj.core.api.Assertions;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.Mockito;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.MediaType;
//import org.springframework.mock.web.MockHttpServletResponse;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.MvcResult;
//import org.springframework.test.web.servlet.RequestBuilder;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//
//import java.nio.charset.StandardCharsets;
//import java.util.ArrayList;
//import java.util.List;
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@WebMvcTest(BranchController.class)
//public class BranchControllerTest {
//
//    @Autowired
//    MockMvc mockMvc;
//
//    @MockBean
//    BranchServiceImpl branchServiceImpl;
//
//    private String mapToJson(Object object) throws JsonProcessingException {
//        ObjectMapper objectMapper = new ObjectMapper();
//        return objectMapper.writeValueAsString(object);
//    }
//
//    @Test
//    public void add() throws Exception {
//        BranchDto branch = this.generateBranch();
//
//        String URI = "/branch";
//        String inputJson = this.mapToJson(branch);
//
//        Mockito.when(branchServiceImpl.createBranch(Mockito.any(CreateBranchRequest.class))).thenReturn(branch);
//
//        RequestBuilder requestBuilder = MockMvcRequestBuilders
//                .post(URI)
//                .accept(MediaType.APPLICATION_JSON).content(inputJson)
//                .contentType(MediaType.APPLICATION_JSON_VALUE);
//
//        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
//        MockHttpServletResponse response = result.getResponse();
//
//        String outputJson = response.getContentAsString(StandardCharsets.UTF_8);
//
//        Assertions.assertThat(inputJson).isEqualTo(outputJson);
//    }
//
//    @Test
//    public void getAll() throws Exception {
//
//        List<BranchDto> branchList = new ArrayList<>();
//        branchList.add(this.generateBranch());
//
//        String URI = "/branch";
//        String inputJson = this.mapToJson(branchList);
//
//        Mockito.when(branchServiceImpl.getAllBranches()).thenReturn(branchList);
//
//        RequestBuilder requestBuilder = MockMvcRequestBuilders
//                .get(URI)
//                .accept(MediaType.APPLICATION_JSON);
//
//        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
//        MockHttpServletResponse response = result.getResponse();
//
//        String outputJson = response.getContentAsString(StandardCharsets.UTF_8);
//
//        Assertions.assertThat(inputJson).isEqualTo(outputJson);
//    }
//
//    @Test
//    public void getById() throws Exception {
//
//        BranchDto branch = this.generateBranch();
//
//        String URI = "/branch/13";
//        String inputJson = this.mapToJson(branch);
//
//        Mockito.when(branchServiceImpl.getBranchDto(Mockito.anyLong())).thenReturn(branch);
//
//        RequestBuilder requestBuilder = MockMvcRequestBuilders
//                .get(URI)
//                .accept(MediaType.APPLICATION_JSON);
//
//        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
//        MockHttpServletResponse response = result.getResponse();
//
//        String outputJson = response.getContentAsString(StandardCharsets.UTF_8);
//
//        Assertions.assertThat(inputJson).isEqualTo(outputJson);
//    }
//
//    @Test
//    public void getAllBtWaiting() throws Exception {
//
//        List<BranchDto> branchList = new ArrayList<>();
//        branchList.add(this.generateBranch());
//
//        String URI = "/branch/waiting";
//        String inputJson = this.mapToJson(branchList);
//
//        Mockito.when(branchServiceImpl.getWaitingBranches("waiting")).thenReturn(branchList);
//
//        RequestBuilder requestBuilder = MockMvcRequestBuilders
//                .get(URI)
//                .accept(MediaType.APPLICATION_JSON);
//
//        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
//        MockHttpServletResponse response = result.getResponse();
//
//        String outputJson = response.getContentAsString(StandardCharsets.UTF_8);
//
//        Assertions.assertThat(inputJson).isEqualTo(outputJson);
//    }
//
//    @Test
//    public void update() throws Exception {
//
//        BranchDto branch = this.generateBranch();
//        String message = "Success branch has been updated! id:" + branch.getId();
//
//        String URI = "/branch/5";
//        String inputJson = this.mapToJson(branch);
//
//        Mockito.when(branchServiceImpl.updateBranch(Mockito.any(UpdateBranchRequest.class)
//                ,Mockito.anyLong())).thenReturn(message);
//
//        RequestBuilder requestBuilder = MockMvcRequestBuilders
//                .put(URI)
//                .accept(MediaType.APPLICATION_JSON).content(inputJson)
//                .contentType(MediaType.APPLICATION_JSON_VALUE);
//
//        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
//        MockHttpServletResponse response = result.getResponse();
//
//        String outputJson = response.getContentAsString(StandardCharsets.UTF_8);
//
//        Assertions.assertThat(message).isEqualTo(outputJson);
//    }
//
//    @Test
//    public void delete() throws Exception {
//
//        branchServiceImpl.deleteBranch(Mockito.anyLong());
//
//        Mockito.verify(branchServiceImpl).deleteBranch(Mockito.anyLong());
//    }
//
//    private BranchDto generateBranch() {
//        return BranchDto.builder()
//                .name("etilerşubesi")
//                .menuDto(MenuDto.builder().build())
//                .build();
//    }
//}