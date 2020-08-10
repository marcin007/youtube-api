package com.marcinwo.youtubeapi.demo.mapper;

import com.marcinwo.youtubeapi.demo.dto.CategoryDTO;
import com.marcinwo.youtubeapi.demo.entity.Category;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CategoryMapperTest {

    private CategoryMapper categoryMapper = new CategoryMapperImpl();

    @Test
    public void toCategoryDtoTest() {
        Category category = new Category(1L, "category1");

        CategoryDTO categoryDTO = categoryMapper.toCategoryDTO(category);

        assertEquals(categoryDTO.getId(), category.getId());
        assertEquals(categoryDTO.getName(), category.getName());
    }

    @Test
    public void toCategoryEntityTest() {
        CategoryDTO categoryDTO = new CategoryDTO(1L, "aaa");

        Category category = categoryMapper.toCategoryEntity(categoryDTO);

        assertEquals(categoryDTO.getName(), category.getName());
        assertEquals(categoryDTO.getId(), category.getId());
    }

}
