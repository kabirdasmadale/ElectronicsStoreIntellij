package com.bikkadit.electronicstore.service;

import com.bikkadit.electronicstore.dtos.PageableResponse;
import com.bikkadit.electronicstore.dtos.ProductDtos;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService {

    //create
    ProductDtos create (ProductDtos productDtos);
    //update
    ProductDtos update(ProductDtos productDtos ,String productId);
    //delete
    void delete(String productId);
    //get singale
    ProductDtos getProductByProductId(String productId);
    //get all
 PageableResponse<ProductDtos> getAllProduct(int pageNumber, int pageSize,String sortBy,String sortDir);
    //get all:live
    PageableResponse<ProductDtos> getAllLive(int pageNumber, int pageSize,String sortBy,String sortDir);
    // serch by title
    PageableResponse<ProductDtos>serchByTitle(String keyword,int pageNumber, int pageSize,String sortBy,String sortDir);


}
