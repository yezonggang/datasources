package com.example.datasources;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication(exclude={
        DataSourceAutoConfiguration.class,
        DataSourceTransactionManagerAutoConfiguration.class,
})
/*
@EnableConfigurationProperties(MppDataSourceProperties.class)
*/
public class DatasourcesApplication {

    public static void main(String[] args) {
        SpringApplication.run(DatasourcesApplication.class, args);
    }

}
