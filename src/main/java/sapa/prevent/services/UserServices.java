package sapa.prevent.services;

import sapa.prevent.dtos.request.*;

import java.math.BigDecimal;

public interface UserServices {
    void register(RegisterRequest registerRequest);

    void login(LoginRequest loginRequest);


    void addIncome(AddIncomeRequest addIncomeRequest);

    BigDecimal getBalance(String email);

    void addExpenses(AddExpensesRequest addExpensesRequest);

    void addBudget(AddBudgetRequest addBudgetRequest);
}
