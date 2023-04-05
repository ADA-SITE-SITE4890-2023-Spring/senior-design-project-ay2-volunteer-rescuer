package com.java.sdpprojectay2.repository;

import com.java.sdpprojectay2.model.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<User, String> {
    List<User> findAll();
}