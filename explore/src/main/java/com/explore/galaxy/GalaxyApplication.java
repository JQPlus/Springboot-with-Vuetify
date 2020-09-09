package com.explore.galaxy;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
//@EnableCaching
@MapperScan(value = {"com.explore.galaxy.basic.modules.*.dao"})
//@PropertySource(value = {"classpath:druid.properties"}, encoding = "utf-8")
public class GalaxyApplication {

    public static void main(String[] args) {
        SpringApplication.run(GalaxyApplication.class, args);
    }

}
