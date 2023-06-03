package com.cursework.ehelthcare.email;

/**
 * The interface Email sender.
 */
public interface EmailSender {
    /**
     * Send.
     *
     * @param to    the to
     * @param email the email
     */
    void send(String to,String email);
}
