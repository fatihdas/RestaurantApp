package com.restaurantapp.restapp.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.restaurantapp.restapp.model.dto.BranchDto;
import com.restaurantapp.restapp.model.dto.MenuDto;
import com.restaurantapp.restapp.model.request.create.CreateMenuRequest;
import com.restaurantapp.restapp.model.request.update.UpdateMenuRequest;
import com.restaurantapp.restapp.service.impl.MenuServiceImpl;
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
@WebMvcTest(value = MenuController.class)
public class MenuControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    MenuServiceImpl menuServiceImpl;

    String mapToJson(Object o) throws JsonProcessingException {

        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(o);
    }

    @Test
    public void add() throws Exception {

        MenuDto menu = this.generateMenu();

        String URI = "/menu";
        String inputJson = this.mapToJson(menu);

        Mockito.when(menuServiceImpl.createMenu(Mockito.any(CreateMenuRequest.class))).thenReturn(menu);

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

        List<MenuDto> menuList = new ArrayList<>();
        menuList.add(this.generateMenu());

        String URI = "/menu";
        String inputJson = this.mapToJson(menuList);

        Mockito.when(menuServiceImpl.getAllMenu()).thenReturn(menuList);

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

        MenuDto menu = this.generateMenu();

        String URI = "/menu/5";
        String inputJson = this.mapToJson(menu);

        Mockito.when(menuServiceImpl.getMenu(Mockito.anyLong())).thenReturn(menu);

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

        MenuDto menu = this.generateMenu();

        String URI = "/menu/12";
        String inputJson = this.mapToJson(menu);

        Mockito.when(menuServiceImpl.updateMenu(Mockito.any(UpdateMenuRequest.class),Mockito.anyLong())).thenReturn(menu);

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

        menuServiceImpl.deleteMenu(Mockito.anyLong());

        Mockito.verify(menuServiceImpl).deleteMenu(Mockito.anyLong());

    }

    private MenuDto generateMenu() {
        return MenuDto.builder()
                .build();
    }
}