package com.ronbrian.demorestful.demo.controllers;

import com.ronbrian.demorestful.demo.entities.User;
import com.ronbrian.demorestful.demo.repositories.UserRepository;
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
public class UserController {

    //HashMap<String, Object> response = new HashMap<>();

    @Autowired
    public UserRepository userRepository;

    @Autowired
    public UserService userService;


    //Creating a new User
    @PostMapping(value = "/users")
    public Map<String, Object> addUser(@Valid @RequestBody Map <String, Object> request) {
        return userService.save(request);
    }

   /* //Verifying
    @PostMapping(value = "/users/verify{id}")
    public boolean addUser(@PathVariable long id, @RequestBody request)) {
        return true;
    }

*/
    //retrieving and returning all names attributes
    @GetMapping(value = "/users")
    public Map<String, Object> getObject1() {
        return userService.getUsers();
    }

    //retrieving name attribute by id
    @GetMapping(value = "/users/{id}")
    public Map<String, Object> retrieveUser(@PathVariable long id){
        return userService.retrieveUsers(id);
    }


    //deleting name by id
    @DeleteMapping(value = "/users/{id}")
    public void deleteUser(@PathVariable long id) {
        userRepository.deleteById(id);
    }



    //Counting number of Tasks
    @GetMapping(value = "/users/count")
    public long getCount(){
        return userRepository.count();
    }


    //Updating User attributes by id -- passing id as path
    @PutMapping("/users/{id}")
    public ResponseEntity<Map<String, String>> updateUsers(@PathVariable long id, @RequestBody User user) {
        return userService.updateUser(id, user);

    }








}
