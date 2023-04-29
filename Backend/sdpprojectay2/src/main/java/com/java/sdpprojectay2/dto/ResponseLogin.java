package com.java.sdpprojectay2.dto;



import com.java.sdpprojectay2.model.Rules;
import com.java.sdpprojectay2.model.Users;

import java.util.List;

public class ResponseLogin {

    private String token;
    private Users user;
    private List<Rules> rules;

    public ResponseLogin() {

    }

    public String getToken() {
        return token;
    }

    public ResponseLogin setToken(String token) {
        this.token = token;
        return this;
    }

    public Users getUser() {
        return user;
    }

    public ResponseLogin setUser(Users user) {
        this.user = user;
        return this;
    }

    public List<Rules> getRules() {
        return rules;
    }

    public ResponseLogin setRules(List<Rules> rules) {
        this.rules = rules;
        return this;
    }

    @Override
    public String toString() {
        return "ResponseLogin{" +
                "token='" + token + '\'' +
                ", user=" + user +
                ", rules=" + rules +
                '}';
    }
}
