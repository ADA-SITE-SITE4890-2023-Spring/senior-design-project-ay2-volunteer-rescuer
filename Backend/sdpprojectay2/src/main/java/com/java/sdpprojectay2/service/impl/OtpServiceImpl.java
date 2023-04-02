package com.java.sdpprojectay2.service.impl;

import com.java.sdpprojectay2.model.dto.OtpCheckRequestDto;
import com.java.sdpprojectay2.model.dto.OtpCheckResponseDto;
import com.java.sdpprojectay2.model.dto.OtpCreateRequestDto;
import com.java.sdpprojectay2.model.dto.OtpCreateResponseDto;
import com.java.sdpprojectay2.service.OtpService;
import com.java.sdpprojectay2.service.SmsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class OtpServiceImpl implements OtpService {

    private final SmsService smsService;


    @Override
    public OtpCreateResponseDto createOtp(OtpCreateRequestDto otpRequest) {
        log.info("ActionLog.createOtp.start body: {}", otpRequest);
        var otp = generateOtp();
        try {
            smsService.send(otpRequest.getPhoneNumber(), otp);
        } catch (Exception ex) {
            log.error("Error while sent sms", ex);
        }
        var uuid = UUID.randomUUID().toString();

        //TODO - create OtpHistory entity and save phone number, uuid and OTP to check it later

        log.info("details for otp check, uuid {} otp {}", uuid, otp);
        log.info("ActionLog.createOtp.end");
        return OtpCreateResponseDto.builder().uuid(uuid).build();
    }


    @Override
    public OtpCheckResponseDto checkOtp(OtpCheckRequestDto otpCheckDto) {
        log.info("ActionLog.checkOtp.info code: {}, uuid: {}", otpCheckDto.getOtp(), otpCheckDto.getUuid());

        //TODO - Check OTP is correct or not

        return OtpCheckResponseDto.builder().success(true).build();
    }

    private String generateOtp() {
        String numbers = "0123456789";
        StringBuilder otpValue = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            otpValue.append(numbers.charAt(new Random().nextInt(numbers.length())));
        }
        return otpValue.toString();
    }

}
