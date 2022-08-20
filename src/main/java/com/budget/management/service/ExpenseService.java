package com.budget.management.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.budget.management.repository.ExpenseRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.budget.management.model.Category;
import com.budget.management.model.Expense;

@Service
public class ExpenseService {
	ExpenseRepository expenseRepository;
	
	public ExpenseService(ExpenseRepository repository) {
		this.expenseRepository = repository;
	}

	public Expense postExpense (Expense expense) throws Exception {
		
		isValidDescription(expense);
		
		if(expense.getCategory() == null)
			expense.setCategory(getCategoryDefault());
		
		return expenseRepository.save(expense);
	}
	
	private Category getCategoryDefault() {
		Category category = new Category();
		
		category.setId(8L);
		
		return category;
	}
	private void isValidDescription(Expense input) throws Exception {
		for (Expense expense : expenseRepository.findAllByDateBetween(
				input.getDate().withDayOfMonth(1), 
				input.getDate().withDayOfMonth(input.getDate().lengthOfMonth()))) {
			if (input.getDescription().equals(expense.getDescription())) 
				throw new Exception("Invalid Description");
		}
	}

	public ResponseEntity<List<Expense>> getAll(){
		return ResponseEntity.ok(expenseRepository.findAll());
	}

	public ResponseEntity<Expense> getById( long id){
		return expenseRepository.findById(id).map(resposta -> ResponseEntity.ok(resposta)).orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());

	}
	
	public ResponseEntity<Expense> putExpense( Long id, Expense input) throws Exception{
		
		isValidDescription(input);
			
		if(input.getCategory() == null)
			input.setCategory(getCategoryDefault());
		
		return expenseRepository.findById(id).map(resposta -> {
			input.setId(id);
			return ResponseEntity.status(HttpStatus.CREATED).body(expenseRepository.save(input));
		}).orElse(null);
		
	}

	public void delete( Long id) {
		Optional<Expense> expense = expenseRepository.findById(id);
		
		if(expense.isEmpty())
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		
		expenseRepository.deleteById(id);
	}

	
	public ResponseEntity<List<Expense>> getByDescription( String description){
		return ResponseEntity.ok(expenseRepository.findAllByDescriptionContainingIgnoreCase(description));
	}

	public ResponseEntity<List<Expense>> getByMonth( int year,  int month){
		LocalDate minDate = LocalDate.of(year, month, 1);
		LocalDate maxDate = LocalDate.of(year, month, minDate.lengthOfMonth());
		
		return ResponseEntity.ok(expenseRepository.findAllByDateBetween(minDate, maxDate));
	}
}

