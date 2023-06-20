package com.bikkadit.electronicstore.service;

import com.bikkadit.electronicstore.dtos.ProductDtos;

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
    List<ProductDtos> getAllProduct();
    //get all:live
    List<ProductDtos> getAllLive();
    // serch by title
    List<ProductDtos> serchByTitle(String keyword);

}
