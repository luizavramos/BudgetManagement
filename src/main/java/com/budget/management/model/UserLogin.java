package com.budget.management.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserLogin {

    private Long id;
    private String name;
    private String user;
    private String secret;
    private String token;

    public UserLogin(Long id, String name, String user, String secret, String token) {
        this.id = id;
        this.name = name;
        this.user = user;
        this.secret = secret;
        this.token = token;
    }
    public UserLogin(){

    }
}
