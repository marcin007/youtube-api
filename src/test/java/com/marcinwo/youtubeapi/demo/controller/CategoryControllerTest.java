package com.marcinwo.youtubeapi.demo.controller;

import com.marcinwo.youtubeapi.demo.JsonUtils;
import com.marcinwo.youtubeapi.demo.dto.CategoryDTO;
import com.marcinwo.youtubeapi.demo.entity.Category;
import com.marcinwo.youtubeapi.demo.mapper.CategoryMapper;
import com.marcinwo.youtubeapi.demo.service.CategoryService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.anyCollection;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(CategoryController.class)
public class CategoryControllerTest {


    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CategoryService categoryService;

    @MockBean
    private CategoryMapper categoryMapper;

    @Test
    public void getCategoriesTest() throws Exception {
        // given
        List<Category> categories = List.of(
                new Category(1L, "Comedy"),
                new Category(2L, "Sci-fi")
        );

        // when
        when(categoryService.getCategories()).thenReturn(categories);

        List<CategoryDTO> categoryDTOS = List.of(
                new CategoryDTO(1L, "Comedy"),
                new CategoryDTO(2L, "Sci-fi")
        );

        when(categoryMapper.toCategoryDTO(anyCollection())).thenReturn(categoryDTOS);

        // then
        mockMvc.perform(get("/categories"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(JsonUtils.toJsonString(categoryDTOS)));
    }
}
