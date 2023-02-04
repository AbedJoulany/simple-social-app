package com.example.ex4.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalTime;
import java.util.List;

/* default scope of this Bean is "singleton" */
public interface UserRepository extends JpaRepository<User, Long> {
    /* add here the queries you may need - in addition to CRUD operations
    List<User> findByUserName(String userName);
    List<User> findUserByUserName(String userName);
    List<User> findByEmail(String email);
    List<User> findByUserNameAndEmail(String userName, String email);
    List<User> findFirst10ByOrderByUserNameDesc();*/
    List<User> findByIsActive(boolean isActive);
    List<User> findByIsActiveAndLastConnectionGreaterThan(boolean isActive, LocalTime time);
    User findByUserName(String userName);
    List<User> findAll();
}
