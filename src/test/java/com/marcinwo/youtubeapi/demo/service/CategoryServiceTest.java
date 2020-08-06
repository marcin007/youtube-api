package com.marcinwo.youtubeapi.demo.service;

import com.marcinwo.youtubeapi.demo.ExampleData;
import com.marcinwo.youtubeapi.demo.dto.PatchCategoryDTO;
import com.marcinwo.youtubeapi.demo.entity.Category;
import com.marcinwo.youtubeapi.demo.exeption.CategoryNotFoundException;
import com.marcinwo.youtubeapi.demo.repository.CategoryRepository;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class CategoryServiceTest {

    @Autowired
    private CategoryService categoryService;

    @MockBean
    private CategoryRepository categoryRepository;


    @Test
    public void given_repositoryHasTwoCategories_when_getCategories_then_returnCategoryList() {
        List<Category> categories = Arrays.asList(
                new Category(1L, "comedy"),
                new Category(2L, "horror"));

        when(categoryRepository.findAll()).thenReturn(categories);

        List<Category> serviceCategories = categoryService.getCategories();
        assertThat(serviceCategories).isEqualTo(categories);

        verify(categoryRepository, times(1)).findAll();
        verifyNoMoreInteractions(categoryRepository);
    }

    @Test
    public void given_categoriesNotExist_when_getCategories_then_returnEmptyList() {
        List<Category> categories = new ArrayList<>();

        when(categoryRepository.findAll()).thenReturn(categories);

        List<Category> serviceCategories = categoryService.getCategories();

        assertThat(serviceCategories).isEmpty();
    }

    @Test
    public void given_categoryExist_when_findCategoryById_then_returnCategory() {
        Category category = new Category(1L, "comedy");

        when(categoryRepository.findById(1L)).thenReturn(Optional.of(category));

        Category categoryById = categoryService.findCategoryById(1L);

        assertThat(categoryById).isEqualTo(category);
    }

    @Test(expected = CategoryNotFoundException.class)
    public void given_categoryNotExist_when_findCategoryById_then_categoryNotFoundException() {
        when(categoryRepository.findById(1L)).thenThrow(new CategoryNotFoundException("Category not found."));

        categoryService.findCategoryById(1L);
    }

    @Test
    public void given_categoryExist_when_postCategory_then_saveCategory() {
        Category category = new Category(1L, "comedy");

        when(categoryRepository.save(category)).thenReturn(category);

        Category categorySaved = categoryService.postCategory(category);

        assertThat(categorySaved).isNotNull();
        verify(categoryRepository).save(any(Category.class));
    }

    @Test
    public void given_categoryExist_when_deleteCategory_then_categoryNotExist() {

        doNothing().when(categoryRepository).deleteById(1L);

        categoryRepository.deleteById(1L);

        verify(categoryRepository, times(1)).deleteById(1L);
    }

    @Test
    public void given_categoryExist_when_updateCategoryById_then_categoryNameUpdated() {
        Category category = new Category(1L, "comedy");
        PatchCategoryDTO patchCategoryDTO = new PatchCategoryDTO("comedy updated");

        when(categoryRepository.findById(1L)).thenReturn(Optional.of(category));
        when(categoryRepository.save(category)).thenReturn(category);

        Category updatedCategory = categoryService.updateCategoryById(1L, patchCategoryDTO);

        assertThat(updatedCategory.getName()).isEqualTo("comedy updated");
    }

}
