package com.github.gustavovitor.erriaga;

import com.github.gustavovitor.erriaga.config.domain.APIConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@EnableConfigurationProperties(APIConfig.class)
@SpringBootApplication
public class ErriagaApplication {

    public static void main(String[] args) {
        SpringApplication.run(ErriagaApplication.class, args);
    }

}
