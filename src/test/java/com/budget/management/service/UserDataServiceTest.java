package com.budget.management.service;

import com.budget.management.Mocks;
import com.budget.management.model.UserData;
import com.budget.management.model.UserLogin;
import com.budget.management.repository.ExpenseRepository;
import com.budget.management.repository.UserDataRepository;
import org.h2.engine.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.util.Assert;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class UserDataServiceTest {
    private UserDataService service;

    @Mock
    UserDataRepository repository;

    @Mock
    UserData userData;

    @BeforeEach
    public void initializer() {
        repository = Mockito.mock(UserDataRepository.class);
        userData = Mockito.mock(UserData.class);
        this.service = new UserDataService(repository);
    }

    @Test
    void registerUserSuccess() {
        when(repository.findByUser(anyString()))
                .thenReturn(Optional.empty());
        when(repository.save(any()))
                .thenReturn(new UserData());

        Optional<UserData> answer = service.registerUser(Mocks.getUserData());

        verify(repository, times(1)).findByUser(Mocks.getUserData().getUser());
        Assert.notNull(answer);
    }

    @Test
    void registerUserFail() {
        when(repository.findByUser(anyString()))
                .thenReturn(Optional.of(Mocks.getUserData()));
        when(repository.save(any()))
                .thenReturn(new UserData());

        assertThrows(ResponseStatusException.class, () -> service.registerUser(Mocks.getUserData()));
    }

    @Test
    void updateUserSuccess() {
        when(repository.findById(anyLong()))
                .thenReturn(Optional.of(Mocks.getUserData()));
        when(repository.findByUser(anyString()))
                .thenReturn(Optional.of(Mocks.getUserData()));
        when(repository.save(any())).thenReturn(new UserData());

        Optional<UserData> answer = service.updateUser(Mocks.getUserData());
        verify(repository, times(1)).findByUser(Mocks.getUserData().getUser());
        verify(repository, times(1)).findById(Mocks.getUserData().getId());
    }

    @Test
    void updateUserFail() {
        when(repository.findById(anyLong()))
                .thenReturn(Optional.of(Mocks.getUserData()));
        when(repository.findByUser(anyString()))
                .thenReturn(Optional.of(new UserData()));
        when(repository.save(any())).thenReturn(new UserData());

        assertThrows(ResponseStatusException.class, () -> service.updateUser(Mocks.getUserData()));
    }

//    @Test
//    void loginUser() {
//
//        when(repository.findByUser(anyString()))
//                .thenReturn(Optional.of(Mocks.getUserData()));
//
//        Optional<UserLogin> answer = service.loginUser(Optional.of(Mocks.getUserLogin()));
//
//        verify(repository, times(1)).findByUser(userData.getUser());
//
//
//    }
}