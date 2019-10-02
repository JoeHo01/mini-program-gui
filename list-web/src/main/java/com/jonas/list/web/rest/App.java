package com.jonas.list.web.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
@ServletComponentScan
public class App {

    private static ConfigurableApplicationContext applicationContext;

    public static void main(String[] args) {
        applicationContext = SpringApplication.run(App.class, args);
    }

    //get applicationContext
    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    //get bean by class
    public static <T> T getBean(Class<T> clazz){
        return applicationContext.getBean(clazz);
    }
}