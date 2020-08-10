package com.marcinwo.youtubeapi.demo.controller;

import com.marcinwo.youtubeapi.demo.JsonUtils;
import com.marcinwo.youtubeapi.demo.dto.CategoryDTO;
import com.marcinwo.youtubeapi.demo.dto.PatchCategoryDTO;
import com.marcinwo.youtubeapi.demo.entity.Category;
import com.marcinwo.youtubeapi.demo.exeption.CategoryNotFoundException;
import com.marcinwo.youtubeapi.demo.mapper.CategoryMapper;
import com.marcinwo.youtubeapi.demo.service.CategoryService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

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

        List<CategoryDTO> categoryDTOS = List.of(
                new CategoryDTO(1L, "Comedy"),
                new CategoryDTO(2L, "Sci-fi")
        );
        // when
        when(categoryService.getCategories()).thenReturn(categories);
        when(categoryMapper.toCategoryDTO(anyCollection())).thenReturn(categoryDTOS);

        // then
        mockMvc.perform(get("/categories"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(JsonUtils.toJsonString(categoryDTOS)));
    }

    @Test
    public void postCategoriesTest() throws Exception {
        CategoryDTO categoryDTOBeforeSave = new CategoryDTO("name1"); // nie ma id
        CategoryDTO categoryDTOAfterSave = new CategoryDTO(1L, "name1"); //to ma id, bo mu nadaje to id baza danych

        Category category = new Category(1L, "name1");
        when(categoryMapper.toCategoryEntity(any(CategoryDTO.class))).thenReturn(category);
        when(categoryService.postCategory(category)).thenReturn(category);
        when(categoryMapper.toCategoryDTO(category)).thenReturn(categoryDTOAfterSave);

        System.out.println(JsonUtils.toJsonString(categoryDTOBeforeSave));

        mockMvc.perform(post("/categories")
                .content(JsonUtils.toJsonString(categoryDTOBeforeSave))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))

                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("name1"))
                .andExpect(content().json(JsonUtils.toJsonString(categoryDTOAfterSave)));
    }


    @Test
    public void given_CategoryNotExist_when_PatchCategory_then_NotFound() throws Exception {
        PatchCategoryDTO patchCategoryDTO = new PatchCategoryDTO("test category");

        when(categoryService.findCategoryById(44L)).thenThrow(new CategoryNotFoundException("category not found."));
        when(categoryService.updateCategoryById(eq(44L), any(PatchCategoryDTO.class))).thenCallRealMethod();

        mockMvc.perform(patch("/categories/44")
                .content(JsonUtils.toJsonString(patchCategoryDTO))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .characterEncoding("utf-8"))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.error").value("Category not found."));
    }


}
