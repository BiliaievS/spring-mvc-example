package com.springframework.cionfig;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ActiveProfiles;

/**
 * Created by sbiliaiev on 02/09/17.
 */
@Configuration
@EnableAutoConfiguration
@ComponentScan("com.springframework")
@ActiveProfiles("jpadao")
public class JPAIntegrationConfig {
}
