package com.budget.management.controller;

import com.budget.management.Asserts;
import com.budget.management.Mocks;

import com.budget.management.model.Expense;
import com.budget.management.model.Income;
import com.budget.management.service.IncomeService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class IncomeControllerTest {

    private IncomeController incomeController;

    @Mock
    IncomeService incomeService;

    @BeforeEach
    public void initializer(){
        incomeService = Mockito.mock(IncomeService.class);
        this.incomeController =  new IncomeController(incomeService);
    }

    @Test
    void getAllSuccess(){
        when(incomeService.getAll())
                .thenReturn(ResponseEntity.of(Optional.of(Mocks.getIncomeList(2))));

        ResponseEntity<List<Income>> answer = incomeController.getAll();

        verify(incomeService, times(1)).getAll();
        assertEquals(2 , answer.getBody().size());

    }

    @Test
    void getAllZero(){
        when(incomeService.getAll())
                .thenReturn(ResponseEntity.of(Optional.of(new ArrayList<>())));

        ResponseEntity<List<Income>> answer = incomeController.getAll();


        assertEquals(0 , answer.getBody().size());
    }

    @Test
    void getByIdSuccess(){
        when(incomeService.getById(anyLong()))
                .thenReturn(ResponseEntity.of(Optional.of(Mocks.getIncome())));
        ResponseEntity<Income> answer = incomeController.getById(anyLong());

        Assert.notNull(answer);
    }

    @Test
    void getByIdFail(){
        when(incomeService.getById(anyLong()))
                .thenReturn(ResponseEntity.of(Optional.empty()));
        ResponseEntity<Income> answer = incomeController.getById(anyLong());

       Assert.isNull(answer.getBody());
    }

    @Test
    void getByDescriptionSuccess(){
        when(incomeService.getByDescription(anyString()))
                .thenReturn(ResponseEntity.of(Optional.of(Mocks.getIncomeList(2))));

        ResponseEntity<List<Income>> answer = incomeController.getByDescription("description");

        assertEquals(2 , answer.getBody().size());

    }

    @Test
    void getByDescriptionFail(){
        when(incomeService.getByDescription(anyString()))
                .thenReturn(ResponseEntity.of(Optional.of(new ArrayList<>())));

        ResponseEntity<List<Income>> answer = incomeController.getByDescription(anyString());

        assertEquals(0, answer.getBody().size());
    }

    @Test
    void getByMonthSuccess() {
        when(incomeService.getByMonthIncome(anyInt(), anyInt()))
                .thenReturn(ResponseEntity.of(Optional.of(Mocks.getIncomeList(2))));

        ResponseEntity<List<Income>> answer = incomeController.getByMonth(anyInt(), anyInt());

        assertEquals(2, answer.getBody().size());
    }

    @Test
    void getByMonthFail() {
        when(incomeService.getByMonthIncome(anyInt(), anyInt()))
                .thenReturn(ResponseEntity.of(Optional.of(new ArrayList<>())));

        ResponseEntity<List<Income>> answer = incomeController.getByMonth(anyInt(), anyInt());

        assertEquals(0, answer.getBody().size());
    }

    @Test
    void postIncomeSuccess() throws Exception{
        when(incomeService.postIncome(any())).thenReturn(new Income());

        ResponseEntity<Income> answer = incomeController.post(any());

        Assert.notNull(answer);
    }

    @Test
    void postIncomeFail() throws Exception{
        when(incomeService.postIncome(any())).thenReturn(null);

        ResponseEntity<Income> answer = incomeController.post(any());

        Assert.isNull(answer.getBody());


    }

    @Test
    void putIncomeSuccess() throws Exception{
        when(incomeService.put(anyLong(), any()))
                .thenReturn(ResponseEntity.of(Optional.of(Mocks.getIncome())));

        ResponseEntity<Income> answer = incomeController.put(any(), anyLong());

        Assert.notNull(answer);
    }

    @Test
    void putIncomeFail() throws Exception{
        when(incomeService.put(anyLong(), any()))
                .thenReturn(ResponseEntity.of(Optional.empty()));

        ResponseEntity<Income> answer = incomeController.put(null, null);

        Assert.isNull(answer);

    }

    @Test
    void deleteSuccess() {
        doNothing().when(incomeService).delete(anyLong());

        incomeController.delete(anyLong());

        Mockito.verify(incomeService, times(1)).delete(anyLong());
    }

    @Test
    void deleteFail(){
        doNothing().when(incomeService).delete(anyLong());

        incomeController.delete(anyLong());

        verify(incomeService, times(0)).delete(isNull());
    }
}
