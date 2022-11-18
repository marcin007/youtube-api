package com.marcinwo.youtubeapi.demo.controller;

import com.marcinwo.youtubeapi.demo.ApiInformation;
import com.marcinwo.youtubeapi.demo.entity.Category;
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


    @DeleteMapping("/categories2/{id}")
    public ApiInformation deleteCategory(@PathVariable Long id){
        categoryService.deleteCategory(id);
        return new ApiInformation("Delete sucessful.", HttpStatus.OK.value());
    }

}
