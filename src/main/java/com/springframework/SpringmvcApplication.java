package com.springframework;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class SpringmvcApplication {

	public static void main(String[] args) {
		ApplicationContext cnxt = SpringApplication.run(SpringmvcApplication.class, args);

		System.out.println(cnxt.getBeanDefinitionCount());
	}
}
