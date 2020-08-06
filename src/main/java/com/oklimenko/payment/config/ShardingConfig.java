package com.oklimenko.payment.config;

import lombok.SneakyThrows;
import org.apache.shardingsphere.shardingjdbc.api.ShardingDataSourceFactory;
import org.apache.shardingsphere.shardingjdbc.api.yaml.YamlShardingDataSourceFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import javax.sql.DataSource;
import java.io.File;

@Configuration
public class ShardingConfig {

    private static final String yamlFile = "sharding.yml";

    @SneakyThrows
    @Bean("dataSource")
    public DataSource dataSource() {
        File resource = new ClassPathResource(
                yamlFile).getFile();
        DataSource dataSource = YamlShardingDataSourceFactory.createDataSource(resource);
        return dataSource;
    }
}
