package sapa.prevent.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sapa.prevent.data.models.Budget;
import sapa.prevent.data.models.Expenses;
import sapa.prevent.data.models.History;
import sapa.prevent.data.models.Income;
import sapa.prevent.data.repositories.HistoryRepository;

import java.util.ArrayList;
import java.util.List;
@Service
public class HistoryServiceImpl implements HistoryService{
    @Autowired
    private HistoryRepository historyRepository;
    private final History history = new History();
    @Override
    public History addToHistory(ArrayList<Income> incomeList, List<Expenses> expensesList, Budget budget) {
        for (Income income : incomeList) {
            history.getAllTransaction().add(income);
            historyRepository.save(history);
        }
        for (Expenses expenses : expensesList){
            history.getAllTransaction().add(expenses);
            historyRepository.save(history);
        }
        history.getAllTransaction().add(budget);
        return history;
    }

    @Override
    public History getAllIncome(ArrayList<Income> incomeList) {
        for (Income income : incomeList) {
            history.getIncomeList().add(income);
            historyRepository.save(history);
        }
        return history;
    }
    @Override
    public History getAllExpenses(List<Expenses> expensesList) {
        for (Expenses expenses : expensesList) {
            history.getExpensesList().add(expenses);
            historyRepository.save(history);
        }
        return history;
    }

}
