package com.java.sdpprojectay2.model;

import javax.persistence.*;
import lombok.*;
import org.hibernate.annotations.Where;

import java.util.UUID;


@Entity
@Table(name = "otp_history")
@Builder
@Data
@AllArgsConstructor
public class OTPHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String phoneNumber;
    private String otp;
    private String uuid;

    public OTPHistory() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

}
