package com.ronbrian.demorestful.demo.controllers;


import com.ronbrian.demorestful.demo.entities.DriverLiveLocation;
import com.ronbrian.demorestful.demo.entities.Passenger;
import com.ronbrian.demorestful.demo.repositories.DriverLiveLocationRepository;
import com.ronbrian.demorestful.demo.services.DriverLiveLocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

@RestController
public class DriverLiveLocationController {

    @Autowired
    public DriverLiveLocationRepository driverLiveLocationRepository;

    @Autowired
    public DriverLiveLocationService driverLiveLocationService;

    //Creating a new Request
    @PostMapping(value = "/api/drivers/location")
    public Map<String, Object> add_DriverLiveLocation(@Valid @RequestBody Map <String, Object> request) {
        return driverLiveLocationService.initialsave(request);
    }

    @PutMapping("/api/drivers/location/{id}")
    public ResponseEntity<Map<String, String>> updateDriverLiveLocation(@PathVariable long id, @RequestBody DriverLiveLocation driverLiveLocation) {
        return driverLiveLocationService.updateDriverLocation(id, driverLiveLocation);
    }





}
