package sapa.prevent.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sapa.prevent.data.models.Expenses;
import sapa.prevent.data.models.History;
import sapa.prevent.data.models.Income;
import sapa.prevent.data.repositories.HistoryRepository;

import java.util.Collections;
import java.util.List;
@Service
public class HistoryServiceImpl implements HistoryService{
    @Autowired
    private HistoryRepository historyRepository;
    private final History history = new History();

    @Override
    public void saveAllIncome(List<Income> incomeList) {
        history.setIncomeList(incomeList);
        history.setAllTransaction(Collections.singletonList(history.getIncomeList()));
        historyRepository.save(history);
    }
    @Override
    public History saveAllExpenses(List<Expenses> expensesList) {
        history.setExpensesList(expensesList);
        history.setAllTransaction(Collections.singletonList(history.getIncomeList()));
        historyRepository.save(history);
        return history;
    }

    @Override
    public History getHistory() {
        return history;
    }

}
