package com.hibernate.cache.example.hibernatecacheexample.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter @Setter
@Table(name = "parent")
public class Parent {

    @Id
    @Column(name = "id", unique = true, nullable = false)
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;

    @Column(name = "parent_name")
    private String name;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "parent")
    private Set<Child> childSet;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Parent)) return false;

        Parent parent = (Parent) o;

        return getName() != null ? getName().equals(parent.getName()) : parent.getName() == null;
    }

    @Override
    public int hashCode() {
        return getName() != null ? getName().hashCode() : 0;
    }
}