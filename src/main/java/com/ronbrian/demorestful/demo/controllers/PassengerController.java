    package com.ronbrian.demorestful.demo.controllers;

    import com.ronbrian.demorestful.demo.entities.Passenger;
    import com.ronbrian.demorestful.demo.entities.User;
    import com.ronbrian.demorestful.demo.repositories.PassengerRepository;
    import com.ronbrian.demorestful.demo.repositories.UserRepository;
    import com.ronbrian.demorestful.demo.services.PassengerService;
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
    public class PassengerController {

    @Autowired
    public PassengerRepository passengerRepository;

    @Autowired
    public PassengerService passengerService;

    //Creating a new Passenger
    @PostMapping(value = "/api/passenger")
    public Map<String, Object> addPassenger(@Valid @RequestBody Map <String, Object> request) {
        return passengerService.save(request);
    }

    //Retrieving all Passengers
    @GetMapping(value = "/api/passengers")
    public Map<String, Object> getObject1() {
        return passengerService.getPassengers();
    }


    //Updating User attributes by id -- passing id as path
        //Before Deleting , you must get usewr details the save them as a json
    @PutMapping("/api/passenger/update/{id}")
    public ResponseEntity<Map<String, String>> updatePassenger(@PathVariable long id, @RequestBody Passenger passenger) {
        return passengerService.updatePassenger(id, passenger);

    }


    }
