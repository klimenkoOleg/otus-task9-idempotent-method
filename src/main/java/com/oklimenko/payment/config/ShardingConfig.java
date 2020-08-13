package com.oklimenko.payment.config;

import lombok.SneakyThrows;
import org.apache.shardingsphere.shardingjdbc.api.ShardingDataSourceFactory;
import org.apache.shardingsphere.shardingjdbc.api.yaml.YamlShardingDataSourceFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.util.FileCopyUtils;

import javax.sql.DataSource;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UncheckedIOException;

import static java.nio.charset.StandardCharsets.UTF_8;

@Configuration
public class ShardingConfig {

//    private static final String yamlFile = "sharding.yml";

    @Value("classpath:sharding-processing.yml")
    private Resource resource;

    @SneakyThrows
    @Bean("dataSource")
    public DataSource dataSource() {
        String shardingYamlContent = asString(resource);
        System.out.println("SHARDING!!!!!");
        System.out.println("SHARDING!!!!!");
        System.out.println("SHARDING!!!!!");
        System.out.println("SHARDING!!!!!");
        System.out.println(shardingYamlContent);
        DataSource dataSource = YamlShardingDataSourceFactory.createDataSource(shardingYamlContent.getBytes(UTF_8));
        return dataSource;

//        resource.getInputStream().
//        File resource = new ClassPathResource(
//                yamlFile).getFile();
//        DataSource dataSource = YamlShardingDataSourceFactory.createDataSource(resource);
//        return dataSource;
    }

    private static String asString(Resource resource) {
        try (Reader reader = new InputStreamReader(resource.getInputStream(), UTF_8)) {
            return FileCopyUtils.copyToString(reader);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }
}
