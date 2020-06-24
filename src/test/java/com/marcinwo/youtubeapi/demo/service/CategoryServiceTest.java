package com.marcinwo.youtubeapi.demo.service;

import com.marcinwo.youtubeapi.demo.entity.Category;
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

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class  CategoryServiceTest {

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



}
