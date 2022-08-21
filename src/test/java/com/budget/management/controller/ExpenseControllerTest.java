package com.budget.management.controller;

import com.budget.management.Asserts;
import com.budget.management.Mocks;
import com.budget.management.model.Category;
import com.budget.management.model.Expense;
import com.budget.management.service.ExpenseService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ExpenseControllerTest {

    private ExpenseController expenseController;

    @Mock
    ExpenseService expenseService;

    @BeforeEach
    public void initializer() {
        expenseService = Mockito.mock(ExpenseService.class);
        this.expenseController = new ExpenseController(expenseService);
    }

    @Test
    void getAllSuccess() {
        when(expenseService.getAll())
                .thenReturn(ResponseEntity.of(Optional.of(Mocks.getExpenseList(2))));

        ResponseEntity<List<Expense>> answer = expenseController.getAll();

        verify(expenseService, times(1)).getAll();

    }

    @Test
    void getAllZero() {
        when(expenseService.getAll())
                .thenReturn(ResponseEntity.of(Optional.of(new ArrayList<>())));

        ResponseEntity<List<Expense>> answer = expenseController.getAll();

        assertEquals(0 , answer.getBody().size());
    }

    @Test
    void getByIdSuccess () {
        when(expenseService.getById(Mockito.anyLong()))
                .thenReturn(ResponseEntity.of(Optional.of(Mocks.getExpense())));

        ResponseEntity<Expense> answer = expenseController.getById(0);

        Assert.notNull(answer);

    }

    @Test
    void getByIdFail (){
        when(expenseService.getById(Mockito.anyLong()))
                .thenReturn(ResponseEntity.of(Optional.empty()));
        ResponseEntity<Expense> answer = expenseController.getById(0);

        Assert.isNull(answer.getBody());
    }

    @Test
    void getByDescriptionSuccess(){
        when(expenseService.getByDescription(anyString()))
                .thenReturn(ResponseEntity.of(Optional.of(Mocks.getExpenseList(2))));

        ResponseEntity<List<Expense>> answer = expenseController.getByDescription("description");

        assertEquals(2 , answer.getBody().size());

    }

    @Test
    void getByDescriptionFail(){
        when(expenseService.getByDescription(anyString()))
                .thenReturn(ResponseEntity.of(Optional.of(new ArrayList<>())));

        ResponseEntity<List<Expense>> answer = expenseController.getByDescription(anyString());

        assertEquals(0, answer.getBody().size());
    }

    @Test
    void getByMonthSuccess() {
        when(expenseService.getByMonth(anyInt(), anyInt()))
                .thenReturn(ResponseEntity.of(Optional.of(Mocks.getExpenseList(2))));

        ResponseEntity<List<Expense>> answer = expenseController.getByMonth(anyInt(), anyInt());

        assertEquals(2, answer.getBody().size());
    }

    @Test
    void getByMonthFail() {
        when(expenseService.getByMonth(anyInt(), anyInt()))
                .thenReturn(ResponseEntity.of(Optional.of(new ArrayList<>())));

        ResponseEntity<List<Expense>> answer = expenseController.getByMonth(anyInt(), anyInt());

        assertEquals(0, answer.getBody().size());
    }

    @Test
    void postExpenseSuccess() throws Exception{
        when(expenseService.postExpense(any())).thenReturn(new Expense());

        ResponseEntity<Expense> answer = expenseController.post(any());

        Assert.notNull(answer);
    }

    @Test
    void postExpenseFail() throws Exception{
        when(expenseService.postExpense(any())).thenReturn(null);

        ResponseEntity<Expense> answer = expenseController.post(any());

        Assert.isNull(answer.getBody());

    }

    @Test
    void putExpenseSuccess() throws Exception{
        when(expenseService.putExpense(anyLong(), any()))
                .thenReturn(ResponseEntity.of(Optional.of(Mocks.getExpense())));

        ResponseEntity<Expense> answer = expenseController.put(any(), anyLong());

        Assert.notNull(answer);
    }

    @Test
    void putExpenseFail() throws Exception{
        when(expenseService.putExpense(anyLong(), any()))
                .thenReturn(ResponseEntity.of(Optional.empty()));

        ResponseEntity<Expense> answer = expenseController.put(null, null);

        Assert.isNull(answer);
    }

    @Test
    void deleteSuccess() {
        doNothing().when(expenseService).delete(anyLong());

        expenseController.delete(anyLong());

        Mockito.verify(expenseService, times(1)).delete(anyLong());
    }

    @Test
    void deleteFail(){
        doNothing().when(expenseService).delete(anyLong());

        expenseController.delete(anyLong());

        verify(expenseService, times(0)).delete(isNull());

    }

}
