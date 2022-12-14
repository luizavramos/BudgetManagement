package com.budget.management.service;

import com.budget.management.model.UserData;
import com.budget.management.model.UserLogin;
import com.budget.management.repository.UserDataRepository;
import org.apache.commons.codec.binary.Base64;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.nio.charset.Charset;
import java.util.Optional;

@Service
public class UserDataService {


    private UserDataRepository userDataRepository;

    public UserDataService(UserDataRepository userDataRepository) {
        this.userDataRepository = userDataRepository;
    }

    public Optional<UserData> registerUser(UserData userData) {
        if (userDataRepository.findByUser(userData.getUser())
                .isPresent())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "User already exists", null);
        userData.setSecret(encryptPassword(userData.getSecret()));
        return Optional.of(userDataRepository.save(userData));
    }

    public Optional<UserData> updateUser(UserData userData) {
        if (userDataRepository.findById(userData.getId()).isPresent()) {
            Optional<UserData> findUser = userDataRepository.
                    findByUser(userData.getUser());
            if (findUser.isPresent()) {
                if (findUser.get().getId() != userData.getId())
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                            "User already exists!", null);
            }
            userData.setSecret(encryptPassword(userData.getSecret()));
            return Optional.of(userDataRepository.save(userData));
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                "User not found", null);
    }

    public Optional<UserLogin> loginUser(
            Optional<UserLogin> userLogin) {
        Optional<UserData> userData = userDataRepository
                .findByUser(userLogin.get().getUser());

        if (userData.isPresent()) {
            if (comparePassword(userLogin.get().getSecret(),
                    userData.get().getSecret())) {
                userLogin.get().setId(userData.get().getId());
                userLogin.get().setName(userData.get().getName());
                userLogin.get().setToken(
                        generateBasicToken(userLogin.get().getUser(),
                                userLogin.get().getSecret()));
                userLogin.get().setSecret(userData.get().getSecret());
                return userLogin;
            }
        }

        throw new ResponseStatusException(
                HttpStatus.UNAUTHORIZED, "User or password is invalid!", null);
    }
    private String encryptPassword(String password) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String passwordEncoder = encoder.encode(password);
       return passwordEncoder;

    }
    private boolean comparePassword(String typedPassword,
                                   String databasePassword) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.matches(typedPassword, databasePassword);
    }
    private String generateBasicToken(String user, String password) {
        String structure = user + ":" + password;
        byte[] structureBase64 = Base64.encodeBase64(
                structure.getBytes(Charset.forName("US-ASCII")));
        return "Basic " + new String(structureBase64);
    }




    }
