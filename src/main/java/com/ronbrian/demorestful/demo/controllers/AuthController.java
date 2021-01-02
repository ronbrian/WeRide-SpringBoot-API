    package com.ronbrian.demorestful.demo.controllers;
    import com.ronbrian.demorestful.demo.entities.Driver;
    import com.ronbrian.demorestful.demo.entities.Passenger;
    import com.ronbrian.demorestful.demo.entities.Admin;
    import com.ronbrian.demorestful.demo.repositories.AdminRepository;
    import com.ronbrian.demorestful.demo.repositories.DriverRepository;
    import com.ronbrian.demorestful.demo.repositories.PassengerRepository;
    import com.ronbrian.demorestful.demo.services.AdminService;
    import com.ronbrian.demorestful.demo.services.DriverService;
    import com.ronbrian.demorestful.demo.services.PassengerService;
    import com.ronbrian.demorestful.demo.OTP.smsAPI;

    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.web.bind.annotation.PostMapping;
    import org.springframework.web.bind.annotation.RequestBody;
    import org.springframework.web.bind.annotation.RequestParam;
    import org.springframework.stereotype.Service;


    import com.africastalking.Callback;
    import com.africastalking.SmsService;
    import com.africastalking.sms.Message;
    import com.africastalking.sms.Recipient;
    import com.africastalking.AfricasTalking;

    import org.springframework.web.bind.annotation.RestController;

    import javax.validation.Valid;
    import java.io.IOException;
    import java.util.*;


    @RestController
    @Service
    public class AuthController {

    @Autowired
    public DriverRepository driverRepository;

    @Autowired
    public PassengerRepository passengerRepository;

    @Autowired
    public AdminRepository adminRepository;

    @Autowired
    public DriverService driverService;

    @Autowired
    public PassengerService passengerService;

    @Autowired
    public AdminService adminService;


    @PostMapping(value = "/api/login")
    public Map<String, Object> login(@Valid @RequestParam String email, @RequestParam String password, @RequestParam String usertype) throws IOException {
        Map<String, Object> map =new HashMap<>();


        //Check for validation to ensure the values are not empty
        if (email.length() == 0 || password.length() == 0 || usertype.length() == 0){
            //One or more of the fields is empty
            map.put("status","04");
            map.put("message","Email or password is empty");
        } else{
            if (usertype.equals("passenger")){
                //Log in as passenger
                Passenger passenger1 = passengerRepository.findByEmail(email);
                if (passenger1.getFname() == null){
                    //The user doesnt exists
                    map.put("status","02");
                    map.put("message","The user does not exist");

                }else{
                    if (passenger1.getPassword().equals(password)){
                        map.put("status","00");
                        map.put("message","Login Succesful");

                        //Login Succesful, proceed to issue a JWT token

                        smsAPI smsapi = new smsAPI();


                    }else{
                        map.put("status","01");
                        map.put("message","Wrong Password");
                    }
                }
            }else if (usertype.equals("driver")){
                //Log in as driver
                Driver driver1 = driverRepository.findByEmail(email);
                if (driver1.getFname() == null){
                    //The user doesnt exists
                    map.put("status","02");
                    map.put("message","The user does not exist");

                }else{
                    if (driver1.getPassword().equals(password)){
                        map.put("status","00");
                        map.put("message","Login Succesful");

                        //Login Successful, proceed to issue a JWT token


                    }else{
                        map.put("status","01");
                        map.put("message","Wrong Password");
                    }
                }
            }else if (usertype.equals("admin")){
                //Log in as admin
                Admin admin1 = adminRepository.findByEmail(email);
                if (admin1.getFname() == null){
                    //The user doesnt exists
                    map.put("status","02");
                    map.put("message","The user does not exist");

                }else{
                    if (admin1.getPassword().equals(password)){
                        map.put("status","00");
                        map.put("message","Login Succesful");

                        //Login Succesful, proceed to issue a JWT token

                        /* Set your app credentials */
                        String USERNAME = "ronbrian";
                        String API_KEY = "cbbe6a000e87d2125bf4c9c3fbde1fe76ebe28c91483f3895ff21f0693d34ea6";

                        /* Initialize SDK */
                        AfricasTalking.initialize(USERNAME, API_KEY);

                        /* Get the SMS service */
                        SmsService sms = AfricasTalking.getService(AfricasTalking.SERVICE_SMS);

                        /* Set the numbers you want to send to in international format */
                        String[] recipients = new String[] {
                                "+254704814222", "+254722323221"
                        };

                        /* Set your message */
                        String message = "We are lumberjacks. We sleep all day and code all night";

                        /* Set your shortCode or senderId */
                        String from = "WERIDE"; // or "ABCDE"

                        /* That’s it, hit send and we’ll take care of the rest */
                        try {
                            List<Recipient> response = sms.send(message, from, recipients, true);
                            for (Recipient recipient : response) {
                                System.out.print(recipient.number);
                                System.out.print(" : ");
                                System.out.println(recipient.status);
                            }
                        } catch(Exception ex) {
                            ex.printStackTrace();
                        }


                    }else{
                        map.put("status","01");
                        map.put("message","Wrong Password");
                    }
                }
            }else{
                map.put("status","usertype not specified");
                System.out.println("usertype not specified");
            }
        }

        //Proceed to Log in




        //map.put("email",email);
        //map.put("password",password);
        //map.put("usertype",usertype);

        return map;

     }

    }