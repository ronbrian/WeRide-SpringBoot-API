package com.ronbrian.demorestful.demo.services;

import com.google.protobuf.InvalidProtocolBufferException;
//import com.google.type.LatLng;
import com.ronbrian.demorestful.demo.repositories.DriverRepository;
import com.ronbrian.demorestful.demo.repositories.PassengerRepository;
import com.ronbrian.demorestful.demo.repositories.UserRepository;
import com.ronbrian.demorestful.demo.entities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Instant;
import java.util.*;
import java.text.DecimalFormat;

@RestController
@Service
public class DriverService {
    @Autowired
    public DriverRepository driverRepository;

    private static DecimalFormat df = new DecimalFormat("0.00");

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
//        String vehicleID = (String) request.get("vehicleID");
//        int noOfTrips = (int) request.get("noOfTrips");
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
//        driver.setVehicleID(vehicleID);
//        driver.setNoOfTrips(noOfTrips);
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
        Map<String, Object> map2 =new HashMap<>();

        List<Driver> list = driverRepository.findAll();
        Long totalCount = driverRepository.count();
        List<Driver> list2 = new ArrayList<>();

        int i = 1;
        for (Driver driver : list) {
            map.put("data" ,map2);
            map2.put("" + i ,driver);
            i++;
        }



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
        map.put("total", totalCount);
//        map.put("data" ,list);
        return map;
    }


    //Retrieving all Drivers
    public Map<String, Object> getDriversNearby(double lat, double lng) throws IOException {
        Map<String, Object> map =new HashMap<>();
        Map<String, Object> map2 =new HashMap<>();
        Map<String, Object> map3 =new HashMap<>();

        List<Driver> list = driverRepository.findAll();
        //List<Driver> list2 = new ArrayList<>();

        int i = 1;
        int totalCount = 0;

        for (Driver driver : list) {
            driver.getLat();
            driver.getLng();

            double dist = calculateDistance(lat, lng, driver.getLat(), driver.getLng());

            if (dist < 3){
                //Driver is around 3 Kilometres away / Nearby
                totalCount++;
                map.put("data" ,map2);
                map2.put("" + i ,driver.getFname() +" " + driver.getLname() +"- "+ dist + " Km Away" + " -" + driver.getPhone()  );

            }else{
                //Far away than 3 Kilometers
                i = i-1;
            }
            i++;
        }
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
        map.put("total", totalCount);
//        map.put("data" ,list);

        //Sending push notification
//        Process process = Runtime.getRuntime().exec("xcrun simctl push FEB79038-C6DC-4060-A65E-1DBCEBB68414 co.ke.ron.We-Ride notification.apns");
//        printResults(process);

        return map;
    }

    public static void printResults(Process process) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String line = "";
        while ((line = reader.readLine()) != null) {
            System.out.println(line);
        }
    }


    //Getting Distance between two Locations
    public double calculateDistance(double lat1, double lon1, double lat2, double lon2) {
        int Radius = 6371;// radius of earth in Km
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2)
                + Math.cos(Math.toRadians(lat1))
                * Math.cos(Math.toRadians(lat2)) * Math.sin(dLon / 2)
                * Math.sin(dLon / 2);
        double c = 2 * Math.asin(Math.sqrt(a));
        double valueResult = Radius * c;
        double km = valueResult / 1;
        DecimalFormat newFormat = new DecimalFormat("####");
        int kmInDec = Integer.valueOf(newFormat.format(km));
        double meter = valueResult % 1000;
        int meterInDec = Integer.valueOf(newFormat.format(meter));
//
        double distance = Radius*c + kmInDec;
        //double dist1 = df.format(distance);

        return round(distance, 2);

    }

    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = BigDecimal.valueOf(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
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
