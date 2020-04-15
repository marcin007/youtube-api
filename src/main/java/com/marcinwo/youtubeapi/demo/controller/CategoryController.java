package com.marcinwo.youtubeapi.demo.controller;


import com.marcinwo.youtubeapi.demo.ApiInformation;
import com.marcinwo.youtubeapi.demo.dto.CategoryDTO;
import com.marcinwo.youtubeapi.demo.dto.PatchCategoryDTO;
import com.marcinwo.youtubeapi.demo.entity.Category;
import com.marcinwo.youtubeapi.demo.mapper.CategoryMapper;
import com.marcinwo.youtubeapi.demo.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.HttpMediaTypeException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;


@RestController
public class CategoryController {

    private CategoryService categoryService;
    private CategoryMapper categoryMapper;

    @Autowired
    public CategoryController(CategoryService categoryService, CategoryMapper categoryMapper) {
        this.categoryService = categoryService;
        this.categoryMapper = categoryMapper;
    }

    @GetMapping("/categories")
    public List<CategoryDTO> getCategories() {
        return categoryMapper.toCategoryDTO(categoryService.getCategories());
    }

//    @PostMapping
//    public CategoryDTO postCategory(@RequestBody CategoryDTO categoryDTO){
//        return categoryMapper.toCategoryDTO(categoryService.postCategory(categoryMapper.toCategoryEntity(categoryDTO)));
//    }
    @DeleteMapping("/categories/{id}")
    public ApiInformation deleteCategory(@PathVariable Long id){
        categoryService.deleteCategory(id);
        return new ApiInformation("Delete sucessful.", HttpStatus.OK.value());
    }

    @PatchMapping("/category/{id}")
    public CategoryDTO updateCategoryById(@PathVariable Long id, @Valid @RequestBody PatchCategoryDTO patchCategoryDTO){
        return categoryMapper.toCategoryDTO(categoryService.updateCategoryById(id,patchCategoryDTO));
    }


}
