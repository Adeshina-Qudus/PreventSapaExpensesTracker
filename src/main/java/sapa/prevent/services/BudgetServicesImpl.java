package sapa.prevent.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sapa.prevent.data.models.Budget;
import sapa.prevent.data.models.Category;
import sapa.prevent.data.repositories.BudgetRepository;

import java.math.BigDecimal;

@Service
public class BudgetServicesImpl implements  BudgetService{

    @Autowired
    private BudgetRepository budgetRepository;
    @Override
    public Budget addBudget(BigDecimal budgetAmount, Category category, String id) {
        Budget budget = new Budget();
        budget.setId(id);
        budget.setBudgetName(category.getNameOfCategory());
        budget.setAmount(budgetAmount);
        budgetRepository.save(budget);
        return budget;
    }
}
