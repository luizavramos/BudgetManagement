package com.budget.management.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.swagger.v3.oas.annotations.media.Schema;


import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "tb_userdata")
public class UserData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "The property name is required.")
    private String name;
    
    @Schema(example = "email@email.com.br")
    @NotNull(message = "The property user is required.")
    @Email(message = "The property user should be a valid email.")
    private String user;

    @NotBlank(message = "The property password is required.")
    @Size(min = 8, message = "The password must be at least 8 characters")
    private String secret;

    @OneToMany(mappedBy = "userData", cascade = CascadeType.REMOVE)
    @JsonIgnoreProperties("userData")
    private List<Expense> expense;

    @OneToMany(mappedBy = "userData", cascade = CascadeType.REMOVE)
    @JsonIgnoreProperties("userData")
    private List<Income> income;


    public UserData(Long id, String name, String user, String secret) {
        this.id = id;
        this.name = name;
        this.user = user;
        this.secret = secret;
    }

    public UserData() {
    }


    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public List<Expense> getExpense() {
        return expense;
    }

    public void setExpense(List<Expense> expense) {
        this.expense = expense;
    }

    public List<Income> getIncome() {
        return income;
    }

    public void setIncome(List<Income> income) {
        this.income = income;
    }
}
