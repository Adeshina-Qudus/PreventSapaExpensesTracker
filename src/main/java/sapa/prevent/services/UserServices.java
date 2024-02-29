package sapa.prevent.services;

import sapa.prevent.data.models.Expenses;
import sapa.prevent.data.models.History;
import sapa.prevent.data.models.Income;
import sapa.prevent.dtos.request.*;

import java.math.BigDecimal;
import java.util.List;

public interface UserServices {
    void registration(RegistrationRequest registerRequest);
    void login(LoginRequest loginRequest);
    void addIncome(AddIncomeRequest addIncomeRequest);
    BigDecimal getBalance(String email);
    void addExpenses(AddExpensesRequest addExpensesRequest);
    void addBudget(AddBudgetRequest addBudgetRequest);
    History getHistory(String mail);
    List<Income> getAllIncomeList(String mail);
    List<Expenses> getAllExpenses(String mail);

}
