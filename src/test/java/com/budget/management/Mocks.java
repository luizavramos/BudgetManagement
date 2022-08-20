package com.budget.management;

import com.budget.management.model.Category;
import com.budget.management.model.Expense;
import com.budget.management.model.Income;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Mocks {

    public static Expense getExpense(){
        return new Expense(0, "description", 10.0, LocalDate.now(), getCategory());
    }

    public static Income getIncome(){
        return new Income(0, "description", 10.0, LocalDate.now());
    }

    public static List<Expense> getExpenseList(int size){
        List<Expense> expenses = new ArrayList<>();

        for (int i = 0; i < size; i++)
             expenses.add(getExpense());

        return expenses;

    }
    public static List<Income> getIncomeList(int size){
        List<Income> incomes = new ArrayList<>();

        for (int i = 0; i < size; i++)
           incomes.add(getIncome());

        return incomes;

    }

    public static Category getCategory(){
        return new Category(0L, null, null, null);
    }

}
