package com.ronbrian.demorestful.demo.entities;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table(name ="Reviews")
public class Reviews implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String reviewType; //P2D(Passenger to Driver) or D2P(Driver to Passenger)
    private int driverId;
    private int tripId;
    private int passengerId;
    private String reviewComment;
    private int rating;  //out of 5 Stars


    public long getReviewId() {
        return id;
    }

    public void setReviewId(int id) {
        this.id = id;
    }

    public int getDriverId() {
        return driverId;
    }

    public void setDriverId(int driverId) {
        this.driverId = driverId;
    }

    public int getTripId() {
        return tripId;
    }

    public void setTripId(int tripId) {
        this.tripId = tripId;
    }

    public int getPassengerId() {
        return passengerId;
    }

    public void setPassengerId(int passengerId) {
        this.passengerId = passengerId;
    }

    public String getReviewComment() {
        return reviewComment;
    }

    public void setReviewComment(String reviewComment) {
        this.reviewComment = reviewComment;
    }

    public int getRating() {
        return rating;
    }

    public String getReviewType() {
        return reviewType;
    }

    public void setReviewType(String reviewType) {
        this.reviewType = reviewType;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}
