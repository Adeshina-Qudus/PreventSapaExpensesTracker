package sapa.prevent.services;

import sapa.prevent.data.models.Budget;
import sapa.prevent.data.models.Category;

import java.math.BigDecimal;

public interface BudgetService {

    Budget addBudget(BigDecimal budgetAmount, Category category, String id);
}
