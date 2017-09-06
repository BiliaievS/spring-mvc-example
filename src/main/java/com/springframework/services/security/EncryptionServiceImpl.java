package com.springframework.services.security;

import org.jasypt.util.password.StrongPasswordEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by sbiliaiev on 06/09/17.
 */
@Service
public class EncryptionServiceImpl implements EncryptionService {

    private StrongPasswordEncryptor encryptor;

    @Autowired
    public void setEncryptor(StrongPasswordEncryptor encryptor) {
        this.encryptor = encryptor;
    }

    @Override
    public String encryptString(String pass) {
        return encryptor.encryptPassword(pass);
    }

    @Override
    public boolean checkPassword(String plainPassword, String encryptedPassword) {
        return encryptor.checkPassword(plainPassword, encryptedPassword);
    }
}
