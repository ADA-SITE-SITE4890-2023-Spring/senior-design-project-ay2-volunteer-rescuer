package com.java.sdpprojectay2.service.impl;

import com.java.sdpprojectay2.model.dto.OtpCheckRequestDto;
import com.java.sdpprojectay2.model.dto.OtpCheckResponseDto;
import com.java.sdpprojectay2.model.dto.OtpCreateRequestDto;
import com.java.sdpprojectay2.model.dto.OtpCreateResponseDto;
import com.java.sdpprojectay2.model.dto.OtpHistoryCreateRequestDto;
import com.java.sdpprojectay2.model.entity.OtpHistory;
import com.java.sdpprojectay2.repository.OtpHistoryRepository;
import com.java.sdpprojectay2.service.OtpService;
import com.java.sdpprojectay2.service.SmsService;
import java.util.Random;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class OtpServiceImpl implements OtpService {

    private final SmsService smsService;
    private final OtpHistoryRepository otpHistoryRepository;


    @Override
    public OtpCreateResponseDto createOtp(OtpCreateRequestDto otpRequest) {
        log.info("ActionLog.createOtp.start body: {}", otpRequest);
        var otp = generateOtp();
        try {
            smsService.send(otpRequest.getPhoneNumber(), otp);
        } catch (Exception ex) {
            log.error("Error while sent sms", ex);
        }
        var uuid = UUID.randomUUID();

        // create OtpHistory entity and save phone number, uuid and OTP to check it later
        createOtpHistory(OtpHistoryCreateRequestDto.builder()
                .otp(otp)
                .phoneNumber(otpRequest.getPhoneNumber())
                .uuid(uuid)
                .build());

        log.info("details for otp check, uuid {} otp {}", uuid, otp);
        log.info("ActionLog.createOtp.end");
        return OtpCreateResponseDto.builder().uuid(uuid).build();
    }

    @Override
    public OtpCheckResponseDto checkOtp(OtpCheckRequestDto otpCheckDto) {
        log.info("ActionLog.checkOtp.info code: {}, uuid: {}", otpCheckDto.getOtp(), otpCheckDto.getUuid());

        // Check OTP is correct or not
        checkOtpHistory(otpCheckDto.getOtp(), otpCheckDto.getUuid(), otpCheckDto.getPhoneNumber());

        return OtpCheckResponseDto.builder().success(true).build();
    }

    private void checkOtpHistory(String otp, UUID uuid, String phoneNumber) {
        OtpHistory otpHistory = otpHistoryRepository
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
        otpHistoryRepository.save(OtpHistory.builder()
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
