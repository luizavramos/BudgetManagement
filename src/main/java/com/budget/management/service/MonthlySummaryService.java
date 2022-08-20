package com.budget.management.service;

import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.List;

import com.budget.management.repository.ExpenseRepository;
import com.budget.management.repository.IncomeRepository;
import org.springframework.stereotype.Service;

import com.budget.management.model.MonthlySummary;
import com.budget.management.model.ValueCategory;


@Service
public class MonthlySummaryService {

	IncomeService incomeService;
	ExpenseService expenseService;
	ExpenseRepository expenseRepository;
	IncomeRepository incomeRepository;

	public MonthlySummaryService(IncomeService incomeService, ExpenseService expenseService, ExpenseRepository expenseRepository, IncomeRepository incomeRepository) {
		this.incomeService = incomeService;
		this.expenseService = expenseService;
		this.expenseRepository = expenseRepository;
		this.incomeRepository = incomeRepository;
	}

	public MonthlySummaryService(){

	}


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
