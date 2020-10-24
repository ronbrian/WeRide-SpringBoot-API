package com.ronbrian.demorestful.demo.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name ="Passenger")

public class Passenger implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long passengerId;
    private String fname;
    private String lname;
    private String email;
    private int phone;
    private String password;
    private int noOfTrips;
    private int usertype;
    private boolean isVerified;
    private boolean isActive;




    public long getPassengerId() {
        return passengerId;
    }

    public void setPassengerId(long passengerId) {
        this.passengerId = passengerId;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getNoOfTrips() {
        return noOfTrips;
    }

    public void setNoOfTrips(int noOfTrips) {
        this.noOfTrips = noOfTrips;
    }

    public int getUsertype() {
        return usertype;
    }

    public void setUsertype(int usertype) {
        this.usertype = usertype;
    }

    public boolean isVerified() {
        return isVerified;
    }

    public void setVerified(boolean verified) {
        isVerified = verified;
    }

    public boolean isActive() { return isActive; }

    public void setActive(boolean active) {
        isActive = active;
    }
}
