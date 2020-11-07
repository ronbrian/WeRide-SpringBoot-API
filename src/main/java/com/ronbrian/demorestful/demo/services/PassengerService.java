package com.ronbrian.demorestful.demo.services;

import com.ronbrian.demorestful.demo.repositories.PassengerRepository;
import com.ronbrian.demorestful.demo.repositories.UserRepository;
import com.ronbrian.demorestful.demo.entities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@Service
public class PassengerService {
    @Autowired
    public PassengerRepository passengerRepository;

    //Saving a new Passenger
    public Map<String, Object> save(Map<String, Object> request) {
//       FOR VALIDATION 👇🏻👇🏻
//        if(null==request.get("name")){
//            request.put("status","00");
//            request.put("message","User Cannot Be Blank !");
//            return request;
//
//        }else if(null ==request.get("email")) {
//            request.put("status","00");
//            request.put("message","email Cannot Be Blank !");
//            return request;
//
//        }else {

            String fname = (String) request.get("fname");
            String lname = (String) request.get("lname");
            String email = (String) request.get("email");
            int phone = (int) request.get("phone");
            String password = (String) request.get("password");
            int noOfTrips = (int) request.get("noOfTrips");
            int usertype = (int) request.get("usertype");
            Boolean isVerified = false;
            Boolean isActive = true;


            // if phone and email are valid
            //request.put("status", "00"); //status success
            request.put("data", "00");

            Passenger passenger = new Passenger();
            passenger.setFname(fname);
            passenger.setLname(lname);
            passenger.setEmail(email);
            passenger.setPhone(phone);
            passenger.setPassword(password);
            passenger.setNoOfTrips(noOfTrips);
            passenger.setUsertype(usertype);
            passenger.setVerified(isVerified);
            passenger.setActive(isActive);

        passengerRepository.save(passenger);
            return request;
        //}
    }

    //updating a passenger




    //Retrieving all Passengers
    public Map<String, Object> getPassengers() {
        Map<String, Object> map =new HashMap<>();

        List<Passenger> list = passengerRepository.findAll();
        List<Passenger> list2 = new ArrayList<>();
//        for (Passenger passenger : list) {
//
//            long id2 = passenger.getId();
//            if (id2>0){                        //return list of users whose id is more than 1
//                list2.add(passenger);
//            }
//        }

        map.put("status","ok");
        map.put("error",false);
        map.put("message","Passenger(s) have been found");
        map.put("data" ,list);
        return map;
    }


    public void disableUser(long id){
        Passenger passenger1 = passengerRepository.findById(id).orElse(null);
        passenger1.setActive(false);
        passengerRepository.save(passenger1);
    }

    public void enableUser(long id){
        Passenger passenger1 = passengerRepository.findById(id).orElse(null);
        passenger1.setActive(true);
        passengerRepository.save(passenger1);
    }

    public ResponseEntity<Map<String, String>> updatePassenger(long id, Passenger passenger){

        Map<String, String> resp = new HashMap<>();
        Passenger passenger1 = passengerRepository.findById(id).orElse(null);

        if (passenger1 == null){
            resp.put("state", "danger");
            resp.put("msg","id not found");
            return ResponseEntity.ok(resp);
        }
        passenger1.setFname(passenger.getFname());
        passenger1.setLname(passenger.getLname());
        passenger1.setPassword(passenger.getPassword());
        passenger1.setVerified(passenger.isVerified());
        passenger1.setActive(passenger.isActive());
        passenger1.setEmail(passenger.getEmail());

        //return nameService.updateTask(id,name,email,phone);

        passengerRepository.save(passenger1);
        resp.put("state", "success");
        resp.put("msg", "Passenger updated successfully");
        return ResponseEntity.ok(resp);
    }





}
