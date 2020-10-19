package com.explore.galaxy;

import com.explore.galaxy.basic.modules.category.controller.CategoryController;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Field;

@SpringBootApplication
//@EnableCaching
@MapperScan(value = {"com.explore.galaxy.basic.modules.*.dao"})
//@PropertySource(value = {"classpath:druid.properties"}, encoding = "utf-8")
public class GalaxyApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(GalaxyApplication.class, args);
//        String[] beanNamesForAnnotation = run.getBeanNamesForAnnotation(Autowired.class);
//        for(String str: beanNamesForAnnotation)
//        {
//            System.out.println(str);
//        }
    }

}
