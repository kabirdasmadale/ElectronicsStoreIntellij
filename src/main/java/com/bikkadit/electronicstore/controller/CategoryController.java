package com.bikkadit.electronicstore.controller;

import com.bikkadit.electronicstore.dtos.CategoryDto;
import com.bikkadit.electronicstore.helper.ApiResponse;
import com.bikkadit.electronicstore.helper.AppConstant;
import com.bikkadit.electronicstore.service.CategoryServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class CategoryController {
    @Autowired
    private CategoryServiceI categoryServiceI;

    //create category
    @PutMapping
    public ResponseEntity<CategoryDto> createCtegory(@Valid @RequestBody CategoryDto categoryDto) {
        CategoryDto category = this.categoryServiceI.createCategory(categoryDto);
        return new ResponseEntity<>(category, HttpStatus.CREATED);

    }

    // update category
    @PutMapping("/category/{categoryId}")
    public ResponseEntity<CategoryDto> updatecategory(@Valid @RequestBody CategoryDto categoryDto, @PathVariable String categoryId) {
        CategoryDto updatedCategory = this.categoryServiceI.updateCategory(categoryDto, categoryId);
        return new ResponseEntity<>(updatedCategory, HttpStatus.OK);
    }

    //delete categary
    @DeleteMapping("/category/{categoryId}")
    public ResponseEntity<ApiResponse> deleteCategory(@PathVariable String categoryId) {
        this.categoryServiceI.deleteCategory(categoryId);
        ApiResponse apiResponse = ApiResponse.builder().message(AppConstant.CATEGORY_DELETE).success(AppConstant.CATEGORY_STATUS).Status(HttpStatus.OK).build();
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }
    //getCategory by id

    // getAllCategory by id
    //serch category by title

}




