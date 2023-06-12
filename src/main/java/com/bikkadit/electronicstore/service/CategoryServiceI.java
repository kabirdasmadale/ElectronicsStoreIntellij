package com.bikkadit.electronicstore.service;

import com.bikkadit.electronicstore.dtos.CategoryDto;
import com.bikkadit.electronicstore.dtos.PageableResponse;

import java.util.List;

public interface CategoryServiceI {

    CategoryDto createCategory(CategoryDto categoryDto);

    CategoryDto updateCategory(CategoryDto categoryDto ,String categoryId);

    void deleteCategory(String categoryId);

    CategoryDto getCategorybyId (String categoryId);

    PageableResponse<CategoryDto> getAllCategories(int pageNumber , int pageSize , String sortBy , String sortDir);

    List<CategoryDto> searchCategorybytitle(String keyword);

}
