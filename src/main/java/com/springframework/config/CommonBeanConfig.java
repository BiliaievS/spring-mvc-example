package com.springframework.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.jasypt.util.password.StrongPasswordEncryptor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Created by sbiliaiev on 06/09/17.
 */
@Configuration
@EnableJpaRepositories("com.springframework.repositories")
public class CommonBeanConfig {

    @Bean
    public StrongPasswordEncryptor strongEncryptor() {
        StrongPasswordEncryptor encryptor = new StrongPasswordEncryptor();
        return encryptor;
    }
}
