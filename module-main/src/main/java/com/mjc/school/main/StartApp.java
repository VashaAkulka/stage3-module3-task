package com.mjc.school.main;

import com.mjc.school.controller.app.App;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@EntityScan("com.mjc.school.repository.*")
@ComponentScan("com.mjc.school.*")
@PropertySource("file:application.properties")
public class StartApp {
    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(StartApp.class, args);
        App app = context.getBean(App.class);
        app.startApp();
    }
}
