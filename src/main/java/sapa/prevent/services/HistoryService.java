package sapa.prevent.services;

import sapa.prevent.data.models.Budget;
import sapa.prevent.data.models.Expenses;
import sapa.prevent.data.models.History;
import sapa.prevent.data.models.Income;

import java.util.ArrayList;
import java.util.List;

public interface HistoryService {

    History addToHistory(ArrayList<Income> incomeList, List<Expenses> expensesList, Budget budget);
    History getAllIncome(ArrayList<Income> incomeList);
    History getAllExpenses(List<Expenses> expensesList);

}
