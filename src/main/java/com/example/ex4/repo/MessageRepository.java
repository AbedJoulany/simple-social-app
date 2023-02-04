package com.example.ex4.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {

    List<Message> findTop5ByOrderByIdDesc();

    List<Message> findByUserId(Long Id);
    List<Message> findAllByMessage(String message);
    List<Message> findAllByMessageContaining(String message);
    /* add here the queries you may need - in addition to CRUD operations

    List<User> findByEmail(String email);
    List<User> findByUserNameAndEmail(String userName, String email);
    List<User> findFirst10ByOrderByUserNameDesc(); */
}
