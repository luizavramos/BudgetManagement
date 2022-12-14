package com.budget.management;

import com.budget.management.model.Category;
import com.budget.management.model.Income;
import com.budget.management.model.MonthlySummary;
import com.budget.management.model.Expense;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Asserts {
    @Test
    public static void assertExpense(Expense expense){
        Expense expenseDefault = Mocks.getExpense();

        assertCategory(expense.getCategory());
        assertEquals(expenseDefault.getDate(), expense.getDate());
        assertEquals(expenseDefault.getDescription(), expense.getDescription());
        assertEquals(expenseDefault.getId(), expense.getId());
        assertEquals(expenseDefault.getValue(), expense.getValue());

    }


    @Test
    public static void assertExpenseWithNewMonth(Expense expense) {
        Expense expenseDefault = Mocks.getExpense();

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
        Category categoryDefault = Mocks.getCategory();

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
