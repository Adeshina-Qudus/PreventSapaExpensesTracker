package sapa.prevent.services;

import sapa.prevent.dtos.request.AddExpensesRequest;
import sapa.prevent.dtos.request.AddIncomeRequest;
import sapa.prevent.dtos.request.RegisterRequest;
import sapa.prevent.dtos.request.LoginRequest;

import java.math.BigDecimal;

public interface UserServices {
    void register(RegisterRequest registerRequest);

    void login(LoginRequest loginRequest);


    void addIncome(AddIncomeRequest addIncomeRequest);

    BigDecimal getBalance(String email);

    void addExpenses(AddExpensesRequest addExpensesRequest);
}
