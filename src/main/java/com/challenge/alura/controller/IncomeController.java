package com.challenge.alura.controller;

import java.util.List;
import java.util.Optional;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.challenge.alura.model.Income;
import com.challenge.alura.service.IncomeService;

@RestController
@RequestMapping("/income")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class IncomeController {

	
	@Autowired 
	private IncomeService incomeService;
	
	@PostMapping
	public ResponseEntity<Income> post(@Valid @RequestBody Income income) throws Exception{
		return ResponseEntity.status(HttpStatus.CREATED).body(incomeService.postIncome(income));
	}
	
	@GetMapping
	public ResponseEntity<List<Income>> getAll(){
		return incomeService.getAll();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Income> getById(@PathVariable Long id){
		return incomeService.getById(id);
		}
	
	@GetMapping("/description/{description}")
	public ResponseEntity<List<Income>> getByDescription(@PathVariable String description)
	{
		return incomeService.getByDescription(description);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Income> put(@Valid @RequestBody Income income, @PathVariable(value = "id") Long id) throws Exception{
		return incomeService.put(id, income);
	}
	
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/{id}")
	public void delete(@PathVariable long id) {
		incomeService.delete(id);
	}
	
	@GetMapping("/{year}/{month}")
	public ResponseEntity<List<Income>> getByMonth( @PathVariable int year, @PathVariable int month){
		return incomeService.getByMonthIncome(year, month);
	}
}
	
