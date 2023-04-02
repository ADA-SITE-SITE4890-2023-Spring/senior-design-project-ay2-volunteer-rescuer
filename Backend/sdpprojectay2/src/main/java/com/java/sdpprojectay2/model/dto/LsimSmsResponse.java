package com.java.sdpprojectay2.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LsimSmsResponse {
    private String successMessage;
    private Integer obj;
    private String errorMessage;
    private Integer errorCode;
}
