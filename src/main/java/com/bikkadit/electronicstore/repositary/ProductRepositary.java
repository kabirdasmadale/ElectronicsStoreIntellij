package com.bikkadit.electronicstore.repositary;

import com.bikkadit.electronicstore.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepositary extends JpaRepository<Product,String> {

    List<Product> findByTitleContaining(String subtitle );

    List<Product> findByLiveTrue();
}
