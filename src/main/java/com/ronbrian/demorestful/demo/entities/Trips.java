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
    private String driverName;
    private int requestID;
    private String origin;
    private double originLatitude;
    private double originLongitude;
    private String destination;
    private double destinationLatitude;
    private double destinationLongitude;
    private int distance;
    private String tripStatus;
    private String time; //in Minutes
    private String passenger;


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

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverID) {
        this.driverName = driverID;
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

    public String getTime() {
        return time;
    }

    public void setTime(String tripDuration) {
        this.time = tripDuration;
    }

    public String getPassenger() {
        return passenger;
    }

    public void setPassenger(String passengers) {
        this.passenger = passengers;
    }

}
