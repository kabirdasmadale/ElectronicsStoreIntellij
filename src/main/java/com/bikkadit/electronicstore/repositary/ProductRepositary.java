package com.bikkadit.electronicstore.repositary;

import com.bikkadit.electronicstore.dtos.ProductDtos;
import com.bikkadit.electronicstore.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepositary extends JpaRepository<Product,String> {

    Page<ProductDtos> findByTitleContaining(String subtitle,Pageable pageable );

    Page<ProductDtos> findByLiveTrue(Pageable pagable);
}
