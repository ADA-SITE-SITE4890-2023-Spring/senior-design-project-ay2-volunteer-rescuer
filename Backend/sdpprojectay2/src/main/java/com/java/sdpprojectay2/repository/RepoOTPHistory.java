package com.java.sdpprojectay2.repository;

import com.java.sdpprojectay2.model.OTPHistory;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

import java.util.UUID;

public interface RepoOTPHistory extends CrudRepository<OTPHistory, Long> {

    void findByPhoneNumber(String phoneNumber);

    Optional<OTPHistory> findByUuid(String uuid);

}
