package com.java.sdpprojectay2.dto;

public class LoginAdminPanel {

    public String email;
    public String password;

    public LoginAdminPanel() {
    }

    public String getEmail() {
        return email;
    }

    public LoginAdminPanel setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public LoginAdminPanel setPassword(String password) {
        this.password = password;
        return this;
    }
}
