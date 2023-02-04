package com.example.ex4.beans;

import com.example.ex4.repo.User;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Component
public class UsersBean implements Serializable {
    private List<User> users;
    static long ID = 1;

    public UsersBean() {
        this.users = new ArrayList<User>();
    }

    public void addUser(User user) {
        if(!users.contains(user)) {
            user.setId(ID++);
            users.add(user);
        }
    }

    public void removeUser(User user) {
        users.get(users.indexOf(user)).setIsActive(false);
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public List<User> getUsers() {
        return this.users;
    }

    public User getUserById(Long id) {
        User user = null;
        for (User activeUser : this.users) {
            if (activeUser.getId() == id) {
                user = activeUser;
            }
        }
        return user;
    }
    public User getUserByUserName(String UserName) {
        User user = null;
        for (User activeUser : this.users) {
            if (activeUser.getUserName().equals(UserName)) {
                user = activeUser;
            }
        }
        return user;
    }

    public List<User> getActiveUsers() {
        List<User> usersActive = new ArrayList<User>();
        for (User activeUser : this.users) {
            if (activeUser.getIsActive()) {
                usersActive.add(activeUser);
            }
        }
        return usersActive;
    }

    public boolean isUserActive(User user){

        System.out.println("users list"+users+ " user"+user);
        System.out.println("in is user active");
        for (User u : this.users){
            if(u.getUserName().equals(user.getUserName())) {
                return user.getIsActive();
            }
        }
        return false;
    }
    public boolean isUserActiveById(Long userId){
        System.out.println("users list"+users);
        for (User u : this.users){
            if(u.getId() == userId)
                return u.getIsActive();
        }
        return false;
    }
}