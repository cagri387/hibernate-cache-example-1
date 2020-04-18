package com.hibernate.cache.example.hibernatecacheexample.repository;

import com.hibernate.cache.example.hibernatecacheexample.entity.Child;
import com.hibernate.cache.example.hibernatecacheexample.entity.Parent;
import lombok.RequiredArgsConstructor;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.criteria.*;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ExampleRepositoryImpl implements ExampleRepository {

    private final SessionFactory sessionFactory;

    private final EntityManager entityManager;

    @Override
    public List<Parent> retrieveParentsWithGirls() {
        CriteriaBuilder builder = sessionFactory.getCriteriaBuilder();
        CriteriaQuery<Parent> criteria = builder.createQuery(Parent.class);
        Root<Parent> parentRoot = criteria.from(Parent.class);
        Fetch<Parent, Child> fetchChildren = parentRoot.fetch("childSet", JoinType.LEFT);
        Join<Parent, Child> joinChildren = (Join<Parent, Child>) fetchChildren;
        criteria.where(builder.equal(joinChildren.get("sex"), "girl"));
        criteria.distinct(true);

        return sessionFactory.getCurrentSession().createQuery(criteria).getResultList();
    }

    @Override
    public Parent retrieveParentWithId(int id) {
        CriteriaBuilder builder = sessionFactory.getCriteriaBuilder();
        CriteriaQuery<Parent> criteria = builder.createQuery(Parent.class);
        Root<Parent> parentRoot = criteria.from(Parent.class);
        parentRoot.fetch("childSet", JoinType.LEFT);
        criteria.where(builder.equal(parentRoot.get("id"), id));

        return sessionFactory.getCurrentSession().createQuery(criteria).getSingleResult();
    }

    @Override
    public List<Parent> retrieveParentsWithGirlsWithEntityManager() {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Parent> criteria = builder.createQuery(Parent.class);
        Root<Parent> parentRoot = criteria.from(Parent.class);
        Fetch<Parent, Child> fetchChildren = parentRoot.fetch("childSet", JoinType.LEFT);
        Join<Parent, Child> joinChildren = (Join<Parent, Child>) fetchChildren;
        criteria.where(builder.equal(joinChildren.get("sex"), "girl"));

        //criteria.where(builder.equal(parentRoot.get("id"), 1));
        criteria.distinct(true);

        return entityManager.createQuery(criteria).getResultList();
    }

    @Override
    public Parent retrieveParentWithIdWithEntiyManager(int id) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Parent> criteria = builder.createQuery(Parent.class);
        Root<Parent> parentRoot = criteria.from(Parent.class);
        parentRoot.fetch("childSet", JoinType.LEFT);
        criteria.where(builder.equal(parentRoot.get("id"), id));

        return entityManager.createQuery(criteria).getSingleResult();
    }

    @Override
    public List<Child> retrieveChildrenByParentId(int id) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Child> criteria = builder.createQuery(Child.class);
        Root<Child> parentRoot = criteria.from(Child.class);
        Join<Child, Parent> joinParent = parentRoot.join("parent", JoinType.INNER);
        criteria.where(builder.equal(joinParent.get("id"), id));

        return entityManager.createQuery(criteria).getResultList();
    }

    @Override
    public Parent loadParent(int id) {
        return sessionFactory.getCurrentSession().load(Parent.class, id);
    }
}
