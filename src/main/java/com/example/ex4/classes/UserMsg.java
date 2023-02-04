package com.example.ex4.classes;

public class UserMsg {

    private String userName;
    private String message;

    public UserMsg(){}
    public UserMsg(String userName, String message)
    {
        setUserName(userName);
        setMessage(message);
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }
}
