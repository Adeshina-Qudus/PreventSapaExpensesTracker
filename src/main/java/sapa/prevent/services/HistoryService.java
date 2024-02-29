package sapa.prevent.services;

import sapa.prevent.data.models.Expenses;
import sapa.prevent.data.models.History;
import sapa.prevent.data.models.Income;

import java.util.List;

public interface HistoryService {


    void saveAllIncome(List<Income> incomeList);
    History saveAllExpenses(List<Expenses> expensesList);
    History getHistory();
}
