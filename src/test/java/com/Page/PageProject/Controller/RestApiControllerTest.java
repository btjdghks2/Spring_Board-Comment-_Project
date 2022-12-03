package com.Page.PageProject.Controller;

import com.Page.PageProject.Entity.PageEntity;
import com.Page.PageProject.service.RestApiService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;



import java.util.ArrayList;
import java.util.List;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest
class RestApiControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    RestApiService restApiService;

    @Test
    void save() throws Exception {
        PageEntity pageEntity = new PageEntity(1L,"제목1","내용1");
        String content = new ObjectMapper().writeValueAsString(pageEntity);
        when(restApiService.saved(pageEntity)).thenReturn(new PageEntity(1L,"제목1","내용1"));


        ResultActions resultAction = mockMvc.perform(
                post("/api/post/")
                        .content(content)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON));

        resultAction
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.title").value("제목1"))
                .andDo(print());


    }

    @Test
    void findAll() throws Exception{

        List<PageEntity> pageEntitys = new ArrayList<>();
        pageEntitys.add(new PageEntity(1L,"제목1","내용1"));
        pageEntitys.add(new PageEntity(2L,"제목2","내용2"));
        pageEntitys.add(new PageEntity(3L,"제목3","내용3"));
        when(restApiService.homePage()).thenReturn(pageEntitys);


        ResultActions resultAction = mockMvc.perform(get("/")
                .accept(MediaType.APPLICATION_JSON));

        resultAction
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.hasSize(3)))
                .andExpect(jsonPath("$.[0].title").value("제목1"))
                .andDo(print());



    }

    @Test
    void findById() throws Exception {
        Long id = 1L;
        when(restApiService.DetailPage(id)).thenReturn(new PageEntity(1L, "제목1", "내용1")); // stub - 행동 정의


        ResultActions resultAction = mockMvc.perform(get("/api/detail/{id}", id)
                .accept(MediaType.APPLICATION_JSON_UTF8));

        resultAction
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("제목1"))
                .andDo(print());


    }

    @Test
    void update() throws Exception{

        Long id = 1L;
        PageEntity pageEntitys = new PageEntity(null, "제목1", "내용1");
        String content = new ObjectMapper().writeValueAsString(new PageEntity(null, "제목1", "내용1"));
        when(restApiService.update(id, pageEntitys)).thenReturn(new PageEntity(1L, "제목1", "내용1"));

        // when
        ResultActions resultAction = mockMvc.perform(put("/api/detail/{id}/updated/", id)
                .content(content)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8));
        // then
        resultAction
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("제목1"))
                .andDo(print());
    }

    @Test
    public void deleted() throws Exception {


        Long id = 1L;
        when(restApiService.delete(id)).thenReturn("ok");


        ResultActions resultAction = mockMvc.perform(delete("/api/detail/{id}/delete/", id));


        resultAction
                .andExpect(status().isOk())
                .andDo(print());

        MvcResult requestResult = resultAction.andReturn();
        String result = requestResult.getResponse().getContentAsString();
        assertEquals("ok", result);
    }
}