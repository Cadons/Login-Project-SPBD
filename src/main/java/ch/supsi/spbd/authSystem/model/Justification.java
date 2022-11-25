package ch.supsi.spbd.authSystem.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.List;


@javax.persistence.Entity
@Table(name = "justifications")
public class Justification {

    @Id
    @GeneratedValue
    private Long id;
    @Column
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date;
    @Column(columnDefinition = "TEXT")
    private String description;
    @Column
    private String userID;
    @Column
    private Status status=Status.NOT_JUSTIFIED;

    public Justification(){

    }

    public Long getId() {
        return id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }
}
