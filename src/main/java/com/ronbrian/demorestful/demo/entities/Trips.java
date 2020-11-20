package com.ronbrian.demorestful.demo.entities;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table(name ="Trips")
public class Trips implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;
    private int vehicleID;
    private int driverID;
    private int requestID;
    private String origin;
    private double originLatitude;
    private double originLongitude;
    private String destination;
    private double destinationLatitude;
    private double destinationLongitude;
    private int distance;
    private String tripStatus;
    private String tripDuration; //in Minutes
    private String passengers;
    private String price;


    public long getTripId() {
        return id;
    }

    public void setTripId(long id) {
        this.id = id;
    }

    public int getVehicleID() {
        return vehicleID;
    }

    public void setVehicleID(int vehicleID) {
        this.vehicleID = vehicleID;
    }

    public int getDriverID() {
        return driverID;
    }

    public void setDriverID(int driverID) {
        this.driverID = driverID;
    }

    public int getRequestID() {
        return requestID;
    }

    public void setRequestID(int requestID) {
        this.requestID = requestID;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public double getOriginLatitude() {
        return originLatitude;
    }

    public void setOriginLatitude(double originLatitude) {
        this.originLatitude = originLatitude;
    }

    public double getOriginLongitude() {
        return originLongitude;
    }

    public void setOriginLongitude(double originLongitude) {
        this.originLongitude = originLongitude;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public double getDestinationLatitude() {
        return destinationLatitude;
    }

    public void setDestinationLatitude(double destinationLatitude) {
        this.destinationLatitude = destinationLatitude;
    }

    public double getDestinationLongitude() {
        return destinationLongitude;
    }

    public void setDestinationLongitude(double destinationLongitude) {
        this.destinationLongitude = destinationLongitude;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public String getTripStatus() {
        return tripStatus;
    }

    public void setTripStatus(String tripStatus) {
        this.tripStatus = tripStatus;
    }

    public String getTripDuration() {
        return tripDuration;
    }

    public void setTripDuration(String tripDuration) {
        this.tripDuration = tripDuration;
    }

    public String getPassengers() {
        return passengers;
    }

    public void setPassengers(String passengers) {
        this.passengers = passengers;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
