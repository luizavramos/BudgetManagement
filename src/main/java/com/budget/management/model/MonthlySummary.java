package com.budget.management.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
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

}