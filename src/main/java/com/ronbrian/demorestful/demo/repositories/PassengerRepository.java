package com.ronbrian.demorestful.demo.repositories;

import com.ronbrian.demorestful.demo.entities.Passenger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PassengerRepository extends JpaRepository<Passenger, Long> {
    Passenger findByEmail(String description);

    Long countByEmail(String s);

}
