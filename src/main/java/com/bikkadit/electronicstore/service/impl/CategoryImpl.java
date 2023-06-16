package com.bikkadit.electronicstore.service.impl;

import com.bikkadit.electronicstore.dtos.CategoryDto;
import com.bikkadit.electronicstore.dtos.PageableResponse;
import com.bikkadit.electronicstore.exception.ResourceNotFoundException;
import com.bikkadit.electronicstore.helper.AppConstant;
import com.bikkadit.electronicstore.model.Category;
import com.bikkadit.electronicstore.repositary.CategoryRepository;
import com.bikkadit.electronicstore.service.CategoryServiceI;
import org.hibernate.hql.spi.id.local.Helper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class CategoryImpl implements CategoryServiceI {
    @Autowired
    private CategoryRepository categoryRepositary;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) {
        Category category = this.modelMapper.map(categoryDto, Category.class);
        String randomId = UUID.randomUUID().toString();
        category.setCategoryId(randomId);
        Category savedCategory = this.categoryRepositary.save(category);
        CategoryDto categoryDto1 = this.modelMapper.map(savedCategory, CategoryDto.class);
        return categoryDto1;
    }

    @Override
    public CategoryDto updateCategory(CategoryDto categoryDto, String categoryId) {
        Category category = this.categoryRepositary.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException(AppConstant.EXCEPTION_MSG));
        category.setTitle(categoryDto.getTitle());
        category.setDescription(categoryDto.getDescription());
        category.setCoverImage(categoryDto.getCoverImage());
        Category updatedCategory = categoryRepositary.save(category);
        return modelMapper.map(updatedCategory, CategoryDto.class);
    }

    @Override
    public void deleteCategory(String categoryId) {
        Category category = this.categoryRepositary.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException(AppConstant.EXCEPTION_MSG));
        this.categoryRepositary.delete(category);

    }

    @Override
    public CategoryDto getCategorybyId(String categoryId) {
        Category category = this.categoryRepositary.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException(AppConstant.EXCEPTION_MSG));
        CategoryDto categoryDto = this.modelMapper.map(category, CategoryDto.class);
        return categoryDto;

    }

    @Override
    public PageableResponse<CategoryDto> getAllCategories(int pageNumber, int pageSize, String sortBy, String sortDir) {
        Sort sort=(sortDir.equalsIgnoreCase("desc"))?(Sort.by(sortBy).descending()):(Sort.by(sortBy).ascending());
        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
        Page<Category> page = categoryRepositary.findAll(pageable);
        PageableResponse<CategoryDto> pageableResponse = Helper.getPageableRespons(page, CategoryDto.class);
        return pageableResponse;
    }

    @Override
    public List<CategoryDto> searchCategorybytitle(String keyword) {
        List<Category> containing = categoryRepositary.findByTitleContaining(keyword);
        List<CategoryDto> collect = containing.stream().map((data) -> this.modelMapper.map(data, CategoryDto.class)).collect(Collectors.toList());
        return collect;
    }
}

