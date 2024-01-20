package sapa.prevent.services;

import sapa.prevent.data.models.Category;
import sapa.prevent.data.models.Expenses;

import java.math.BigDecimal;

public interface ExpensesService {


    Expenses addExpenses(BigDecimal amount, Category category, String id, BigDecimal balance);
}
