package com.ronbrian.demorestful.demo.controllers;

import com.ronbrian.demorestful.demo.entities.Driver;
import com.ronbrian.demorestful.demo.entities.Passenger;
import com.ronbrian.demorestful.demo.entities.User;
import com.ronbrian.demorestful.demo.repositories.DriverRepository;
import com.ronbrian.demorestful.demo.repositories.PassengerRepository;
import com.ronbrian.demorestful.demo.repositories.UserRepository;
import com.ronbrian.demorestful.demo.services.DriverService;
import com.ronbrian.demorestful.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


import javax.validation.Valid;
import java.util.Map;


@RestController
public class DriverController {
    @Autowired
    public DriverRepository driverRepository;

    @Autowired
    public DriverService driverService;

    //Creating new Driver
    @PostMapping(value = "/api/driver")
    public Map<String, Object> addDriver(@Valid @RequestBody Map <String, Object> request) {
        return driverService.save(request);
    }

    //Retrieving all Drivers
    @GetMapping(value = "/api/drivers")
    public Map<String, Object> getAllDrivers() {
        return driverService.getDrivers();
    }


    //Retrieving passenger by ID
     @GetMapping(value = "/api/drivers/{id}")
    public Map<String, Object> retrieveDriver(@PathVariable long id){
        return driverService.retrieveDriver(id);
    }



    //    //Updating User attributes by id -- passing id as path
//    //Before Deleting , you must get user details the save them as a json

    @PutMapping("/api/driver/update/{id}")
    public ResponseEntity<Map<String, String>> updateDriver(@PathVariable long id, @RequestBody Driver driver) {
        return driverService.updateDriver(id, driver);
    }


//
//    //Disabling user record "Deleting" - Changing isActive
    @PutMapping("/api/driver/disable/{id}")
    public void hideDriver(@PathVariable long id) {
        driverService.disableDriver(id);
    }

    @PutMapping("/api/driver/enable/{id}")
    public void showDriver(@PathVariable long id) {
        driverService.enableDriver(id);
    }

//
//    //deleting name by id
//    @DeleteMapping(value = "/api/passenger/delete/{id}")
//    public void deleteUser(@PathVariable long id) {
//        passengerRepository.deleteById(id);
//    }
//






}
