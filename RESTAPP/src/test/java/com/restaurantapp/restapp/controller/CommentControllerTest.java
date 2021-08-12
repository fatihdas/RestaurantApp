package com.restaurantapp.restapp.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.restaurantapp.restapp.model.dto.BranchDto;
import com.restaurantapp.restapp.model.dto.CommentDto;
import com.restaurantapp.restapp.model.request.create.CreateCommentRequest;
import com.restaurantapp.restapp.model.request.update.UpdateCommentRequest;
import com.restaurantapp.restapp.service.impl.CommentServiceImpl;
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
@WebMvcTest(value = CommentController.class)
public class CommentControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    CommentServiceImpl commentServiceImpl;

    String mapToJson(Object o) throws JsonProcessingException {

        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(o);
    }


    @Test
    public void add() throws Exception {

        CommentDto comment = this.generateComment();

        String URI = "/commentList";
        String inputJson = this.mapToJson(comment);

        Mockito.when(commentServiceImpl.createComment(Mockito.any(CreateCommentRequest.class))).thenReturn(comment);

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

        List<CommentDto> commentList = new ArrayList<>();
        commentList.add(this.generateComment());

        String URI = "/commentList";
        String inputJson = this.mapToJson(commentList);

        Mockito.when(commentServiceImpl.getAllComments()).thenReturn(commentList);

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

        CommentDto comment = this.generateComment();

        String URI = "/commentList/13";
        String inputJson = this.mapToJson(comment);

        Mockito.when(commentServiceImpl.getComment(Mockito.anyLong())).thenReturn(comment);

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

        CommentDto comment = this.generateComment();

        String URI = "/commentList/7";
        String inputJson = this.mapToJson(comment);

        Mockito.when(commentServiceImpl.updateComment(Mockito.any(UpdateCommentRequest.class)
                ,Mockito.anyLong())).thenReturn(comment);

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

        commentServiceImpl.deleteComment(Mockito.anyLong());

        Mockito.verify(commentServiceImpl).deleteComment(Mockito.anyLong());

    }

    private CommentDto generateComment() {
        return CommentDto.builder()
                .content("bla bla bla")
                .build();
    }
}