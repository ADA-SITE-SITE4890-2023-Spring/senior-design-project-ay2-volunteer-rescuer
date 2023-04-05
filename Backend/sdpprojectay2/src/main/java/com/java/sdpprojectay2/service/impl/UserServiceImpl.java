package com.java.sdpprojectay2.service.impl;

import com.java.sdpprojectay2.model.UserPrincipal;
import com.java.sdpprojectay2.model.entity.User;
import com.java.sdpprojectay2.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserDetailsService {

    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findById(username).get();

        System.out.println(user.getUsername() + " is logging in...");
        return new UserPrincipal(user);
    }
}
