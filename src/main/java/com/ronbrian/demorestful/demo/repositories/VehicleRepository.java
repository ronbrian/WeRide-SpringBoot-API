package com.ronbrian.demorestful.demo.repositories;


import com.ronbrian.demorestful.demo.entities.Vehicles; // <- -
import com.sun.xml.bind.v2.model.core.ID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicles, Long> { // <- - twice
    Vehicles findById(ID id);    // <- -

    Long countById(ID s);
}
