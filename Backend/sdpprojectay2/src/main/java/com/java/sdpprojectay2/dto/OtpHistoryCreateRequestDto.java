package com.java.sdpprojectay2.dto;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OtpHistoryCreateRequestDto {

    private String phoneNumber;
    private String otp;
    private String uuid;

}
