package sapa.prevent.services;

import sapa.prevent.data.models.Category;
import sapa.prevent.data.models.Income;

import java.math.BigDecimal;

public interface IncomeService {

    Income addIncome(BigDecimal income, Category category, String id, BigDecimal balance);
}
