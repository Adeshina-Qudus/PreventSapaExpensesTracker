package sapa.prevent.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sapa.prevent.data.models.Category;
import sapa.prevent.data.models.Expenses;
import sapa.prevent.data.repositories.ExpensesRepository;

import java.math.BigDecimal;

@Service
public class ExpensesServicesImpl implements ExpensesService{
    @Autowired
    private ExpensesRepository expensesRepository;

    @Override
    public Expenses addExpenses(BigDecimal amount, Category category, String id, BigDecimal balance) {
        Expenses expenses = new Expenses();
        expenses.setId(id);
        expenses.setCategory(category);
        expenses.setAmount(balance.subtract(amount));
        expensesRepository.save(expenses);
        return expenses;
    }
}
