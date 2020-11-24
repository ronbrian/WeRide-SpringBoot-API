package com.ronbrian.demorestful.demo.services;


import com.ronbrian.demorestful.demo.entities.Reviews;
import com.ronbrian.demorestful.demo.repositories.ReviewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.*;

@RestController
@Service
public class ReviewsService {

    @Autowired
    public ReviewsRepository reviewsRepository;

    public Map<String, Object> save(Map<String, Object> request) {

        int driverId = (int) request.get("driverId");
        int tripId = (int) request.get("tripId");
        int passengerId = (int) request.get("passengerId");
        String reviewComment = (String) request.get("reviewComment");
        int rating = (int) request.get("rating");
        String reviewType = (String) request.get("reviewType");
        
        request.put("data", "00");

        
        
        Reviews review = new Reviews();
        review.setDriverId(driverId);
        review.setTripId(tripId);
        review.setPassengerId(passengerId);
        review.setReviewComment(reviewComment);
        review.setRating(rating);
        review.setReviewType(reviewType);
        reviewsRepository.save(review);
        return request;

    }

    public Map<String, Object> getReviews() {
        Map<String, Object> map =new HashMap<>();
        List<Reviews> list = reviewsRepository.findAll();
        List<Reviews> list2 = new ArrayList<>();

        map.put("status","ok");
        map.put("error",false);
        map.put("message","Driver(s) have been found");
        map.put("data" ,list);
        return map;
    }






}
