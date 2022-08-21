package com.budget.management.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserLogin {

    private Long id;
    private String name;
    private String user;
    private String password;
    private String token;

    public UserLogin(Long id, String name, String user, String password, String token) {
        this.id = id;
        this.name = name;
        this.user = user;
        this.password = password;
        this.token = token;
    }
    public UserLogin(){

    }
}
