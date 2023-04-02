package com.java.sdpprojectay2.service;

import com.java.sdpprojectay2.model.dto.OtpCheckRequestDto;
import com.java.sdpprojectay2.model.dto.OtpCheckResponseDto;
import com.java.sdpprojectay2.model.dto.OtpCreateRequestDto;
import com.java.sdpprojectay2.model.dto.OtpCreateResponseDto;

public interface OtpService {
    OtpCreateResponseDto createOtp(OtpCreateRequestDto otpRequest);

    OtpCheckResponseDto checkOtp(OtpCheckRequestDto otpCheckDto);
}
