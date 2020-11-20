package com.ronbrian.demorestful.demo.repositories;

import com.ronbrian.demorestful.demo.entities.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {
    Admin findByEmail(String description);

    Long countByEmail(String s);
}
