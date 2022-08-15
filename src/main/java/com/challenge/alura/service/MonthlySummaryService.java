package com.challenge.alura.service;

import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.challenge.alura.model.MonthlySummary;
import com.challenge.alura.model.ValueCategory;
import com.challenge.alura.repository.ExpenseRepository;
import com.challenge.alura.repository.IncomeRepository;
 

@Service
public class MonthlySummaryService {

	@Autowired
	IncomeService incomeService;
	
	@Autowired
	ExpenseService expenseService;
	
	@Autowired
	ExpenseRepository expenseRepository;
	
	@Autowired
	IncomeRepository incomeRepository;
	

	public MonthlySummary getSummary(Integer year, Integer month){
			
		LocalDate date = LocalDate.of(year,month,1);
        LocalDate first = date.with(TemporalAdjusters.firstDayOfMonth());
        LocalDate last = date.with(TemporalAdjusters.lastDayOfMonth());
		Double sumExpenses = expenseRepository.sumExpense(first, last).get();
		Double sumIncome = incomeRepository.sumIncome(first, last).get();
        Double balances = sumIncome - sumExpenses;
        
        List<ValueCategory> aux = expenseRepository.countTotalDespesaByCategoryBetweenData(first, last);
		return new MonthlySummary (
				incomeService.getByMonthIncome(year, month).getBody(),
				expenseService.getByMonth(year, month).getBody(),
				expenseRepository.sumExpense(first, last).get(),
				incomeRepository.sumIncome(first, last).get(),
				balances, aux
				);
				
	}
	
	
}
