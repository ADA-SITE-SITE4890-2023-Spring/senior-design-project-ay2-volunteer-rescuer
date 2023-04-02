package com.java.sdpprojectay2.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "Otp check response model")
public class OtpCheckResponseDto {
    @ApiModelProperty(value = "OTP is correct or not")
    private boolean success;
}
