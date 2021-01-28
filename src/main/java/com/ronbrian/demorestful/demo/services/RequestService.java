package com.ronbrian.demorestful.demo.services;

import com.ronbrian.demorestful.demo.entities.Passenger;
import com.ronbrian.demorestful.demo.entities.Requests;
import com.ronbrian.demorestful.demo.repositories.RequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@Service
public class RequestService {

    @Autowired
    public RequestRepository requestRepository;

    //Saving a new Request
    public Map<String, Object> save(Map<String, Object> request) {
        int tripID = (int) request.get("tripID");
        int passengerID = (int) request.get("passengerID");
        int driverID = (int) request.get("driverID");
        String origin = (String) request.get("origin");
        double originLatitude = (double) request.get("originLatitude");
        double originLongitude = (double) request.get("originLongitude");
        String destination = (String) request.get("destination");
        double destinationLatitude = (double) request.get("destinationLatitude");
        double destinationLongitude = (double) request.get("destinationLongitude");
        int status = (int) request.get("status");
        int requestValidityTime = (int) request.get("requestValidityTime");
        int tripStatus = (int) request.get("tripStatus");
        int tripDuration = (int) request.get("tripDuration");
        int availableSeats = (int) request.get("availableSeats");
        int price = (int) request.get("price");

        Requests quest = new Requests();

        quest.setTripID(tripID);
        quest.setDriverID(driverID);
        quest.setOrigin(origin);
        quest.setOriginLatitude(originLatitude);
        quest.setOriginLongitude(originLongitude);
        quest.setDestination(destination);
        quest.setDestinationLatitude(destinationLatitude);
        quest.setDestinationLongitude(destinationLongitude);
        quest.setStatus(status);
        quest.setRequestValidityTime(requestValidityTime);
        quest.setTripStatus(tripStatus);
        quest.setTripDuration(tripDuration);
        quest.setAvailableSeats(availableSeats);


        request.put("data", "00");
        requestRepository.save(quest);
        return request;
    }

    //Retrieving passenger by ID
    public Map<String, Object> retrieveRequest(long id) {
        Map<String, Object> map = new HashMap<>();
        Optional<Requests> name = requestRepository.findById(id);


        if (!name.isPresent()) {

            map.put("status", "00");
            map.put("message", "Request with that ID does not exist ! ");

        } else {
            map.put("status", "01");
            map.put("message", "success");

            map.put("data", name);
        }

        return map;
    }


    //Retrieving all Requests
    public Map<String, Object> getRequests() {
        Map<String, Object> map = new HashMap<>();

        List<Requests> list = requestRepository.findAll();
        List<Requests> list2 = new ArrayList<>();
//        for (Passenger passenger : list) {
//
//            long id2 = passenger.getId();
//            if (id2>0){                        //return list of users whose id is more than 1
//                list2.add(passenger);
//            }
//        }

        map.put("status", "ok");
        map.put("error", false);
        map.put("message", "Request(s) have been found");
        map.put("data", list);
        return map;
    }

    //Updating Request
    public ResponseEntity<Map<String, String>> updateRequest(long id, Requests quest) {

        Map<String, String> resp = new HashMap<>();
        Requests requests1 = requestRepository.findById(id).orElse(null);

        if (requests1 == null) {
            resp.put("state", "danger");
            resp.put("msg", "id not found");
            return ResponseEntity.ok(resp);
        }

        requests1.setTripID(quest.getTripID());
        requests1.setDriverID(quest.getDriverID());
        requests1.setOrigin(quest.getOrigin());
        requests1.setOriginLatitude(quest.getOriginLatitude());
        requests1.setOriginLongitude(quest.getOriginLongitude());
        requests1.setDestination(quest.getDestination());
        requests1.setDestinationLatitude(quest.getDestinationLatitude());
        requests1.setDestinationLongitude(quest.getDestinationLongitude());
        requests1.setStatus(quest.getStatus());
        requests1.setRequestValidityTime(quest.getRequestValidityTime());
        requests1.setTripStatus(quest.getTripStatus());
        requests1.setTripDuration(quest.getTripDuration());
        requests1.setAvailableSeats(quest.getAvailableSeats());

        requestRepository.save(quest);
        resp.put("state", "success");
        resp.put("msg", "Request updated successfully");
        return ResponseEntity.ok(resp);

    }


}
