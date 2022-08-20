package com.budget.management.service;

import com.budget.management.Mocks;
import com.budget.management.model.Category;
import com.budget.management.repository.ExpenseRepository;
import com.budget.management.model.Expense;
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


public class ExpenseServiceTest {

	private ExpenseService service;

	@Mock
	ExpenseRepository repository;

	@BeforeEach
	public void initializer() {
		repository = Mockito.mock(ExpenseRepository.class);
		this.service = new ExpenseService(repository);
	}

	@Test
	void findByIdSuccess() {
		when(repository.findById(Mockito.anyLong()))
				.thenReturn(Optional.of(new Expense()));

		ResponseEntity<Expense> answer = service.getById(0);
		Assert.notNull(answer);
	}

	@Test
	void findByIdFail() {
		when(repository.findById(Mockito.anyLong()))
				.thenReturn(Optional.empty());

		ResponseEntity<Expense> answer = service.getById(0);
		Assert.isNull(answer.getBody());
	}

	@Test
	void getAllSuccess() {

		when(repository.findAll())
				.thenReturn(Mocks.getExpenseList(2));

		ResponseEntity<List<Expense>> answer = service.getAll();
		assertEquals(2, answer.getBody().size());

	}

	@Test
	void getAllFail() {
		when(repository.findAll()).thenReturn(new ArrayList<Expense>());

		ResponseEntity<List<Expense>> answer = service.getAll();
		assertEquals(0, answer.getBody().size());
	}

	@Test
	void getByDescriptionSuccess() {

		when(repository.findAllByDescriptionContainingIgnoreCase(Mockito.anyString()))
				.thenReturn(Mocks.getExpenseList(2));

		ResponseEntity<List<Expense>> answer = service.getByDescription("description");

		assertEquals(2, answer.getBody().size());

	}

	@Test
	void getByDescriptionZero() {

		when(repository.findAllByDescriptionContainingIgnoreCase(Mockito.anyString()))
				.thenReturn(new ArrayList<Expense>());

		ResponseEntity<List<Expense>> answer = service.getByDescription("Test");

		assertEquals(0, answer.getBody().size());
	}

	@Test
	void getByMonthSuccess() {
		when(repository.findAllByDateBetween(Mockito.any(), Mockito.any()))
				.thenReturn(Mocks.getExpenseList(2));

		ResponseEntity<List<Expense>> answer = service.getByMonth(2022, 8);

		assertEquals(2, answer.getBody().size());
	}

	@Test
	void getByMonthZero() {
		when(repository.findAllByDateBetween(Mockito.any(), Mockito.any()))
				.thenReturn(new ArrayList<>());

		ResponseEntity<List<Expense>> answer = service.getByMonth(2022, 8);

		assertEquals(0, answer.getBody().size());
	}

	@Test
	void postExpenseSuccess() throws Exception {

		Expense expense = Mockito.mock(Expense.class);

		when(expense.getCategory()).thenReturn(new Category());
		when(expense.getDate()).thenReturn(LocalDate.now());

		when(repository.save(Mockito.any()))
				.thenReturn(expense);

		when(repository.findAllByDateBetween(Mockito.any(), Mockito.any()))
				.thenReturn(new ArrayList<Expense>());

		service.postExpense(expense);

		Mockito.verify(expense, times(1)).getCategory();
		Mockito.verify(repository, times(1)).save(Mockito.any());
		Mockito.verify(repository, times(1)).findAllByDateBetween(Mockito.any(), Mockito.any());

	}

	@Test()
	void postExpenseFail() throws Exception {

		Expense expense = Mockito.mock(Expense.class);

		when(expense.getDate()).thenReturn(LocalDate.now());
		when(expense.getDescription()).thenReturn("description");

		when(repository.findAllByDateBetween(Mockito.any(), Mockito.any()))
				.thenReturn(Mocks.getExpenseList(2));

		assertThrows(Exception.class, () -> service.postExpense(expense));
	}

	@Test
	void postExpenseSuccessCategoryDefault() throws Exception {

		Expense expense = Mockito.mock(Expense.class);

		when(expense.getCategory()).thenReturn(null);
		when(expense.getDate()).thenReturn(LocalDate.now());
		doNothing().when(expense).setCategory(Mockito.any());

		when(repository.save(Mockito.any()))
				.thenReturn(expense);

		when(repository.findAllByDateBetween(Mockito.any(), Mockito.any()))
				.thenReturn(new ArrayList<Expense>());

		service.postExpense(expense);

		Mockito.verify(expense, times(1)).getCategory();
		Mockito.verify(repository, times(1)).save(Mockito.any());
		Mockito.verify(repository, times(1)).findAllByDateBetween(Mockito.any(), Mockito.any());

	}

	@Test
	void putExpenseSuccess() throws Exception {

		Expense expense = Mockito.mock(Expense.class);

		when(expense.getCategory()).thenReturn(new Category());
		when(expense.getDate()).thenReturn(LocalDate.now());

		when(repository.save(Mockito.any()))
				.thenReturn(expense);

		when(repository.findById(Mockito.anyLong()))
				.thenReturn(Optional.of(expense));

		when(repository.findAllByDateBetween(Mockito.any(), Mockito.any()))
				.thenReturn(new ArrayList<Expense>());

		service.putExpense(1L, expense);

		Mockito.verify(expense, times(1)).getCategory();
		Mockito.verify(repository, times(1)).save(Mockito.any());
		Mockito.verify(repository, times(1)).findAllByDateBetween(Mockito.any(), Mockito.any());

	}

	@Test()
	void putExpenseFail() throws Exception {

		Expense expense = Mockito.mock(Expense.class);

		when(expense.getDate()).thenReturn(LocalDate.now());
		when(expense.getDescription()).thenReturn("description");

		when(repository.findAllByDateBetween(Mockito.any(), Mockito.any()))
				.thenReturn(Mocks.getExpenseList(2));

		assertThrows(Exception.class, () -> service.putExpense(1L, expense));
	}

	@Test
	void putExpenseSuccessCategoryDefault() throws Exception {

		Expense expense = Mockito.mock(Expense.class);

		when(expense.getCategory()).thenReturn(null);
		when(expense.getDate()).thenReturn(LocalDate.now());
		doNothing().when(expense).setCategory(Mockito.any());

		when(repository.save(Mockito.any()))
				.thenReturn(expense);

		when(repository.findById(Mockito.anyLong()))
				.thenReturn(Optional.of(expense));

		when(repository.findAllByDateBetween(Mockito.any(), Mockito.any()))
				.thenReturn(new ArrayList<Expense>());

		service.postExpense(expense);

		Mockito.verify(expense, times(1)).getCategory();
		Mockito.verify(repository, times(1)).save(Mockito.any());
		Mockito.verify(repository, times(1)).findAllByDateBetween(Mockito.any(), Mockito.any());

	}

	@Test
	void deleteSuccess() {
		when(repository.findById(Mockito.anyLong()))
				.thenReturn(Optional.of(new Expense()));
		doNothing().when(repository).deleteById(Mockito.notNull());

		service.delete(anyLong());

		Mockito.verify(repository, times(1)).findById(anyLong());
		Mockito.verify(repository, times(1)).deleteById(notNull());
	}

	@Test
	void deleteFail() {
		when(repository.findById(Mockito.anyLong()))
				.thenReturn(Optional.of(new Expense()));
		doNothing().when(repository).deleteById(Mockito.notNull());

		service.delete(anyLong());
		assertThrows(ResponseStatusException.class, () -> service.delete(null));
	}

}

