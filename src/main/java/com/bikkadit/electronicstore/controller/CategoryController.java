package com.bikkadit.electronicstore.controller;

import com.bikkadit.electronicstore.dtos.CategoryDto;
import com.bikkadit.electronicstore.dtos.PageableResponse;
import com.bikkadit.electronicstore.helper.ApiResponse;
import com.bikkadit.electronicstore.helper.AppConstant;
import com.bikkadit.electronicstore.service.CategoryServiceI;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api")
public class CategoryController {
    @Autowired
    private CategoryServiceI categoryServiceI;

    /**
     * @param categoryDto
     * @return
     * @Author Kabirdas madale
     * @apiNote This api is used to create Category
     */
    @PostMapping
    public ResponseEntity<CategoryDto> createCtegory(@Valid @RequestBody CategoryDto categoryDto) {
        log.info(" Starting request for ccreate Category");
        CategoryDto category = this.categoryServiceI.createCategory(categoryDto);
        log.info(" Ending request for ccreate Category");
        return new ResponseEntity<>(category, HttpStatus.CREATED);

    }

    /**
     * @param categoryDto
     * @param categoryId
     * @return
     * @Author Kabirdas Madale
     * @apiNote This api is used to update category
     */
    @PutMapping("/category/{categoryId}")
    public ResponseEntity<CategoryDto> updatecategory(@Valid @RequestBody CategoryDto categoryDto, @PathVariable String categoryId) {
        log.info("Starting request for update category by categoryId : {}", categoryId);
        CategoryDto updatedCategory = this.categoryServiceI.updateCategory(categoryDto, categoryId);
        log.info("Ending request for update category by categoryId : {}", categoryId);
        return new ResponseEntity<>(updatedCategory, HttpStatus.OK);
    }

    /**
     * @param categoryId
     * @return
     * @Author Kabirdas madale
     * @apiNote This api is used to delete category
     */
    @DeleteMapping("/category/{categoryId}")
    public ResponseEntity<ApiResponse> deleteCategory(@PathVariable String categoryId) {
        log.info("Starting request for delete category by categoryId : {}", categoryId);
        this.categoryServiceI.deleteCategory(categoryId);
        ApiResponse apiResponse = ApiResponse.builder().message(AppConstant.CATEGORY_DELETE).success(AppConstant.CATEGORY_STATUS).Status(HttpStatus.OK).build();
        log.info("Ending request for delete category by categoryId : {}", categoryId);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    /**
     * @Author kabirdas Madale
     * @apiNote  This api is used to get category by id
     * @param categoryId
     * @return
     */
    @GetMapping("/category/{categoryId}")
    public ResponseEntity<CategoryDto> getCategoryById(@PathVariable String categoryId) {
        log.info("Starting request for get category by categoryId : {}", categoryId);
        CategoryDto categorybyId = this.categoryServiceI.getCategorybyId(categoryId);
        log.info("Ending request for get category by categoryId : {}", categoryId);
        return new ResponseEntity<CategoryDto>(categorybyId, HttpStatus.OK);

    }

    /**
     * @Author kabirdas madale
     * @apiNoThis api is used to get all Category
     * @param pageNumber
     * @param pageSize
     * @param sortBy
     * @param sortDir
     * @return
     */
    @GetMapping("/allcategory")
    public ResponseEntity<PageableResponse<CategoryDto>> getAllCategory(
            @RequestParam(value = "pageNumber", defaultValue = "0", required = false) int pageNumber,
            @RequestParam(value = "pageSize", defaultValue = "1", required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = "title", required = false) String sortBy,
            @RequestParam(value = "SortDir", defaultValue = "asc", required = false) String sortDir) {
        log.info("Starting request for get all category ");
        PageableResponse<CategoryDto> allCategories = this.categoryServiceI.getAllCategories(pageNumber, pageSize, sortBy, sortDir);
        ResponseEntity<PageableResponse<CategoryDto>> pageableResponseResponseEntity = new ResponseEntity<>(allCategories, HttpStatus.OK);
        log.info("Ending request for get all category ");
        return pageableResponseResponseEntity;
    }

    /**
     * @Author Kabirdas Madale
     * @apiNote  This api is used to serch category by title
     * @param keyword
     * @return
     */
    @GetMapping("/category/search/{keyword}")
    public ResponseEntity<List<CategoryDto>> searchCategorybytitle(@PathVariable String keyword) {
        log.info("Starting request for serch category by keyword  : {}", keyword);
        List<CategoryDto> categoryDtos = this.categoryServiceI.searchCategorybytitle(keyword);
        log.info("Ending request for serch category by keyword : {}", keyword);
        return new ResponseEntity<List<CategoryDto>>(categoryDtos, HttpStatus.FOUND);
    }

}




