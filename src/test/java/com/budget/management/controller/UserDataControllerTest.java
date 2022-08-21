package com.budget.management.controller;

import com.budget.management.Mocks;
import com.budget.management.model.UserData;
import com.budget.management.model.UserLogin;
import com.budget.management.repository.UserDataRepository;
import com.budget.management.service.UserDataService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class UserDataControllerTest {

   private UserDataController userDataController;

    @Mock
    UserDataService service;

    @Mock
    UserDataRepository repository;

    @BeforeEach
    public void initializer(){
        repository = Mockito.mock(UserDataRepository.class);
        service = Mockito.mock(UserDataService.class);
        this.userDataController = new UserDataController(service, repository);
    }

    @Test
    void getAllSuccess() {
        when(repository.findAll()).thenReturn(Mocks.getUserDataList(2));

        ResponseEntity<List<UserData>> answer = userDataController.getAll();

        assertEquals(2, answer.getBody().size());
    }
    @Test
    void getAllFail() {
        when(repository.findAll()).thenReturn(Mocks.getUserDataList(0));

        ResponseEntity<List<UserData>> answer = userDataController.getAll();

        assertEquals(0, answer.getBody().size());
    }

    @Test
    void getByIdSuccess() {
        when(repository.findById(anyLong())).thenReturn(Optional.of(Mocks.getUserData()));

        ResponseEntity<UserData> answer = userDataController.getById(anyLong());

        Assert.notNull(answer);

    }

    @Test
    void getByIdFail() {
        when(repository.findById(anyLong())).thenReturn(Optional.empty());

        ResponseEntity<UserData> answer = userDataController.getById(0);

        Assert.isNull(answer.getBody());

    }

    @Test
    void authenticationUserSuccess() {
        when(service.loginUser(any())).thenReturn(Optional.of(Mocks.getUserLogin()));

        ResponseEntity<UserLogin> answer =  userDataController.authenticationUser(any());

        Assert.notNull(answer);
    }

    @Test
    void authenticationUserFail() {
        when(service.loginUser(any())).thenReturn(Optional.empty());

        ResponseEntity<UserLogin> answer =  userDataController.authenticationUser(any());

        Assert.isNull(answer.getBody());
    }

    @Test
    void postUserSuccess() {
        when(service.registerUser(any())).thenReturn(Optional.of(Mocks.getUserData()));

        ResponseEntity<UserData> answer = userDataController.postUser(any());

        Assert.notNull(answer);
    }
    @Test
    void postUserFail() {
        when(service.registerUser(any())).thenReturn(Optional.empty());

        ResponseEntity<UserData> answer = userDataController.postUser(any());

        Assert.isNull(answer.getBody());
    }

    @Test
    void putUserSuccess() {
        when(service.updateUser(any())).thenReturn(Optional.of(Mocks.getUserData()));

        ResponseEntity<UserData> answer = userDataController.putUser(any());

        Assert.notNull(answer);
    }

    @Test
    void putUserFail() {
        when(service.updateUser(any())).thenReturn(Optional.empty());

        ResponseEntity<UserData> answer = userDataController.putUser(any());

        Assert.isNull(answer.getBody());
    }

}