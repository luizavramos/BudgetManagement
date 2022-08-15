package com.challenge.alura.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MonthlySummary {
	
	@JsonProperty
	List<Income> incomes;

	@JsonProperty
	List<Expense> expenses;
	
	@JsonProperty
	Double totalValueExpense;
	
	@JsonProperty
	Double totalValueReceipt;
	
	@JsonProperty
	Double balance;
	
	@JsonProperty
	List<ValueCategory> valueCategory;
	

	public MonthlySummary(List<Income> incomes, List<Expense> expenses, Double totalValueExpense, Double totalValueReceipt, Double balance, List<ValueCategory> aux) {
		this.incomes = incomes;
		this.expenses = expenses;
		this.totalValueExpense = totalValueExpense;
		this.totalValueReceipt = totalValueReceipt;
		this.balance = balance;
		this.valueCategory = aux;
	}

}