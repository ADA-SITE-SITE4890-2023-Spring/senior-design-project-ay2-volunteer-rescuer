package com.java.sdpprojectay2.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel(value = "Otp create request model")
public class OtpCreateRequestDto {
    @ApiModelProperty(value = "User's phone number which otp will be sent")
    private String phoneNumber;
}