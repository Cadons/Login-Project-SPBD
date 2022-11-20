package ch.supsi.spbd.authSystem.model;

import org.hibernate.annotations.Entity;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


@javax.persistence.Entity
public class Customer {
    @Id
    @GeneratedValue
    private int id;
    @Column
    private String name;
    @Column
    private String surname;
    public Customer(){

    }
    public int getId() {
        return id;
    }

        public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surnname) {
        this.surname = surnname;
    }
}
