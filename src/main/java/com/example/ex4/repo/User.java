package com.example.ex4.repo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import java.time.LocalTime;


@Entity
public class User{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotEmpty(message = "user name is mandatory")
    private String userName;

    // @NotEmpty(message = "isActive is mandatory")

    private boolean isActive;

    private LocalTime lastConnection;

    public User() {
        System.out.println(LocalTime.now().toSecondOfDay());
        this.lastConnection=LocalTime.now();
        this.isActive = true;
    }

    public User(String userName, boolean isActive) {
        this.userName = userName;
        this.isActive = isActive;
        System.out.println(LocalTime.now());
        this.lastConnection=LocalTime.now();
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    public void setIsActive(boolean isActive){
        this.lastConnection=LocalTime.now();
        this.isActive = isActive;
    }

    public boolean getIsActive() {
        if(!(LocalTime.now().toSecondOfDay() - this.lastConnection.toSecondOfDay()  <= 10)){
            isActive =false;
        }
        return isActive;
    }


    public void setLastConnection(LocalTime time){ this.lastConnection = time;}

    public LocalTime getLastConnection() {return this.lastConnection;}



    @Override
    public String toString() {
        return "User{" + "id=" + id + ", userName=" + userName  + '}';
    }

}

