package com.budget.management.controller;

import com.budget.management.model.UserData;
import com.budget.management.model.UserLogin;
import com.budget.management.repository.UserDataRepository;
import com.budget.management.service.UserDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UserDataController {

    private UserDataService service;


    private UserDataRepository repository;

    public UserDataController(UserDataService service, UserDataRepository repository) {
        this.service = service;
        this.repository = repository;
    }
    @GetMapping("/all")
    public ResponseEntity <List<UserData>> getAll() {
        return ResponseEntity.ok(repository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserData> getById(@PathVariable long id) {
        return repository.findById(id)
                .map(resp -> ResponseEntity.ok(resp))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/login")
    public ResponseEntity<UserLogin> authenticationUser(@RequestBody Optional<UserLogin> user) {
        return service.loginUser(user)
                .map(resp -> ResponseEntity.ok(resp))
                .orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
    }

    @PostMapping("/register")
    public ResponseEntity<UserData> postUser(
            @Valid @RequestBody UserData userData) {
        return service.registerUser(userData)
                .map(resp -> ResponseEntity.status(HttpStatus.CREATED).body(resp))
                .orElse(ResponseEntity.status(HttpStatus.BAD_REQUEST).build());
    }

    @PutMapping("/update")
    public ResponseEntity<UserData> putUser(
            @Valid @RequestBody UserData userData){
        return service.updateUser(userData)
                .map(resp -> ResponseEntity.status(HttpStatus.OK).body(resp))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }



}
