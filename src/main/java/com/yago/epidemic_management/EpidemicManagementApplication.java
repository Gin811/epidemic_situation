package com.yago.epidemic_management;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@MapperScan("com.yago.epidemic_management.model.dao")
public class EpidemicManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(EpidemicManagementApplication.class, args);
    }

}
