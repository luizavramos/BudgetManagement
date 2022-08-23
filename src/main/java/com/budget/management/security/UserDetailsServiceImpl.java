package com.budget.management.security;

import com.budget.management.model.UserData;
import com.budget.management.repository.UserDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserDataRepository repository;

    @Override
    public UserDetails loadUserByUsername(String userName)
            throws UsernameNotFoundException {
        Optional<UserData> user = repository.findByUser(userName);

        user.orElseThrow(() -> new UsernameNotFoundException(userName + " Not found. "));

        return user.map(UserDetailsImpl::new).get();
    }
}
