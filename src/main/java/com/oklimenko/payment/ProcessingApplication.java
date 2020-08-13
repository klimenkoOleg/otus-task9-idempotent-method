package com.oklimenko.payment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

@EnableResourceServer
@EnableJpaRepositories("com.oklimenko.payment.repository")
@EntityScan("com.oklimenko.payment.model")
@SpringBootApplication(scanBasePackages = "com.oklimenko")
public class ProcessingApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(ProcessingApplication.class, args);
    }

}