package com.ronbrian.demorestful.demo.repositories;

import com.ronbrian.demorestful.demo.entities.Driver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DriverRepository extends JpaRepository<Driver, Long> {
    Driver findByEmail(String description);

    Long countByEmail(String s);
}
