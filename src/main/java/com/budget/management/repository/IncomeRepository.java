package com.budget.management.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.budget.management.model.Income;

public interface IncomeRepository extends JpaRepository<Income, Long>{
	
	List<Income> findAllByDateBetween(LocalDate start, LocalDate end);
	
	public List<Income> findAllByDescriptionContainingIgnoreCase(@Param("description")String description);
	
	@Query (value = "select sum(r.value) from Income r " +
            "where r.date BETWEEN :firstDay AND :lastDay")
    Optional<Double> sumIncome (@Param("firstDay") LocalDate firstDay, @Param("lastDay") LocalDate lastDay);
	
	
}