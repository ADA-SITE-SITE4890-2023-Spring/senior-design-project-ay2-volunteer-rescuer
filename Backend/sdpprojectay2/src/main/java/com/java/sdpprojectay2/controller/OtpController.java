package com.java.sdpprojectay2.controller.admin;

import com.java.sdpprojectay2.dto.*;
import com.java.sdpprojectay2.service.DaoOTP;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mobile/volunteer-rescue-project/otp")
@RequiredArgsConstructor
@Api("Controller to handle OTP check and check")
public class OtpController {

    private final DaoOTP otpService;

    @PostMapping("/create")
    public Response createOtp(@RequestBody OtpCreateRequestDto otpRequest) {
        return otpService.createOtp(otpRequest);
    }

    @PostMapping("/check")
    public Response checkOtp(@RequestBody OtpCheckRequestDto otpCheckDto) {
        return otpService.checkOtp(otpCheckDto);
    }
}