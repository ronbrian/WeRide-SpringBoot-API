package com.ronbrian.demorestful.demo.services;


import com.ronbrian.demorestful.demo.entities.DriverLiveLocation;
import com.ronbrian.demorestful.demo.entities.Reviews;
import com.ronbrian.demorestful.demo.entities.User;
import com.ronbrian.demorestful.demo.repositories.DriverLiveLocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Time;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

@RestController
@Service
public class DriverLiveLocationService {

    @Autowired
    public DriverLiveLocationRepository driverLiveLocationRepository;


    public Map<String, Object> initialsave(Map<String, Object> request) {

        int driverId = (int) request.get("driverId");
        double lat = (double) request.get("lat");
        double lng = (double) request.get("lng");

        //Getting the current time in unix timestamp
        long unixTime = Instant.now().getEpochSecond();

        DriverLiveLocation driverLiveLocation = new DriverLiveLocation();

        driverLiveLocation.setDriverID(driverId);
        driverLiveLocation.setLat(lat);
        driverLiveLocation.setLng(lng);
        driverLiveLocation.setTime(unixTime);
        driverLiveLocationRepository.save(driverLiveLocation);
        request.put("data", "00");
        return request;

    }

    //Create for update
    public ResponseEntity<Map<String, String>> updateDriverLocation(long driverId, DriverLiveLocation driverLiveLocation){

        Map<String, String> resp = new HashMap<>();
        DriverLiveLocation driverLiveLocation1 = driverLiveLocationRepository.findById(driverId).orElse(null);

        if (driverLiveLocation1 == null){
            resp.put("state", "danger");
            resp.put("msg","id not found");
            return ResponseEntity.ok(resp);
        }
        driverLiveLocation1.setDriverID(driverLiveLocation.getDriverID());
        driverLiveLocation1.setLng(driverLiveLocation.getLng());
        driverLiveLocation1.setLat(driverLiveLocation.getLat());
        long unixTime = Instant.now().getEpochSecond();
        driverLiveLocation1.setTime(unixTime);

        driverLiveLocationRepository.save(driverLiveLocation1);
        resp.put("state", "success");
        resp.put("msg", "DriverLiveLocation updated successfully");
        return ResponseEntity.ok(resp);
    }

}
