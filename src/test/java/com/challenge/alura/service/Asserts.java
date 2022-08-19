package com.challenge.alura.service;

import com.challenge.alura.model.Category;
import com.challenge.alura.model.Expense;
import com.challenge.alura.model.Income;
import com.challenge.alura.model.MonthlySummary;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Asserts {
    @Test
    public static void assertExpense(Expense expense){
        Expense expenseDefault = new Expense();

        assertEquals(expenseDefault.getCategory(), expense.getCategory());
        assertEquals(expenseDefault.getDate(), expense.getDate());
        assertEquals(expenseDefault.getDescription(), expense.getDescription());
        assertEquals(expenseDefault.getId(), expense.getId());
        assertEquals(expenseDefault.getValue(), expense.getValue());

    }
    @Test
    public static void assertExpenseWithNewMonth(Expense expense) {
        Expense expenseDefault = new Expense();

        expenseDefault.getDate();

        assertEquals(expenseDefault.getDate().getMonthValue()
                , expense.getDate().getMonthValue());

        expense.setDate(null);
        assertExpense(expense);
    }
    @Test
    public static void assertIncome(Income income){
        Income incomeDefault = new Income();

        assertEquals(incomeDefault.getDate(), income.getDate());
        assertEquals(incomeDefault.getDescription(), income.getDescription());
        assertEquals(incomeDefault.getId(), income.getId());
        assertEquals(incomeDefault.getValue(), income.getValue());

    }
    @Test
    public static void assertCategory(Category category){
        Category categoryDefault = new Category();

        assertEquals(categoryDefault.getDescription(), category.getDescription());
        assertEquals(categoryDefault.getName(), category.getName());
        assertEquals(categoryDefault.getId(), category.getId());

    }
    @Test
    public static void assertMonthlySummary(MonthlySummary monthlySummary){
        MonthlySummary monthlySummaryDefault = new MonthlySummary();

        assertEquals(monthlySummaryDefault.getExpenses(), monthlySummary.getExpenses());
        assertEquals(monthlySummaryDefault.getBalance(), monthlySummary.getBalance());
        assertEquals(monthlySummaryDefault.getIncomes(), monthlySummary.getIncomes());
        assertEquals(monthlySummaryDefault.getTotalValueExpense(), monthlySummary.getTotalValueExpense());
        assertEquals(monthlySummaryDefault.getTotalValueReceipt(), monthlySummary.getTotalValueReceipt());
        assertEquals(monthlySummaryDefault.getValueCategory(), monthlySummary.getValueCategory());

    }
}
