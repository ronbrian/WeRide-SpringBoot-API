package com.ronbrian.demorestful.demo.services;

import com.ronbrian.demorestful.demo.repositories.DriverRepository;
import com.ronbrian.demorestful.demo.repositories.PassengerRepository;
import com.ronbrian.demorestful.demo.repositories.UserRepository;
import com.ronbrian.demorestful.demo.entities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
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


    public Map<String, Object> retrieveDriver(long id){
        Map<String, Object> map =new HashMap<>();
        Optional<Driver> name = driverRepository.findById(id);


        if (!name.isPresent()){

            map.put("status","00");
            map.put("message", "Driver with that ID does not exist ! ");

        }else {
            map.put("status", "01");
            map.put("message", "success");

            map.put("data", name);
        }

        return map;
    }



    public ResponseEntity<Map<String, String>> updateDriver(long id, Driver driver){

        Map<String, String> resp = new HashMap<>();
        Driver driver1 = driverRepository.findById(id).orElse(null);

        if (driver1 == null){
            resp.put("state", "danger");
            resp.put("msg","id not found");
            return ResponseEntity.ok(resp);
        }
        driver1.setFname(driver.getFname());
        driver1.setLname(driver.getLname());
        driver1.setEmail(driver.getEmail());
        driver1.setPhone(driver.getPhone());
        driver1.setPassword(driver.getPassword());
        driver1.setVehicleID(driver.getVehicleID());
        driver1.setNoOfTrips(driver.getNoOfTrips());
        driver1.setUsertype(driver.getUsertype());
        driver1.setActive(driver.isActive());
        driver1.setEmail(driver.getEmail());
        driver1.setVerified(driver.isVerified());
        driver1.setLat(driver.getLat());
        driver1.setLng(driver.getLng());
        long unixTime = Instant.now().getEpochSecond();
        driver1.setLastupdated(unixTime);

        driverRepository.save(driver1);
        resp.put("state", "success");
        resp.put("msg", "Driver updated successfully");
        return ResponseEntity.ok(resp);
    }

    public void disableDriver(long id){
        Driver driver = driverRepository.findById(id).orElse(null);
        driver.setActive(false);
        driverRepository.save(driver);
    }

    public void enableDriver(long id){
        Driver driver = driverRepository.findById(id).orElse(null);
        driver.setActive(true);
        driverRepository.save(driver);
    }
    
    
    
    
    
    

}
