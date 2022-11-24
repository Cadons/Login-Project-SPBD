package ch.supsi.spbd.authSystem.model;

import org.hibernate.annotations.Entity;

import javax.persistence.*;
import java.util.List;


@javax.persistence.Entity
@Table(name = "customers")
public class Customer {
    @Id
    @GeneratedValue
    private Long id;
    @Column
    private String name;
    @Column
    private String surname;
    private String userID;
    @OneToMany(mappedBy = "customer")
    private List<Order> orders;
    public Customer(){

    }
    public long getId() {
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

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

}
