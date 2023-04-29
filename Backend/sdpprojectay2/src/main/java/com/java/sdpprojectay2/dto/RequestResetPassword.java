package com.java.sdpprojectay2.dto;

public class RequestResetPassword {

    private String otp;
    private String email;
    private String password;

    public RequestResetPassword() {
    }

    public String getOtp() {
        return otp;
    }

    public RequestResetPassword setOtp(String otp) {
        this.otp = otp;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public RequestResetPassword setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public RequestResetPassword setPassword(String password) {
        this.password = password;
        return this;
    }
}
