package com.hibernate.cache.example.hibernatecacheexample;

import com.hibernate.cache.example.hibernatecacheexample.service.ExampleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class DriverBean {

    @Autowired
    private ExampleService exampleService;

    @PostConstruct
    public void runExample() {
        System.out.println("Result (with SessionFactory): " + exampleService.getNumberOfChildrenWithSessionFactory());
        System.out.println("Result (with EntityManager): " + exampleService.getNumberOfChildrenWithEntityManager());
    }
}
