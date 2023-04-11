package com.java.sdpprojectay2.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel(value = "Otp details check model")
public class OtpCheckRequestDto {
    @ApiModelProperty(value = "User's phone number which otp was sent")
    private String phoneNumber;
    @ApiModelProperty(value = "Otp code which user enter in ui")
    private String otp;
    @ApiModelProperty(value = "To identify which user applied code")
    private UUID uuid;
}
