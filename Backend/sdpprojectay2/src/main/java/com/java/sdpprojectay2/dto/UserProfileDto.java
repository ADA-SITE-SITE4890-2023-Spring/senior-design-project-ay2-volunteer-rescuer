package com.java.sdpprojectay2.dto;


public class UserProfileDto {

    private String inputFirstname;
    private String inputLastname;
    private String inputEmail;
    private String inputPassword;

    public UserProfileDto() {
    }

    public String getInputFirstname() {
        return inputFirstname;
    }

    public UserProfileDto setInputFirstname(String inputFirstname) {
        this.inputFirstname = inputFirstname;
        return this;
    }

    public String getInputLastname() {
        return inputLastname;
    }

    public UserProfileDto setInputLastname(String inputLastname) {
        this.inputLastname = inputLastname;
        return this;
    }

    public String getInputEmail() {
        return inputEmail;
    }

    public UserProfileDto setInputEmail(String inputEmail) {
        this.inputEmail = inputEmail;
        return this;
    }

    public String getInputPassword() {
        return inputPassword;
    }

    public UserProfileDto setInputPassword(String inputPassword) {
        this.inputPassword = inputPassword;
        return this;
    }
}
