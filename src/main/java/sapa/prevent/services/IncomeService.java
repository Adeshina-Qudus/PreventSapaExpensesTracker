package sapa.prevent.services;

import sapa.prevent.data.models.Category;
import sapa.prevent.data.models.Income;

import java.math.BigDecimal;
import java.util.List;

public interface IncomeService {

    Income addIncome(BigDecimal income, Category category, String id);

    List<Income> getAllIncome();
}
