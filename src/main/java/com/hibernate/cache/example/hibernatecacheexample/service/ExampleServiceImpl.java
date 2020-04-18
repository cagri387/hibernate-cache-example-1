package com.hibernate.cache.example.hibernatecacheexample.service;

import com.hibernate.cache.example.hibernatecacheexample.entity.Child;
import com.hibernate.cache.example.hibernatecacheexample.entity.Parent;
import com.hibernate.cache.example.hibernatecacheexample.repository.ExampleRepository;
import lombok.RequiredArgsConstructor;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ExampleServiceImpl implements ExampleService {

    private final ExampleRepository exampleRepository;

    private final SessionFactory sessionFactory;

    private final EntityManager entityManager;

    @Override
    @Transactional(transactionManager = "customTransactionManager", readOnly = true)
    public int getNumberOfChildrenWithSessionFactory() {
        //just for adding parents to session (cache)
        List<Parent> parentList = exampleRepository.retrieveParentsWithGirls();

        //sessionFactory.getCurrentSession().clear();
        Parent parent = exampleRepository.retrieveParentWithId(1);

        return parent.getChildSet().size();
    }

    @Override
    @Transactional
    public int getNumberOfChildrenWithEntityManager() {
        //just for adding parents to session (cache)
        List<Parent> parentList = exampleRepository.retrieveParentsWithGirlsWithEntityManager();

        //entityManager.clear();
        Parent parent = exampleRepository.retrieveParentWithIdWithEntiyManager(1);

        List<Child> childList = exampleRepository.retrieveChildrenByParentId(1);

        return childList.size();
    }

    @Override
    @Transactional(readOnly = true)
    public void getLazyParent() {
        Parent parent = exampleRepository.loadParent(1);
        Parent anotherParent = exampleRepository.loadParent(2);
        anotherParent.setName("Cemalettin");


        if(anotherParent.equals(parent)) {
            System.out.println("parents names are equal");
        }
    }
}
