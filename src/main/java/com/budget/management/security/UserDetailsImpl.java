package com.budget.management.security;

import com.budget.management.model.UserData;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class UserDetailsImpl implements UserDetails {

    private static final long serialVersionUID =1L;
    private String userName;
    private String password1;
    private List<GrantedAuthority> authorities;

    public UserDetailsImpl (UserData user1){
        this.userName = user1.getUser();
        this.password1 = user1.getPassword();
    }
    public UserDetailsImpl (){}
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }
    @Override
    public String getPassword() {
        return password1;
    }
    @Override
    public String getUsername() {
        return userName;
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
    public boolean isCredentialsNonExpired(){
        return true;
    }
    @Override
    public boolean isEnabled() {
        return true;
    }
}
