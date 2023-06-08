package com.bikkadit.electronicstore.repositary;

import com.bikkadit.electronicstore.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface UserRepositary extends JpaRepository<User, String> {
    Optional<User> findByUserEmail(String email);

    List<User> findByUserNameContaining(String keyword);

}
