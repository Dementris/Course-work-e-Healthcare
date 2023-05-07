package com.cursework.ehelthcare.repository;


import com.cursework.ehelthcare.model.User;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional(readOnly = true)
public interface UserRepository {
    Optional<User> findByEmail(String email);
}
