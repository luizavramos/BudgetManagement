package com.challenge.alura.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.server.ResponseStatusException;

import com.challenge.alura.model.Income;
import com.challenge.alura.repository.IncomeRepository;


@Service
public class IncomeService {
	
	@Autowired
	IncomeRepository incomeRepository;

	public IncomeService(IncomeRepository repository) {
		this.incomeRepository = repository;
	}

	public Income postIncome(Income input) throws Exception {
		
		isValidDescription(input);
		
		return incomeRepository.save(input);
	}
	
	public ResponseEntity<List<Income>> getAll(){
		return ResponseEntity.ok(incomeRepository.findAll());
	}
	

	public ResponseEntity<Income> getById( long id){
		return incomeRepository.findById(id).map(resposta -> ResponseEntity.ok(resposta)).orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());

	}
	

	public ResponseEntity<Income> put(Long id, Income input) throws Exception{
		
		isValidDescription(input);
		
		return incomeRepository.findById(id).map(resposta -> {
			input.setId(id);
			return ResponseEntity.status(HttpStatus.CREATED).body(incomeRepository.save(input));
		}).orElse(null);
		
	}
	
	
	private void isValidDescription(Income input) throws Exception {
		for (Income income : incomeRepository.findAllByDateBetween(
				input.getDate().withDayOfMonth(1), 
				input.getDate().withDayOfMonth(input.getDate().lengthOfMonth()))) {
			if (input.getDescription().equals(income.getDescription())) 
				throw new Exception("Invalid Description");
		}
	}
	
	public void delete( Long id) {
		Optional<Income> income = incomeRepository.findById(id);
		
		if(income.isEmpty())
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		
		incomeRepository.deleteById(id);
	}
	
	public ResponseEntity<List<Income>> getByDescription(  String description){
		return ResponseEntity.ok(incomeRepository.findAllByDescriptionContainingIgnoreCase(description));
	}
	
	public ResponseEntity<List<Income>> getByMonthIncome( int year,  int month){
		LocalDate minDate = LocalDate.of(year, month, 1);
		LocalDate maxDate = LocalDate.of(year, month, minDate.lengthOfMonth());
		
		return ResponseEntity.ok(incomeRepository.findAllByDateBetween(minDate, maxDate));
	}
	
}
