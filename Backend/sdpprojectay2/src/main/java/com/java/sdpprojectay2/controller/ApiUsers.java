package com.java.sdpprojectay2.controller;

import com.java.sdpprojectay2.dto.*;
import com.java.sdpprojectay2.model.Users;
import com.java.sdpprojectay2.service.DaoUsers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/mobile/volunteer-rescue-project/users")
public class ApiUsers {

    @Autowired
    DaoUsers daoUsers;

    @PostMapping("/signup")
    public Response insert(@RequestBody Users user,
                           @RequestHeader String phoneNumber) {
        return daoUsers.insert(user, phoneNumber);
    }

    @PostMapping("/update")
    public Response update(@RequestBody Users users) {
        return daoUsers.update(users);
    }

    @PostMapping("/login")
    public Response login(@RequestBody RequestLogin requestLogin) {
        return daoUsers.login(requestLogin);
    }

    @GetMapping("/checkToken")
    public Response checkToken(@RequestParam String token) {
        return daoUsers.checkToken(token);
    }

    @PostMapping("/resetPassword")
    public Response resetPassword(@RequestBody OtpCreateRequestDto otpCreateRequestDto) {
        return daoUsers.resetPassword(otpCreateRequestDto);
    }

    @PostMapping("/resetPasswordConfirm")
    public Response resetPasswordConfirm(@RequestBody OtpCheckRequestDto otpCheckRequestDto) {
        return daoUsers.resetPasswordConfirm(otpCheckRequestDto);
    }


    @PutMapping("/changePassword")
    public Response resetPass(@RequestBody ChangePassword changePassword, @RequestHeader String phoneNumber) {
        System.out.println(changePassword.getPassword());
        System.out.println(changePassword.getNewPassword());
        System.out.println(phoneNumber);
        return daoUsers.resetPass(changePassword.getPassword(), changePassword.getNewPassword(), phoneNumber);
    }
}