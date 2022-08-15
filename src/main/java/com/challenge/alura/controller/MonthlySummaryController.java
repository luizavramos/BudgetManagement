 package com.challenge.alura.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.challenge.alura.model.MonthlySummary;
import com.challenge.alura.service.MonthlySummaryService;

@RestController
@RequestMapping("/summary")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class MonthlySummaryController {

	@Autowired
	MonthlySummaryService monthlySummaryService;
	
	@GetMapping(path = "{year}/{month}")
	public ResponseEntity<MonthlySummary> getMonthlySummary(@PathVariable Integer month, @PathVariable Integer year){

		return ResponseEntity.ok(monthlySummaryService.getSummary(year, month));
	}
}
