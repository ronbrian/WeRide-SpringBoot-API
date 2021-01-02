package com.ronbrian.demorestful.demo.controllers;
import com.ronbrian.demorestful.demo.entities.Admin;
import com.ronbrian.demorestful.demo.repositories.AdminRepository;
import com.ronbrian.demorestful.demo.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ronbrian.demorestful.demo.services.AdminService;
import com.ronbrian.demorestful.demo.repositories.AdminRepository;
import com.ronbrian.demorestful.demo.entities.Admin;
import javax.validation.Valid;
import java.util.Map;

@RestController
public class AdminController {
    @Autowired
    public AdminRepository adminRepository;

    @Autowired
    public AdminService adminService;

    //Creating new Admin
    @PostMapping(value = "/api/admin")
    public Map<String, Object> addAdmin(@Valid @RequestBody Map <String, Object> request) {
        return adminService.save(request);
    }

    //Retrieving all Admins
    @GetMapping(value = "/api/admins")
    public Map<String, Object> getAllAdmins() {
        return adminService.getAdmins();
    }


    //Retrieving passenger by ID
    @GetMapping(value = "/api/admins/{id}")
    public Map<String, Object> retrieve_Admin(@PathVariable long id){
        return adminService.retrieveAdmin(id);
    }



    //    //Updating Admin attributes by id -- passing id as path
    //    //Before Deleting , you must get user details the save them as a json
    @PutMapping("/api/admin/update/{id}")
    public ResponseEntity<Map<String, String>> update_Admin(@PathVariable long id, @RequestBody Admin admin) {
        return adminService.updateAdmin(id, admin);
    }


}
