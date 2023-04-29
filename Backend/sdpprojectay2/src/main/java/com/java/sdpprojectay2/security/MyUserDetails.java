package com.java.sdpprojectay2.security;

import com.java.sdpprojectay2.model.Rules;
import com.java.sdpprojectay2.model.Users;
import com.java.sdpprojectay2.utils.EncryptUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class MyUserDetails implements UserDetails {

    private final String firstName;
    private final String lastName;
    private final String username;
    private final String password;
    private final List<GrantedAuthority> authorities = new ArrayList<>();

    public MyUserDetails(Users users, List<Object> rules) {
        this.firstName = users.getFirstName();
        this.lastName = users.getLastName();
        this.username = users.getEmail();
        this.password = new EncryptUtils().decrypt(users.getPassword());
//        for (Object rule : rules) {
//            authorities.add(new SimpleGrantedAuthority(rule.getKeyword()));
//        }
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
