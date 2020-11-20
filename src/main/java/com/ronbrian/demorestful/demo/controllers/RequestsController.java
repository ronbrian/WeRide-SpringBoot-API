package com.ronbrian.demorestful.demo.controllers;

import com.ronbrian.demorestful.demo.entities.Requests;
import com.ronbrian.demorestful.demo.repositories.RequestRepository;
import com.ronbrian.demorestful.demo.services.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

@RestController
public class RequestsController {


    @Autowired
    public RequestRepository requestRepository;

    @Autowired
    public RequestService requestService;

    //Creating a new Request
    @PostMapping(value = "/api/request")
    public Map<String, Object> add_Request(@Valid @RequestBody Map <String, Object> request) {
        return requestService.save(request);
    }

    //Retrieving request by ID
    @GetMapping(value = "/api/request/{id}")
    public Map<String, Object> retrieve_Request(@PathVariable long id){
        return requestService.retrieveRequest(id);
    }

    //Retrieving all Request
    @GetMapping(value = "/api/requests")
    public Map<String, Object> getAllRequests() {
        return requestService.getRequests();
    }

    @PutMapping("/api/request/update/{id}")
    public ResponseEntity<Map<String, String>> updateRequest(@PathVariable long id, @RequestBody Requests request) {
        return requestService.updateRequest(id, request);
    }





}
