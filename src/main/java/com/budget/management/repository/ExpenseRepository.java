package com.budget.management.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.budget.management.model.Expense;
import com.budget.management.model.ValueCategory;


public interface ExpenseRepository extends JpaRepository<Expense, Long>{

	List<Expense> findAllByDateBetween(LocalDate start, LocalDate end);

	public List<Expense> findAllByDescriptionContainingIgnoreCase(@Param("description") String description);
	
	@Query (value = "select sum(r.value) from Expense r " +
            "where r.date BETWEEN :firstDay AND :lastDay")
    Optional<Double> sumExpense (@Param("firstDay") LocalDate firstDay, @Param("lastDay") LocalDate lastDay);
	
	
	@Query("SELECT d.category AS category, SUM(d.value) AS total FROM Expense d WHERE d.date >= :startDate AND d.date <= :endDate GROUP BY d.category")
	public List<ValueCategory> countTotalDespesaByCategoryBetweenData(LocalDate startDate, LocalDate endDate);

}
