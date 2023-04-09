package com.java.sdpprojectay2.repository;
import com.java.sdpprojectay2.model.entity.OtpHistory;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;

public interface OtpHistoryRepository extends CrudRepository<OtpHistory, Integer> {
    void findByPhoneNumber(String phoneNumber);
    Optional<OtpHistory> findByUuid(String uuid);
}