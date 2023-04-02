package com.java.sdpprojectay2.controller;

import com.java.sdpprojectay2.model.dto.OtpCheckRequestDto;
import com.java.sdpprojectay2.model.dto.OtpCheckResponseDto;
import com.java.sdpprojectay2.model.dto.OtpCreateRequestDto;
import com.java.sdpprojectay2.model.dto.OtpCreateResponseDto;
import com.java.sdpprojectay2.service.OtpService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/otp")
@RequiredArgsConstructor
@Api("Controller to handle OTP check and check")
public class OtpController {

    private final OtpService otpService;

    @PostMapping("/create")
    public OtpCreateResponseDto createOtp(@RequestBody OtpCreateRequestDto otpRequest) {
        return otpService.createOtp(otpRequest);
    }

    @PostMapping("/check")
    public OtpCheckResponseDto checkOtp(@RequestBody OtpCheckRequestDto otpCheckDto) {
        return otpService.checkOtp(otpCheckDto);
    }
}
