package com.java.sdpprojectay2.service;

import com.java.sdpprojectay2.dto.LsimSmsResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigInteger;
import java.security.MessageDigest;

@Service
public class DaoLsim implements SmsService{

    private final RestTemplate restTemplate;

    @Value("${client.lsim.base-url}")
    private String smsClientBaseUrl;
    @Value("${client.lsim.user}")
    private String smsClientLogin;

    @Value("${client.lsim.password}")
    private String smsClientPassword;

    @Value("${client.lsim.sender}")
    private String smsClientSender;

    public DaoLsim(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public void send(String phoneNumber, String messageBody) {
        restTemplate.getForObject(buildSendSmsUri(phoneNumber, messageBody), LsimSmsResponse.class);
    }

    private String buildSendSmsUri(String phoneNumber, String messageBody) {
        String s =  String.format("%s%s?login=%s&msisdn=%s&text=%s&sender=%s&key=%s",
                smsClientBaseUrl, "v1/send", smsClientLogin, phoneNumber, messageBody, smsClientSender,
                buildSmsClientKey(phoneNumber, messageBody));

        System.out.println(s);
        return s;
    }

    private String buildSmsClientKey(String to, String text) {
        try {
            return encryptInMd5(String.join("", encryptInMd5(smsClientPassword), smsClientLogin, text, to, smsClientSender));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private String encryptInMd5(String s) throws Exception {
        MessageDigest m = MessageDigest.getInstance("MD5");
        m.update(s.getBytes(), 0, s.length());
        return new BigInteger(1, m.digest()).toString(16);
    }
}
