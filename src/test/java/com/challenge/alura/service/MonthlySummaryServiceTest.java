package com.challenge.alura.service;

import com.challenge.alura.model.Income;
import com.challenge.alura.model.MonthlySummary;
import com.challenge.alura.repository.ExpenseRepository;
import com.challenge.alura.repository.IncomeRepository;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.internal.stubbing.BaseStubbing;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static org.mockito.Mockito.*;


public class MonthlySummaryServiceTest {

    private MonthlySummaryService service;

    @Mock
    ExpenseRepository expenseRepository;

    @Mock
    IncomeRepository incomeRepository;

    @Mock
    IncomeService incomeService;

    @Mock
    ExpenseService expenseService;

    @BeforeEach
    public void initializer() {
        expenseRepository = Mockito.mock(ExpenseRepository.class);
        incomeRepository = Mockito.mock(IncomeRepository.class);
        expenseService = Mockito.mock(ExpenseService.class);
        incomeService = Mockito.mock(IncomeService.class);

        this.service = new MonthlySummaryService(incomeService, expenseService, expenseRepository, incomeRepository);
    }

    @Test
    void getSummarySuccess (){

        when(incomeRepository.sumIncome(Mockito.any(), Mockito.any()))
                .thenReturn(Optional.of(0D));

        when(expenseRepository.sumExpense(Mockito.any(), Mockito.any()))
                .thenReturn(Optional.of(0D));

        when(incomeService.getByMonthIncome(2022, 8))
                .thenReturn((ResponseEntity.of(Optional.of(new ArrayList<>()))));

        when(expenseRepository.countTotalDespesaByCategoryBetweenData(Mockito.any(), Mockito.any()))
                .thenReturn(new ArrayList<>());

        when(expenseService.getByMonth(2022, 8))
                .thenReturn((ResponseEntity.of(Optional.of(new ArrayList<>()))));

        MonthlySummary monthlySummary = service.getSummary(2022, 8);

        Mockito.verify(expenseRepository, times(2)).sumExpense(Mockito.any(), Mockito.any());

        monthlySummary.getExpenses().stream().forEach(
                expense -> Asserts.assertExpense(expense));

        monthlySummary.getExpenses().stream().forEach(
                expense -> Asserts.assertExpenseWithNewMonth(expense));

    }
}
