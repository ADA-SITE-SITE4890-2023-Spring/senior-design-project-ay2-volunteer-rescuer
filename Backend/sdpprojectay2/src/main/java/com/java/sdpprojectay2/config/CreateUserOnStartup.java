package com.java.sdpprojectay2.config;


import com.java.sdpprojectay2.model.Users;
import com.java.sdpprojectay2.repository.RepoUsers;
import com.java.sdpprojectay2.utils.EncryptUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

@Component
public class CreateUserOnStartup implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private RepoUsers repoUsers;


    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        Optional<Users> user = repoUsers.findByEmail("test@mail.ru");
        if (user.isPresent()) {
            return;
        }
        Users admin = new Users()
                //.setId(1)
                .setDeleted(false)
                .setCreatedDate(LocalDateTime.now())
                .setBirthDate(LocalDate.of(2023, 1, 1))
                .setEmail("test@mail.ru")
                .setGender(1)
                .setIdGroup(1)
                .setPassword(new EncryptUtils().encrypt("123456"))
                .setPhoneNumber("994504442630")
                .setFirstName("admin")
                .setLastName("admin");
        repoUsers.save(admin);
    }
}
