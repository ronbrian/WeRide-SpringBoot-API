package com.ronbrian.demorestful.demo.controllers;

import com.ronbrian.demorestful.demo.entities.Passenger;
import com.ronbrian.demorestful.demo.entities.Trips;
import com.ronbrian.demorestful.demo.repositories.PassengerRepository;
import com.ronbrian.demorestful.demo.repositories.TripsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.time.Instant;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



@RestController
public class TripsController {

    @Autowired
    public TripsRepository tripsRepository;


    //Creating a new Passenger
    @PostMapping(value = "/api/trip")
    public Map<String, Object> addPassenger(@Valid @RequestBody Map <String, Object> request) throws IOException {
        Map<String, Object> map =new HashMap<>();

        Trips trip = new Trips();
        long unixTime = Instant.now().getEpochSecond();

        String driverName = (String) request.get("driverName");
        String origin = (String) request.get("origin");
        String destination = (String) request.get("destination");
        String time = String.valueOf(unixTime);
        String passenger = (String) request.get("passenger");
        String passengerPhone = (String) request.get("passengerPhone");

        trip.setDriverName(driverName);
        trip.setOrigin(origin);
        trip.setDestination(destination);
        trip.setTime(time);
        trip.setPassenger(passenger);
        trip.setTripStatus(passengerPhone);

        tripsRepository.save(trip);
        Process process = Runtime.getRuntime().exec("xcrun simctl push FEB79038-C6DC-4060-A65E-1DBCEBB68414 co.ke.ron.We-Ride notification.apns");


        map.put("status","00");
        map.put("message", "Trip Saved ! ");

        return map;
    }

    //Retrieving all Passengers
    @GetMapping(value = "/api/trips/{drivername}")
    public Map<String, Object> getObject1(@PathVariable String drivername) {
        Map<String, Object> map =new HashMap<>();
        Map<String, Object> map2 =new HashMap<>();

        List<Trips> list = tripsRepository.findAll();

        int counter = 1;

        for (Trips trips : list) {
            String drivername2 = trips.getDriverName();
            if (drivername2.equals(drivername)){

                map2.put("" + counter, map);
                map.put("origin", trips.getOrigin());
                map.put("destination", trips.getDestination());
                map.put("time", trips.getTime());
                map.put("passenger", trips.getPassenger());
                map.put("passengerPhone", trips.getTripStatus());
                counter++;

            }else{
                counter = counter - 1;
//                map.put("status", "00");
//                map.put("for", drivername);

            }
        }

        return map;

    }


    @GetMapping(value = "/api/trips")
    public Map<String, Object> getAllTrips() {
        Map<String, Object> map =new HashMap<>();

        List<Trips> list = tripsRepository.findAll();

       map.put("total", tripsRepository.count());
       map.put("data", list);

        return map;

    }








}
