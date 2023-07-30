package com.bikkadit.electronicstore.controller;

import com.bikkadit.electronicstore.dtos.PageableResponse;
import com.bikkadit.electronicstore.dtos.ProductDtos;
import com.bikkadit.electronicstore.helper.ApiResponse;
import com.bikkadit.electronicstore.helper.AppConstant;
import com.bikkadit.electronicstore.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    // create
    @PostMapping
    public ResponseEntity<ProductDtos> cteate(@RequestBody ProductDtos productDtos) {
        ProductDtos createdProducts = productService.create(productDtos);
        return new ResponseEntity<>(createdProducts, HttpStatus.CREATED);

    }

    // update

    @PutMapping("/{productId")
    public ResponseEntity<ProductDtos> update(@RequestBody ProductDtos productDtos, @PathVariable String productId) {
        ProductDtos updatedProducts = productService.update(productDtos, productId);
        return new ResponseEntity<>(updatedProducts, HttpStatus.OK);
    }

    // delete
    @DeleteMapping("/{productId}")
    public ResponseEntity<ApiResponse> deleteProduct(@PathVariable String productid) {
        this.productService.delete(productid);
        ApiResponse build = ApiResponse.builder().message(AppConstant.PRODUCT_DELETE).Status(HttpStatus.OK).success(true).build();
        return ResponseEntity.ok(build);


    }


    // get
    @GetMapping("/{productId}")
    public ResponseEntity<ProductDtos> getSingle(@PathVariable String productId) {
        ProductDtos product = productService.getProductByProductId(productId);
        return new ResponseEntity<>(product, HttpStatus.OK);

    }


    // get all
    @GetMapping
    public ResponseEntity<PageableResponse<ProductDtos>> getAllProduct(
            @RequestParam(value = "pageNumber", defaultValue = "0", required = false) int pageNumber,
            @RequestParam(value = "pageSize", defaultValue = "1", required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = "title", required = false) String sortBy,
            @RequestParam(value = "SortDir", defaultValue = "asc", required = false) String sortDir) {
        PageableResponse<ProductDtos> allProduct = this.productService.getAllProduct(pageNumber, pageSize, sortBy, sortDir);
        ResponseEntity<PageableResponse<ProductDtos>> responseEntity = new ResponseEntity<>(allProduct, HttpStatus.OK);
        return responseEntity;
    }


    //product live
    @GetMapping("Live")
    public ResponseEntity<PageableResponse<ProductDtos>> productIsLive(
            @RequestParam(value = "pageNumber", defaultValue = "0", required = false) int pageNumber,
            @RequestParam(value = "pageSize", defaultValue = "1", required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = "title", required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = "asc", required = false) String sortDir
    ) {
        PageableResponse<ProductDtos> allLive = this.productService.getAllLive(pageNumber, pageSize, sortBy, sortDir);
        ResponseEntity<PageableResponse<ProductDtos>> allLiveProduct = new ResponseEntity<PageableResponse<ProductDtos>>(allLive, HttpStatus.OK);
        return allLiveProduct;
    }


    // serch by title
    @GetMapping("/query")
    public ResponseEntity<PageableResponse<ProductDtos>> serchByTitle(
            @PathVariable String query,
            @RequestParam(value = "pageNumber", defaultValue = "0", required = false) int pageNumber,
            @RequestParam(value = "pageSize", defaultValue = "1", required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = "title", required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = "asc", required = false) String sortDir
    ) {
        PageableResponse<ProductDtos> pageableResponse = this.productService.serchByTitle(query, pageNumber, pageSize, sortBy, sortDir);
        return new ResponseEntity<>(pageableResponse, HttpStatus.OK);
    }


}
