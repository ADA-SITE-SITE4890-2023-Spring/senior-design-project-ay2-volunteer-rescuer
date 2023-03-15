package com.java.sdpprojectay2.service;

import com.java.sdpprojectay2.model.entity.User;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface UserService {
    User create(@RequestBody User user);

    User update(@RequestBody User user);

    List<User> getAll();

    User getById(Integer id);

    void deleteById(Integer id);
}
