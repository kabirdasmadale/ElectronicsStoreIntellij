package com.bikkadit.electronicstore.repositary;

import com.bikkadit.electronicstore.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface CategoryRepository extends JpaRepository<Category,String> {

    List<Category> findByTitleContaining(String keyword);
}
