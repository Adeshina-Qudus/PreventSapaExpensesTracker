package sapa.prevent.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sapa.prevent.data.models.Category;
import sapa.prevent.data.models.Income;
import sapa.prevent.data.repositories.IncomeRepository;

import java.math.BigDecimal;
import java.util.List;

@Service
public class IncomeServiceImpl  implements IncomeService{

    @Autowired
    private IncomeRepository incomeRepository;
    @Autowired
    private CategoryService categoryService;
    @Override
    public Income addIncome(BigDecimal income, Category category, String id) {
        Income income1 = new Income();
        income1.setId(id);
        income1.setAmountOfIncome(income);
        income1.setCategory(category);
        categoryService.addCategory(category);
        incomeRepository.save(income1);
        return income1;
    }

    @Override
    public List<Income> getAllIncome() {
        return incomeRepository.findAll();
    }
}
