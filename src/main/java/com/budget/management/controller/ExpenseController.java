package com.budget.management.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.budget.management.model.Expense;
import com.budget.management.service.ExpenseService;

@RestController
@RequestMapping("/expense")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ExpenseController {

	@Autowired 
	private ExpenseService expenseService;
	
	@PostMapping
	public ResponseEntity<Expense> post(@Valid @RequestBody Expense expense) throws Exception{
		return ResponseEntity.status(HttpStatus.CREATED).body(expenseService.postExpense(expense));
	}
	
	@GetMapping
	public ResponseEntity<List<Expense>> getAll(){
		return expenseService.getAll();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Expense> getById(@PathVariable long id){
		return expenseService.getById(id);
	}
	
	@GetMapping("/description/{description}")
	public ResponseEntity<List<Expense>> getByDescription(@PathVariable String description)
	{
		return expenseService.getByDescription(description);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Expense> put(@Valid @RequestBody Expense expense, @PathVariable(value = "id") Long id) throws Exception{
		return expenseService.putExpense(id, expense);
	}
	
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/{id}")
	public void delete(@PathVariable long id) {
		expenseService.delete(id);
	}
	
	@GetMapping("/{year}/{month}")
	public ResponseEntity<List<Expense>> getByMonth( @PathVariable int year, @PathVariable int month){
		return expenseService.getByMonth(year, month);
	}
}
