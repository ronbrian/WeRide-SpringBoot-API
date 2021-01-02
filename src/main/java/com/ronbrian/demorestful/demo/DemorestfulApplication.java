package com.ronbrian.demorestful.demo;

import com.ronbrian.demorestful.demo.entities.Passenger;
import com.ronbrian.demorestful.demo.repositories.PassengerRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.ApplicationPidFileWriter;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EntityScan
@EnableJpaAuditing
public class DemorestfulApplication {

    public static void main(String[] args) {

        SpringApplication springApplication = new SpringApplication(DemorestfulApplication.class);
        springApplication.addListeners(new ApplicationPidFileWriter());
        springApplication.run(args);

    }

}
