package com.ronbrian.demorestful.demo.services;

import com.ronbrian.demorestful.demo.repositories.DriverRepository;
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
public class DriverService {
    @Autowired
    public DriverRepository driverRepository;

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
        String vehicleID = (String) request.get("vehicleID");
        int noOfTrips = (int) request.get("noOfTrips");
        int usertype = (int) request.get("usertype");
        Boolean isVerified = false;
        Boolean isActive = true;


        // if phone and email are valid
        //request.put("status", "00"); //status success
        request.put("data", "00");

        Driver driver = new Driver();
        driver.setFname(fname);
        driver.setLname(lname);
        driver.setEmail(email);
        driver.setPhone(phone);
        driver.setPassword(password);
        driver.setVehicleID(vehicleID);
        driver.setNoOfTrips(noOfTrips);
        driver.setUsertype(usertype);
        driver.setActive(true);
        driver.setVerified(isVerified);
        

        driverRepository.save(driver);
        return request;
        //}
    }

    //Retrieving all Drivers
    public Map<String, Object> getDrivers() {
        Map<String, Object> map =new HashMap<>();

        List<Driver> list = driverRepository.findAll();
        List<Driver> list2 = new ArrayList<>();
//        for (Driver driver : list) {
//
//            long id2 = driver.getId();
//            if (id2>0){                        //return list of users whose id is more than 1
//                list2.add(driver);
//            }
//        }

        map.put("status","ok");
        map.put("error",false);
        map.put("message","Driver(s) have been found");
        map.put("data" ,list);
        return map;
    }
    
    
    
    
    
    

}
