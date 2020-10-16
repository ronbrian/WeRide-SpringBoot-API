package com.ronbrian.demorestful.demo;

import com.ronbrian.demorestful.demo.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@Service
public class UserService {

    @Autowired
    public UserRepository userRepository;


    public Map<String, Object> save(Map<String, Object> request) {
    if(null==request.get("name")){
            request.put("status","00");
            request.put("message","User Cannot Be Blank !");
            return request;

        }else if(null ==request.get("email")) {
            request.put("status","00");
            request.put("message","email Cannot Be Blank !");
            return request;

        }else {

            String name = (String) request.get("name");
            String email = (String) request.get("email");
            String userId = (String) request.get("userId");
            String location = (String) request.get("location");





            // if phone and email are valid
            //request.put("status", "00"); //status success
            request.put("data", "00");

            User user1 = new User();
            user1.setName(name);
            user1.setEmail(email);
            user1.setUserId(userId);
            user1.setLocation(location);

            userRepository.save(user1);

            return request;



        }


    }

    public Map<String, Object> getUsers() {
        Map<String, Object> map =new HashMap<>();

        List<User> list = userRepository.findAll();
        List<User> list2 = new ArrayList<>();
        for (User user : list) {

            long id2 = user.getId();
            if (id2>0){                        //return list of users whose id is more than 1
                list2.add(user);
            }
        }

        map.put("status","ok");
        map.put("error",false);
        map.put("message","Task(s) have been found");
        map.put("data" ,list2);
        return map;
    }


    public Map<String, Object> retrieveUsers(long id){
        Map<String, Object> map =new HashMap<>();
        Optional<User> name = userRepository.findById(id);


        if (!name.isPresent()){

            map.put("status","00");
            map.put("message", "User with that ID does not exist ! ");

        }else {
            map.put("status", "01");
            map.put("message", "success");

            map.put("data", name);
        }

        return map;
    }



    public ResponseEntity<Map<String, String>> updateUser(long id, User user){

        Map<String, String> resp = new HashMap<>();
        User user1 = userRepository.findById(id).orElse(null);

        if (user1 == null){
            resp.put("state", "danger");
            resp.put("msg","id not found");
            return ResponseEntity.ok(resp);
        }
        user1.setName(user.getName());
        user1.setEmail(user.getEmail());
        user1.setLocation(user.getLocation());
        user1.setUserId(user.getUserId());

        //return nameService.updateTask(id,name,email,phone);

        userRepository.save(user1);
        resp.put("state", "success");
        resp.put("msg", "User updated successfully");
        return ResponseEntity.ok(resp);
    }









}
