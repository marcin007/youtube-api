package com.marcinwo.youtubeapi.demo.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.marcinwo.youtubeapi.demo.JsonUtils;
import com.marcinwo.youtubeapi.demo.YoutubeApiApplication;
import com.marcinwo.youtubeapi.demo.dto.PatchCategoryDTO;
import com.marcinwo.youtubeapi.demo.mapper.CategoryMapper;
import com.marcinwo.youtubeapi.demo.service.CategoryService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ContextConfiguration(classes = YoutubeApiApplication.class)
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest
public class CategoryControllerTestIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CategoryMapper categoryMapper;

    @Autowired
    private CategoryService categoryService;

    @Test// TODO nie działa CZEMU??
    public void given_CategoryNotFound_when_PatchCategory_then_NotFound() throws Exception {
        categoryService.findCategoryById(22L);

        PatchCategoryDTO patchCategoryDTO = new PatchCategoryDTO("category test");

        mockMvc.perform(patch("/categories/22")
                .content(JsonUtils.toJsonString(patchCategoryDTO))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .characterEncoding("utf-8"))

                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.error").value("Category not found."));
    }

}
