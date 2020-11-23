package com.ronbrian.demorestful.demo.controllers;


import com.ronbrian.demorestful.demo.repositories.ReviewsRepository;
import com.ronbrian.demorestful.demo.services.DriverService;
import com.ronbrian.demorestful.demo.services.ReviewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Map;

@RestController
public class ReviewsController {

    @Autowired
    public ReviewsRepository reviewsRepository;

    @Autowired
    public ReviewsService reviewsService;

    //Creating a new Review
    @PostMapping(value = "/api/review")
    public Map<String, Object> add_Review(@Valid @RequestBody Map <String, Object> request) {
        return reviewsService.save(request);
    }

    //Get all Reviews


    //Get all reviews about this driver -- Reviews for a driver written by a passenger -- containing his driverId

    //Get all reviews about this Passenger -- Reviews for a passenger written by driver -- containing his passengerId

    //Get all Reviews written by this {id} Passenger --

    //Get all Reviews written by this {id} Driver --




}
