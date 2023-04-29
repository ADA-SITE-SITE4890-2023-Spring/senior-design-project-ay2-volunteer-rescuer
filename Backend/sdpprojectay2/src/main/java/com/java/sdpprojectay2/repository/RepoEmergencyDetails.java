package com.java.sdpprojectay2.repository;

import com.java.sdpprojectay2.model.EmergencyDetails;
import com.java.sdpprojectay2.model.Users;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface RepoEmergencyDetails extends CrudRepository<EmergencyDetails, Long> {

    List<EmergencyDetails> findByStatusOrderByCreatedDate(int status);

    @Query(value="select count(*) from emergency_details where status = :status", nativeQuery = true)
    Long countByStatus(int status);

    List<EmergencyDetails> findAllByStatus(int status);

    @Query(value = "select * from emergency_details u join users on u.users_id = users.id where users.id_group = 3 AND u.status = :status AND u.deleted = false " +
            "AND (users.first_name like concat('%', :keyword, '%') " +
            "OR users.last_name like concat('%', :keyword, '%') " +
            "OR u.id like concat('%', :keyword, '%') " +
            "OR users.phone_number like concat('%', :keyword, '%'))", nativeQuery=true)
    List<EmergencyDetails> findAllBySearch(String keyword, int status);
}