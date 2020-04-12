package com.hibernate.cache.example.hibernatecacheexample.repository;

import com.hibernate.cache.example.hibernatecacheexample.entity.Parent;

import java.util.List;

public interface ExampleRepository {

    List<Parent> retrieveParentsWithGirls();

    Parent retrieveParentWithId(int id);

    List<Parent> retrieveParentsWithGirlsWithEntityManager();

    Parent retrieveParentWithIdWithEntiyManager(int id);

}
