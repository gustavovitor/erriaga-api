package com.github.gustavovitor.erriaga;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class ErriagaApplication {

    public static void main(String[] args) {
        SpringApplication.run(ErriagaApplication.class, args);
    }

}
