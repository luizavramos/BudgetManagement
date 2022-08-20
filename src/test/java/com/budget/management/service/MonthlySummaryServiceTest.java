package com.budget.management.service;


import com.budget.management.Asserts;
import com.budget.management.model.MonthlySummary;
import com.budget.management.repository.ExpenseRepository;
import com.budget.management.repository.IncomeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.http.ResponseEntity;
import java.util.ArrayList;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertThrows;
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
        Mockito.verify(incomeRepository, times(2)).sumIncome(Mockito.any(), Mockito.any());
        Mockito.verify(incomeService, times(1)).getByMonthIncome(2022,8);
        Mockito.verify(expenseRepository, times(1)).countTotalDespesaByCategoryBetweenData(Mockito.any(), Mockito.any());
        Mockito.verify(expenseService, times(1)).getByMonth(2022,8);

        monthlySummary.getExpenses().stream().forEach(
                expense -> Asserts.assertExpense(expense));

        monthlySummary.getExpenses().stream().forEach(
                expense -> Asserts.assertExpenseWithNewMonth(expense));

    }

    @Test
    void getSummaryFail () {
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

        assertThrows(Exception.class, () -> service.getSummary(null, null));

    }
}
