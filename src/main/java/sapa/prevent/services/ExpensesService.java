package sapa.prevent.services;

import sapa.prevent.data.models.Category;
import sapa.prevent.data.models.Expenses;

import java.math.BigDecimal;
import java.util.Optional;

public interface ExpensesService {


    Expenses addExpenses(BigDecimal amount, Category category, String id);


}
