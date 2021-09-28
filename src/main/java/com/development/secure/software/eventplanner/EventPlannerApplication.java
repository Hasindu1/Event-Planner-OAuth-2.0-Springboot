package com.development.secure.software.eventplanner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;


@EnableAutoConfiguration (exclude = {ErrorMvcAutoConfiguration.class})
@SpringBootApplication
public class EventPlannerApplication {

    public static void main(String[] args) {
        SpringApplication.run(EventPlannerApplication.class, args);
    }


}
