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
//    public Map<String, Object> login(@Valid @RequestParam String email, @RequestParam String password, @RequestParam String usertype) throws IOException {
    public Map<String, Object> login(@Valid @RequestBody Map <String, Object> request) throws IOException {
        Map<String, Object> map =new HashMap<>();
        Map<String, Object> map2 =new HashMap<>();

        String email = (String) request.get("email");
        String password = (String) request.get("password");
        String usertype = (String) request.get("usertype");

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
                        map.put("UserDetails", map2);
                        // --User Details

                        map2.put("id", passenger1.getPassengerId());
                        map2.put("fname", passenger1.getFname());
                        map2.put("lname", passenger1.getLname());
                        map2.put("email", passenger1.getEmail());
                        map2.put("phone", passenger1.getPhone());
                        map2.put("password", passenger1.getPassword());
                        map2.put("noOfTrips", passenger1.getNoOfTrips());


                        //Login Succesful, proceed to issue a JWT token

                        //smsAPI smsapi = new smsAPI();


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
                        // -- User Details
                        map.put("UserDetails", map2);
                        map2.put("id", driver1.getDriverId());
                        map2.put("driverId", driver1.getDriverId());
                        map2.put("fname", driver1.getFname());
                        map2.put("lname", driver1.getLname());
                        map2.put("email", driver1.getEmail());
                        map2.put("phone", driver1.getPhone());
                        map2.put("password", driver1.getPassword());
                        map2.put("noOfTrips", driver1.getNoOfTrips());
                        map2.put("vehicleID", driver1.getVehicleID());





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
        //Creating a new User
        //Validate to make sure all fields are present, email and phone are not already in the database, then call the services for saving them.
        @PostMapping(value = "/api/register")
        public Map<String, Object> registerUser(@Valid @RequestBody Map <String, Object> request) {
            Map<String, Object> map =new HashMap<>();
            int usertype = (int) request.get("usertype");

            String fname = (String) request.get("fname");
            String lname = (String) request.get("lname");
            String email = (String) request.get("email");
            String password = (String) request.get("password");
//            String phone = (int) request.get("phone");

            if(fname.equals("")){
                map.put("status","missing fname");
                map.put("message","fname is missing");
            }else if(lname.equals("")){
                map.put("status","missing lname");
                map.put("message","lname is missing");
            }else if(email.equals("")){
                map.put("status","missing email");
                map.put("message","email is missing");
            }else if(password.equals("")){
                map.put("status","missing password");
                map.put("message","password is missing");
            }else{
                if (usertype==88){
                    //Passenger
                    map.put("message","Passenger is registering");

                    //Check if email exists
                        // Passenger passenger1 = passengerRepository.findByEmail(email);

                    //Check if phone exists
                        // Passenger passenger1 = passengerRepository.findByPhone(phone);


                    //Save passenger if email & phone does not exist
                    passengerService.save(request);

                }else if (usertype==99){
                    //Driver
                    map.put("message","driver is registering");
                    driverService.save(request);

                }else if (usertype==101){
                    //Admin
                    map.put("message","admin is registering");

                }else{
                    map.put("status","usertype not specified");
                }
            }






            //return passengerService.save(request);
            return map;
        }


    }
