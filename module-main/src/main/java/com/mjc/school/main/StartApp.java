package com.mjc.school.main;

import com.mjc.school.controller.app.App;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootApplication
public class StartApp {
    public static void main(String[] args) {
//        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
//        App app = context.getBean(App.class);
//        app.startApp();

        ConfigurableApplicationContext ctx = SpringApplication.run(StartApp.class);
        App app = ctx.getBean(App.class);
        app.startApp();
    }
}
