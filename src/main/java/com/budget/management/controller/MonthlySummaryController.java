 package com.budget.management.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.budget.management.model.MonthlySummary;
import com.budget.management.service.MonthlySummaryService;

@RestController
@RequestMapping("/summary")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class MonthlySummaryController {

	MonthlySummaryService monthlySummaryService;

	public MonthlySummaryController(MonthlySummaryService monthlySummaryService) {
		this.monthlySummaryService = monthlySummaryService;
	}

	@GetMapping(path = "{year}/{month}")
	public ResponseEntity<MonthlySummary> getMonthlySummary(@PathVariable Integer month, @PathVariable Integer year){

		return ResponseEntity.ok(monthlySummaryService.getSummary(year, month));
	}
}
