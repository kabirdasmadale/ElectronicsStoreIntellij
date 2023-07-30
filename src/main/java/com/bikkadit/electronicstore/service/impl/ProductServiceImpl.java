package com.bikkadit.electronicstore.service.impl;

import com.bikkadit.electronicstore.dtos.PageableResponse;
import com.bikkadit.electronicstore.dtos.ProductDtos;
import com.bikkadit.electronicstore.exception.ResourceNotFoundException;
import com.bikkadit.electronicstore.helper.AppConstant;
import com.bikkadit.electronicstore.helper.Helper;
import com.bikkadit.electronicstore.model.Product;
import com.bikkadit.electronicstore.repositary.ProductRepositary;
import com.bikkadit.electronicstore.service.ProductService;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepositary productRepositary;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public ProductDtos create(ProductDtos productDtos) {
        Product product = modelMapper.map(productDtos, Product.class);
        Product product1 = this.productRepositary.save(product);
        return modelMapper.map(product1, ProductDtos.class);
    }

    @Override
    public ProductDtos update(ProductDtos productDtos, String productId) {
        Product product = this.productRepositary.findById(productId).orElseThrow(() -> new ResourceNotFoundException(AppConstant.PRODUCT_EXCEPTION));

        product.setTitle(productDtos.getTitle());
        product.setDescripsition(productDtos.getDescripsition());
        product.setPrice(productDtos.getPrice());
        product.setQuantity(productDtos.getQuantity());
        product.setLive(productDtos.isLive());
        product.setStotck(productDtos.isStotck());
        product.setAddedDate(productDtos.getAddedDate());
        product.setDiscountPrice(productDtos.getDiscountPrice());
        return modelMapper.map(product, ProductDtos.class);
    }

    @Override
    public void delete(String productId) {
        Product product = this.productRepositary.findById(productId).orElseThrow(() -> new ResourceNotFoundException(AppConstant.PRODUCT_EXCEPTION));
        this.productRepositary.delete(product);
    }

    @Override
    public ProductDtos getProductByProductId(String productId) {
        Product product = this.productRepositary.findById(productId).orElseThrow(() -> new ResourceNotFoundException(AppConstant.PRODUCT_EXCEPTION));
        ProductDtos productDtos = this.modelMapper.map(product, ProductDtos.class);
        return productDtos;
    }

    @Override
    public PageableResponse<ProductDtos> getAllProduct(int pageNumber, int pageSize, String sortBy, String sortDir) {
        Sort sort = (sortDir.equalsIgnoreCase("desc")) ? (Sort.by(sortBy).descending()) : (Sort.by(sortBy).ascending());
        Pageable pageable = PageRequest.of(pageNumber, pageSize,sort);
        Page<Product> page = this.productRepositary.findAll(pageable);
        return Helper.getPageableResponse(page, ProductDtos.class);

    }

    @Override
    public PageableResponse<ProductDtos> getAllLive(int pageNumber, int pageSize, String sortBy, String sortDir) {

        Sort sort = (sortDir.equalsIgnoreCase("desc")) ? (Sort.by(sortBy).descending()) : (Sort.by(sortBy).ascending());
        Pageable pageable = PageRequest.of(pageNumber, pageSize,sort);
        Page<ProductDtos> page = this.productRepositary.findByLiveTrue(pageable);
        return Helper.getPageableResponse(page, ProductDtos.class);

    }


    @Override
    public PageableResponse<ProductDtos> serchByTitle(String keyword, int pageNumber, int pageSize, String sortBy, String sortDir) {

        Sort sort = (sortDir.equalsIgnoreCase("desc")) ? (Sort.by(sortBy).descending()) : (Sort.by(sortBy).ascending());
        Pageable pageable = PageRequest.of(pageNumber, pageSize,sort);
        Page<ProductDtos> page = this.productRepositary.findByTitleContaining(keyword, pageable);
        return Helper.getPageableResponse(page, ProductDtos.class);
    }
}
