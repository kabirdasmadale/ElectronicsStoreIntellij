package com.bikkadit.electronicstore.controller;

import com.bikkadit.electronicstore.dtos.CategoryDto;
import com.bikkadit.electronicstore.service.CategoryServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryServiceI categoryServiceI;
    //create category
    @PutMapping
    public ResponseEntity<CategoryDto> createCtegory(@Valid @RequestBody CategoryDto categoryDto) {
        CategoryDto category = this.categoryServiceI.createCategory(categoryDto);
        return new ResponseEntity<>(category, HttpStatus.OK);

    }
    // update category


}




