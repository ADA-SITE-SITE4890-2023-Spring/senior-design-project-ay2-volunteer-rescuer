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
@ApiModel(value = "Otp create response model")
public class OtpCreateResponseDto {
    @ApiModelProperty(value = "To identify which user applied code")
    private UUID uuid;
}
