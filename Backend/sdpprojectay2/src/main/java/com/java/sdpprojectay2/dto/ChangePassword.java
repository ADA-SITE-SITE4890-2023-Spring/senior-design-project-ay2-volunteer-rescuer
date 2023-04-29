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
@ApiModel(value = "password details check model")
public class ChangePassword {
    @ApiModelProperty(value = "User's password which otp was sent")
    private String password;
    @ApiModelProperty(value = "newPassword which user enter in ui")
    private String newPassword;

}