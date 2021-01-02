package com.ronbrian.demorestful.demo.OTP;

/* Import SDK classes */
import com.africastalking.Callback;
import com.africastalking.SmsService;
import com.africastalking.sms.Message;
import com.africastalking.sms.Recipient;
import com.africastalking.AfricasTalking;

import java.util.List;
import java.io.IOException;

public class smsAPI {
    public smsAPI(){ }

    public static void main(String[] args) {
        /* Set your app credentials */
        String USERNAME = "ronbrian";
        String API_KEY = "cbbe6a000e87d2125bf4c9c3fbde1fe76ebe28c91483f3895ff21f0693d34ea6";

        /* Initialize SDK */
        AfricasTalking.initialize(USERNAME, API_KEY);

        /* Get the SMS service */
        SmsService sms = AfricasTalking.getService(AfricasTalking.SERVICE_SMS);

        /* Set the numbers you want to send to in international format */
        String[] recipients = new String[] {
                "+254704814222"
        };

        /* Set your message */
        String message = "We are lumberjacks. We sleep all day and code all night";

        /* Set your shortCode or senderId */
        String from = "XXXXX"; // or "ABCDE"

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

        System.out.println("Done");
    }

    public void send(){


    }
}
