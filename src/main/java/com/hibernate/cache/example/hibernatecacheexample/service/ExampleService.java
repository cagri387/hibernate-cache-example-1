package com.hibernate.cache.example.hibernatecacheexample.service;

public interface ExampleService {
    int getNumberOfChildrenWithSessionFactory();

    int getNumberOfChildrenWithEntityManager();

    void getLazyParent();
}
