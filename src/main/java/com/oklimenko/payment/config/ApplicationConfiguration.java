package com.oklimenko.payment.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;

@Validated
@Configuration
@ConfigurationProperties(prefix = "processing-app")
//@PropertySource(value = "${application.properties.path}", factory = PropertySourceFactoryCustom.class)
@Data // lombok
public class ApplicationConfiguration {
    private String cacheEnabled;
}
