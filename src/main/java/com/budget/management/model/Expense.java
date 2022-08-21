package com.budget.management.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_expense")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Expense {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id; 
	
	@NotBlank
	@Size(min = 3, max = 255)
	private String description;
	
	@NotNull
	private double value;
	
	@NotNull
	private LocalDate date;
	
	@ManyToOne
	@JsonIgnoreProperties("expense")
	private Category category;

	@ManyToOne
	@JsonIgnoreProperties("expense")
	private UserData userData;

	public Expense(long id, String description, double value, LocalDate date, Category category) {
		this.id = id;
		this.description = description;
		this.value = value;
		this.date = date;
		this.category = category;
		this.userData = userData;
	}
}
