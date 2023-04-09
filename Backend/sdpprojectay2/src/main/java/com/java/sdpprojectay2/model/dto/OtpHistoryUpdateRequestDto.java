package com.java.sdpprojectay2.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OtpHistoryUpdateRequestDto {
    private String otp;
    private String uuid;
}