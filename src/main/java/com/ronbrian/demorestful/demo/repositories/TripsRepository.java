package com.ronbrian.demorestful.demo.repositories;

import com.ronbrian.demorestful.demo.entities.Trips;
import com.ronbrian.demorestful.demo.entities.Trips; // <- -
import com.sun.xml.bind.v2.model.core.ID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TripsRepository extends JpaRepository<Trips, Long> { // <- - twice
    Trips findById(ID id);    // <- -

    Long countById(ID s);
}
