package com.cursework.ehelthcare.registration.token;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

/**
 * The interface Confirmation token repository.
 */
@Repository
@Transactional(readOnly = true)
public interface ConfirmationTokenRepository extends JpaRepository<ConfirmationToken, Long> {

    /**
     * Find by token optional.
     *
     * @param token the token
     * @return the optional
     */
    Optional<ConfirmationToken> findByToken(String token);

    /**
     * Update confirmed at int.
     *
     * @param token       the token
     * @param confirmedAt the confirmed at
     * @return the int
     */
    @Transactional
    @Modifying
    @Query("UPDATE ConfirmationToken c SET c.confirmedAt = ?2 WHERE c.token = ?1")
    int updateConfirmedAt(String token, LocalDateTime confirmedAt);
}
