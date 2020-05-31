package com.marcinwo.youtubeapi.demo.controller;

import com.marcinwo.youtubeapi.demo.ApiInformation;
import com.marcinwo.youtubeapi.demo.dto.CategoryDTO;
import com.marcinwo.youtubeapi.demo.dto.PatchCategoryDTO;
import com.marcinwo.youtubeapi.demo.entity.Category;
import com.marcinwo.youtubeapi.demo.mapper.CategoryMapper;
import com.marcinwo.youtubeapi.demo.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CategoryControllerWithoutMapper {

    private CategoryService categoryService;

    @Autowired
    public CategoryControllerWithoutMapper(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/categories2")
    public List<Category> getCategories() {
        return categoryService.getCategories();
    }


    @DeleteMapping("/categories/{id}")
    public ApiInformation deleteCategory(@PathVariable Long id){
        categoryService.deleteCategory(id);
        return new ApiInformation("Delete sucessful.", HttpStatus.OK.value());
    }

//    @PostMapping("/categories")
//    public CategoryDTO postCategory(@RequestBody CategoryDTO categoryDTO){
//        return categoryMapper.toCategoryDTO();
//    }
//
//    @PatchMapping("/categories/{id}")
//    public CategoryDTO updateCategoryById(@PathVariable Long id, @Valid @RequestBody PatchCategoryDTO patchCategoryDTO){
//        return categoryMapper.toCategoryDTO(categoryService.updateCategoryById(id,patchCategoryDTO));
//    }
}
