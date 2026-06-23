package org.joaocorrea;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;

import java.util.List;

@Entity
public class Person extends PanacheEntity {

    public String name;
    public int yearOfBirth;

    public static List<Person> findByYearOfBirth(int yearOfBirth) {
        return find("yearOfBirth", yearOfBirth).list();
    }
}
