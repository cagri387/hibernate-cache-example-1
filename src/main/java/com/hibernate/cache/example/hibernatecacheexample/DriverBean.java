package com.hibernate.cache.example.hibernatecacheexample;

import com.hibernate.cache.example.hibernatecacheexample.service.ExampleService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DriverBean implements CommandLineRunner {

    private final ExampleService exampleService;

    @Override
    public void run(String... args) {
        System.out.println("Result (with SessionFactory): " + exampleService.getNumberOfChildrenWithSessionFactory());
        System.out.println("Result (with EntityManager): " + exampleService.getNumberOfChildrenWithEntityManager());
    }
}
