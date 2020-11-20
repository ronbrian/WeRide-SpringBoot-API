package com.ronbrian.demorestful.demo.services;

import com.ronbrian.demorestful.demo.entities.Admin;
import com.ronbrian.demorestful.demo.repositories.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@Service
public class AdminService {
    @Autowired
    public AdminRepository adminRepository;

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
        int usertype = 1;
        


        // if phone and email are valid
        //request.put("status", "00"); //status success
        request.put("data", "00");

        Admin admin = new Admin();
        admin.setFname(fname);
        admin.setLname(lname);
        admin.setEmail(email);
        admin.setPhone(phone);
        admin.setPassword(password);
        admin.setUserType(usertype);

        adminRepository.save(admin);
        return request;
        //}
    }

    //Retrieving all Drivers
    public Map<String, Object> getAdmins() {
        Map<String, Object> map =new HashMap<>();

        List<Admin> list = adminRepository.findAll();
        List<Admin> list2 = new ArrayList<>();
//        for (Admin admin : list) {
//
//            long id2 = admin.getId();
//            if (id2>0){                        //return list of users whose id is more than 1
//                list2.add(admin);
//            }
//        }

        map.put("status","ok");
        map.put("error",false);
        map.put("message","Admin(s) have been found");
        map.put("data" ,list);
        return map;
    }


    public Map<String, Object> retrieveAdmin(long id){
        Map<String, Object> map =new HashMap<>();
        Optional<Admin> name = adminRepository.findById(id);


        if (!name.isPresent()){

            map.put("status","00");
            map.put("message", "Admin with that ID does not exist ! ");

        }else {
            map.put("status", "01");
            map.put("message", "success");

            map.put("data", name);
        }

        return map;
    }



    public ResponseEntity<Map<String, String>> updateAdmin(long id, Admin admin){

        Map<String, String> resp = new HashMap<>();
        Admin admin1 = adminRepository.findById(id).orElse(null);

        if (admin1 == null){
            resp.put("state", "danger");
            resp.put("msg","id not found");
            return ResponseEntity.ok(resp);
        }
        admin1.setFname(admin.getFname());
        admin1.setLname(admin.getLname());
        admin1.setEmail(admin.getEmail());
        admin1.setPhone(admin.getPhone());
        admin1.setPassword(admin.getPassword());
        admin1.setUserType(admin.getUserType());
        admin1.setEmail(admin.getEmail());

        adminRepository.save(admin1);
        resp.put("state", "success");
        resp.put("msg", "Admin updated successfully");
        return ResponseEntity.ok(resp);
    }


}
