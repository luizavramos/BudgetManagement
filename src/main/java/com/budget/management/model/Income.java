package com.budget.management.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "tb_income")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Income {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@NotBlank
	@Size(min = 3, max = 255)
	private String description;
	
	@NotNull
	private double value;
	
	@NotNull
	//@Temporal(TemporalType.DATE)
	private LocalDate date;

	@ManyToOne
	@JsonIgnoreProperties("income")
	private UserData userData;

	public Income(long id, String description, double value, LocalDate date) {
		this.id = id;
		this.description = description;
		this.value = value;
		this.date = date;
	}
}
