package com.blogapp.services;

import com.blogapp.formdata.CategoryForm;
import com.blogapp.payloads.CategoryDto;

import java.util.List;

public interface CategoryService {

     CategoryDto createCategory(CategoryForm categoryForm);

     CategoryDto updateCategory(CategoryForm categoryForm,Integer categoryId);

     void deleteCategory(Integer categoryId);

     CategoryDto getCategory(Integer categoryId);

     List<CategoryDto> getCategories();
}
