package com.todo.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.todo")
public class ToDoApp {
    public static void main(String[] args) {
        SpringApplication.run(ToDoApp.class, args);
    }
}
