package com.budget.management.security;

import com.budget.management.model.UserData;
import com.budget.management.repository.UserDataRepository;
import org.hibernate.validator.internal.util.stereotypes.Lazy;
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
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

        Optional<UserData> usuario = repository.findByEmail(userName);

        usuario.orElseThrow(() -> new UsernameNotFoundException(userName + " not found."));

        return usuario.map(UserDetailsImpl::new).get();
    }
}
