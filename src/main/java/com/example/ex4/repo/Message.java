package com.example.ex4.repo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;

@Entity
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotEmpty(message = "Message is mandatory")
    private String message;

    /*@ManyToOne
    private User user;*/

    //@NotEmpty(message = "userId is mandatory")
    private Long userId;

    public Message() {}

    public Message(String message, Long userId) {
        this.message = message;
        this.userId = userId;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getUserId() {
        return userId;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", message=" + this.message  + '}';
    }
}

