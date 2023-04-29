package com.java.sdpprojectay2.service;

import com.java.sdpprojectay2.dto.*;
import com.java.sdpprojectay2.exceptions.AlreadyExistException;
import com.java.sdpprojectay2.exceptions.NotFoundException;
import com.java.sdpprojectay2.model.OTPHistory;
import com.java.sdpprojectay2.repository.RepoOTPHistory;
import com.java.sdpprojectay2.repository.RepoUsers;
import com.java.sdpprojectay2.enums.Status;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class DaoOTP {

    private final SmsService smsService;
    private final RepoOTPHistory otpHistoryRepository;
    private final RepoUsers repoUsers;



    public Response createOtp(OtpCreateRequestDto otpRequest) {
        log.info("ActionLog.createOtp.start body: {}", otpRequest);
        var otp = generateOtp();
        try {
            smsService.send(otpRequest.getPhoneNumber(), otp);
            System.out.println("otp where is");
        } catch (Exception ex) {
            log.error("Error while sent sms", ex);
        }
        String uuid = String.valueOf(UUID.randomUUID());

        // create OtpHistory entity and save phone number, uuid and OTP to check it later
        createOtpHistory(OtpHistoryCreateRequestDto.builder()
                .otp(otp)
                .phoneNumber(otpRequest.getPhoneNumber())
                .uuid(uuid)
                .build());

        log.info("details for otp check, uuid {} otp {}", uuid, otp);
        log.info("ActionLog.createOtp.end");
        return new Response().setResponse(OtpCreateResponseDto.builder().uuid(uuid).build());
    }


    public Response checkOtp(OtpCheckRequestDto otpCheckDto) {
        log.info("ActionLog.checkOtp.info code: {}, uuid: {}", otpCheckDto.getOtp(), otpCheckDto.getUuid());

        // Check OTP is correct or not
        checkOtpHistory(otpCheckDto.getOtp(), otpCheckDto.getUuid(), otpCheckDto.getPhoneNumber());

        return new Response().setResponse(OtpCheckResponseDto.builder().success(true).build());
    }

    private void checkOtpHistory(String otp, String uuid, String phoneNumber) {
        OTPHistory otpHistory = otpHistoryRepository
                .findByUuid(uuid).orElseThrow(() -> new RuntimeException("OTP history not found"));

        if (!otp.equals(otpHistory.getOtp())) {
            log.error("OTP does not match for uuid: {}", uuid);
            throw new RuntimeException("Invalid OTP");
        }

        if (!phoneNumber.equals(otpHistory.getPhoneNumber())) {
            log.error("Phone number does not match for uuid: {}", uuid);
            throw new RuntimeException("Invalid phone number");
        }
    }

    private void createOtpHistory(OtpHistoryCreateRequestDto otpData) {
        otpHistoryRepository.save(OTPHistory.builder()
                .otp(otpData.getOtp())
                .phoneNumber(otpData.getPhoneNumber())
                .uuid(otpData.getUuid())
                .build());
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
