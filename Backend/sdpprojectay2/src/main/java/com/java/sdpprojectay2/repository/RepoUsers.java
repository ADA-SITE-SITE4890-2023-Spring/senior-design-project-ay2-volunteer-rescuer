package com.java.sdpprojectay2.repository;

import com.java.sdpprojectay2.model.Users;
import org.apache.catalina.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;


public interface RepoUsers extends CrudRepository<Users, Long>{

    Optional<Users> findByEmail(String email);
    Optional<Users> findByEmailAndPassword(String email, String password);
    List<Users> findAllByTokenNotNull();

    Optional<Users> findByResetToken(String token);

    Optional<Users> findByPhoneNumber(String phoneNumber);

    Optional<Users> findByPhoneNumberAndPassword(String phoneNumber, String password);

    List<Users> findAll();

    @Query(value = "select * from users u where u.id_group = 3 AND u.deleted = false " +
            "AND (u.first_name like concat('%', :keyword, '%') " +
            "OR u.last_name like concat('%', :keyword, '%') " +
            "OR u.id like concat('%', :keyword, '%') " +
            "OR u.phone_number like concat('%', :keyword, '%'))", nativeQuery=true)
    List<Users> findAllBySearch(String keyword);

    @Query(value="select count(*) from users where id_group = :idGroup", nativeQuery = true)
    Long countByIdGroup(long idGroup);

    Optional<Users> findByToken(String token);

    List<Users> findByIdGroup(long idGroup);

    @Query(value = "SELECT * FROM users WHERE deleted = false " +
            "AND CASE WHEN :query != '' THEN fullname like concat('%', concat(:query, '%')) ELSE TRUE END " +
            "", nativeQuery = true)
    Page<Users> findAllBy(String query, Pageable pageable);

    List<User> findAll(Sort email);

}