package com.ronbrian.demorestful.demo.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name ="mytodo")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;
    private String name;
    private String email;
    private String userId;
    private String location;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String task) {
        this.name = task;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String desc) {
        this.email = desc;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
