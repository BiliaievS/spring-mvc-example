package com.springframework.services.security;

/**
 * Created by sbiliaiev on 06/09/17.
 */
public interface EncryptionService {
    String encryptString(String input);
    boolean checkPassword(String plainPassword, String encryptedPassword);
}
