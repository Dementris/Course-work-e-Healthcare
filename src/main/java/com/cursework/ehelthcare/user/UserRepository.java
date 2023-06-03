package com.cursework.ehelthcare.user;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * The interface User repository.
 */
@Repository
@Transactional(readOnly = true)
public interface UserRepository extends JpaRepository<User, Long> {
    /**
     * Find by email optional.
     *
     * @param email the email
     * @return the optional
     */
    Optional<User> findByEmail(String email);

    /**
     * Find by user role optional.
     *
     * @param userRole the user role
     * @return the optional
     */
    Optional<User> findByUserRole(UserRole userRole);

    /**
     * Find all by user role list.
     *
     * @param userRole the user role
     * @return the list
     */
    List<User> findAllByUserRole(UserRole userRole);

    /**
     * Enable app user int.
     *
     * @param email the email
     * @return the int
     */
    @Transactional
    @Modifying
    @Query("UPDATE User u SET u.enabled = TRUE WHERE u.email = ?1")
    int enableAppUser(String email);
}
