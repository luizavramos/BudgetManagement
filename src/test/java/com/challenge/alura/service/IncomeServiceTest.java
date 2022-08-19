package com.challenge.alura.service;

import com.challenge.alura.model.Category;
import com.challenge.alura.model.Expense;
import com.challenge.alura.model.Income;
import com.challenge.alura.repository.ExpenseRepository;
import com.challenge.alura.repository.IncomeRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class IncomeServiceTest {

    private IncomeService service;

    @Mock
    IncomeRepository repository;

    @BeforeEach
    public void initializer() {
        repository = Mockito.mock(IncomeRepository.class);
        this.service = new IncomeService(repository);
    }

    @Test
    void getAllSuccess(){

        when(repository.findAll()).thenReturn(getIncomeList());

        ResponseEntity<List<Income>> answer = service.getAll();

        assertEquals(2, answer.getBody().size());
    }

    @Test
    void getAllFail(){
        when(repository.findAll()).thenReturn(new ArrayList<Income>());

        ResponseEntity<List<Income>> answer = service.getAll();

        assertEquals(0, answer.getBody().size());
    }

    @Test
    void findByIdSuccess() {
        when(repository.findById(Mockito.anyLong())).thenReturn(Optional.of(new Income()));

        ResponseEntity<Income> answer = service.getById(0);

        Assert.notNull(answer);
    }

    @Test
    void findByIdFail(){
        when(repository.findById(Mockito.anyLong())).thenReturn(Optional.empty());

        ResponseEntity<Income> answer = service.getById(0);

        Assert.isNull(answer.getBody());
    }

    @Test
    void getByDescriptionSuccess(){
        when(repository.findAllByDescriptionContainingIgnoreCase(Mockito.anyString()))
                .thenReturn(getIncomeList());

        ResponseEntity<List<Income>> answer = service.getByDescription("Test");

        assertEquals(2, answer.getBody().size());
    }

    @Test
    void getByDescriptionFail(){
        when(repository.findAllByDescriptionContainingIgnoreCase(Mockito.anyString()))
                .thenReturn(new ArrayList<Income>());

        ResponseEntity<List<Income>> answer = service.getByDescription("Test");

        assertEquals(0, answer.getBody().size());
    }

    @Test
    void getByMonthSuccess(){
        when(repository.findAllByDateBetween(Mockito.any(), Mockito.any()))
                .thenReturn(getIncomeList());
        ResponseEntity<List<Income>> answer = service.getByMonthIncome(2022,8);

        assertEquals(2, answer.getBody().size());
    }

    @Test
    void getByMonthFail(){
        when(repository.findAllByDateBetween(Mockito.any(), Mockito.any()))
                .thenReturn(new ArrayList<Income>());

        ResponseEntity<List<Income>> answer = service.getByMonthIncome(2022,8);

        assertEquals(0, answer.getBody().size());
    }

    @Test
    void deleteSuccess(){
        when(repository.findById(Mockito.anyLong()))
                .thenReturn(Optional.of(new Income()));

        doNothing().when(repository).deleteById(Mockito.anyLong());

        service.delete(Mockito.anyLong());

        Mockito.verify(repository, times(1)).findById(Mockito.anyLong());

    }

    @Test
    void deleteFail(){
        when(repository.findById(Mockito.anyLong()))
                .thenReturn(Optional.of(new Income()));

        doNothing().when(repository).deleteById(Mockito.anyLong());

        service.delete(Mockito.anyLong());

        assertThrows(ResponseStatusException.class, () -> service.delete(null));

    }
    @Test
    void postIncomeSuccess() throws Exception{
        Income income = Mockito.mock(Income.class);
        when(income.getDate()).thenReturn(LocalDate.now());

        when(repository.save(Mockito.any()))
                .thenReturn(income);

        when(repository.findAllByDateBetween(Mockito.any(), Mockito.any()))
                .thenReturn(new ArrayList<Income>());

        service.postIncome(income);

        Mockito.verify(repository, times(1)).save(Mockito.any());
        Mockito.verify(repository, times(1)).findAllByDateBetween(Mockito.any(), Mockito.any());
    }

    @Test
    void postIncomeFail() throws Exception{
        Income income = Mockito.mock(Income.class);

        when(income.getDate()).thenReturn(LocalDate.now());
        when(income.getDescription()).thenReturn("Test");

        when(repository.save(Mockito.any()))
                .thenReturn(income);

        when(repository.findAllByDateBetween(Mockito.any(), Mockito.any()))
                .thenReturn(getIncomeList());

        assertThrows(Exception.class, () -> service.postIncome(income));
    }

    @Test
    void putSuccess() throws Exception{
        Income income = Mockito.mock(Income.class);
        when(income.getDate()).thenReturn(LocalDate.now());

        when(repository.save(Mockito.any()))
                .thenReturn(income);

        when(repository.findById(Mockito.anyLong()))
                .thenReturn(Optional.of(income));

        when(repository.findAllByDateBetween(Mockito.any(), Mockito.any()))
                .thenReturn(new ArrayList<Income>());

        service.put(1L, income);

        Mockito.verify(repository, times(1)).save(Mockito.any());
        Mockito.verify(repository, times(1)).findAllByDateBetween(Mockito.any(), Mockito.any());
    }

    @Test
    void putFail() throws Exception{

        Income income = Mockito.mock(Income.class);

        when(income.getDate()).thenReturn(LocalDate.now());
        when(income.getDescription()).thenReturn("Test");

        when(repository.save(Mockito.any()))
                .thenReturn(income);

        when(repository.findAllByDateBetween(Mockito.any(), Mockito.any()))
                .thenReturn(getIncomeList());

        assertThrows(Exception.class, () -> service.put(1L, income));
    }


    private ArrayList getIncomeList() {

        ArrayList<Income> incomes = new ArrayList<Income>();

        incomes.add(
                new Income(0, "Test", 10.0, LocalDate.now()));
        incomes.add(
                new Income(0, "Test 2", 10.0, LocalDate.now()));

        return incomes;

    }
}
