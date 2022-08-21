package com.budget.management;

import com.budget.management.model.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Mocks {

    public static Expense getExpense(){
        return new Expense(0L, "description", 10.0, LocalDate.now(), getCategory());
    }

    public static UserData getUserData(){
        return new UserData(0L, "name","user", "password");
    }
    public static UserLogin getUserLogin(){
        return new UserLogin(0L, "name", "user", "Password", "token");
    }

    public static Income getIncome(){
        return new Income(0L, "description", 10.0, LocalDate.now());
    }

    public static List<Expense> getExpenseList(int size){
        List<Expense> expenses = new ArrayList<>();

        for (int i = 0; i < size; i++)
             expenses.add(getExpense());

        return expenses;

    }

    public static List<UserData> getUserDataList(int size){
        List<UserData> userData = new ArrayList<>();

        for (int i = 0; i < size; i++)
            userData.add(getUserData());

        return userData;

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
