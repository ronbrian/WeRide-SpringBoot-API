package com.ronbrian.demorestful.demo;

import com.ronbrian.demorestful.demo.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String description);

    Long countByEmail(String s);
}
