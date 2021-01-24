package com.ronbrian.demorestful.demo.repositories;
import com.ronbrian.demorestful.demo.entities.DriverLiveLocation;
import com.ronbrian.demorestful.demo.entities.Trips;
import org.springframework.data.jpa.repository.JpaRepository;
import com.sun.xml.bind.v2.model.core.ID;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface DriverLiveLocationRepository extends JpaRepository<DriverLiveLocation, Long>{
    DriverLiveLocation findByDriverID(long id);
//    DriverLiveLocation findBymID(long id);

    Long countByDriverID(ID s);

}
