package com.ronbrian.demorestful.demo.controllers;

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
    public Map<String, Object> getObject1() {
        return driverService.getDrivers();
    }






}
