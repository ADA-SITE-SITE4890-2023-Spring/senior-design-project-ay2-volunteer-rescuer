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
public class OtpHistoryUpdateRequestDto {

    private String otp;
    private UUID uuid;

}