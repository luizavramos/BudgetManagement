package com.budget.management.controller;

import com.budget.management.model.MonthlySummary;
import com.budget.management.service.ExpenseService;
import com.budget.management.service.MonthlySummaryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

class MonthlySummaryControllerTest {

    MonthlySummaryController monthlySummaryController;

    @Mock
    MonthlySummaryService monthlySummaryService;

    @BeforeEach
    public void initializer() {
        monthlySummaryService = Mockito.mock(MonthlySummaryService.class);
        this.monthlySummaryController = new MonthlySummaryController(monthlySummaryService);
    }

    @Test
   void getMonthlySummarySuccess(){
        when(monthlySummaryService.getSummary(anyInt(), anyInt()))
                .thenReturn(new MonthlySummary());
        ResponseEntity<MonthlySummary> answer = monthlySummaryController.getMonthlySummary(anyInt(), anyInt());

        verify(monthlySummaryService, times(1)).getSummary(anyInt(), anyInt());
        Assert.notNull(answer);
    }

    @Test
    void getMonthlySummaryFail(){
        when(monthlySummaryService.getSummary(anyInt(), anyInt()))
                .thenReturn(new MonthlySummary());

        ResponseEntity<MonthlySummary> answer = monthlySummaryController.getMonthlySummary(null, null);

        Assert.isNull(answer.getBody());

    }
}
