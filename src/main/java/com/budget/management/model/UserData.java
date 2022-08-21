package com.budget.management.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "tb_user_data")
@Getter
@Setter
public class UserData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "The property name is required.")
    private String name;

    @NotNull(message = "The property user is required.")
    @Email(message = "The property user should be a valid email.")
    private String user;

    @NotBlank(message = "The property password is required.")
    @Size(min = 8, message = "The password must be at least 8 characters")
    private String password;

    @OneToMany(mappedBy = "userData", cascade = CascadeType.REMOVE)
    @JsonIgnoreProperties("userData")
    private List<Expense> expense;

    @OneToMany(mappedBy = "userData", cascade = CascadeType.REMOVE)
    @JsonIgnoreProperties("userData")
    private List<Income> income;





}
