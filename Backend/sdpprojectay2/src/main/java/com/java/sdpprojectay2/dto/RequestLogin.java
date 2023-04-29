package com.java.sdpprojectay2.dto;

public class RequestLogin {

    private String phoneNumber;
    private String password;

    public RequestLogin() {

    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public RequestLogin setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public RequestLogin setPassword(String password) {
        this.password = password;
        return this;
    }

    @Override
    public String toString() {
        return "RequestLogin{" +
                "phoneNumber='" + phoneNumber + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
