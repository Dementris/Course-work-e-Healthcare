package com.cursework.ehelthcare.registration.token;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

/**
 * The type Confirmation token service.
 */
@Service
@AllArgsConstructor
public class ConfirmationTokenService {

    private final ConfirmationTokenRepository confirmationTokenRepository;

    /**
     * Save confirmation token.
     *
     * @param token the token
     */
    public  void saveConfirmationToken(ConfirmationToken token){
        confirmationTokenRepository.save(token);
    }

    /**
     * Get token optional.
     *
     * @param token the token
     * @return the optional
     */
    public Optional<ConfirmationToken> getToken(String token){
        return confirmationTokenRepository.findByToken(token);
    }

    /**
     * Set confirmed at int.
     *
     * @param token the token
     * @return the int
     */
    public int setConfirmedAt(String token){
        return confirmationTokenRepository.updateConfirmedAt(token, LocalDateTime.now());
    }

}
