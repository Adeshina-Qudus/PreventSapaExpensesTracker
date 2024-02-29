package sapa.prevent.services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sapa.prevent.data.models.*;
import sapa.prevent.data.repositories.UserRepository;
import sapa.prevent.dtos.request.*;
import sapa.prevent.exception.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import static sapa.prevent.utils.Mapper.map;

@Service
public class UserServicesImpl implements  UserServices{
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private IncomeService incomeService;
    @Autowired
    private ExpensesService expensesService;
    @Autowired
    private BudgetService budgetService;
    @Autowired
    private HistoryService historyService;
    @Override
    public void registration(RegistrationRequest registerRequest) {
        if (userExist(registerRequest.getEmail())) throw new UserAlreadyExistException(
                registerRequest.getEmail()+" Already Exist");
        User user = map(registerRequest);
        userRepository.save(user);
    }
    @Override
    public void login(LoginRequest loginRequest) {
        User foundUser = userRepository.findByEmail(loginRequest.getEmail());
        if (!userExist(loginRequest.getEmail())) throw new InvalidDetailsException(
                loginRequest.getEmail()+" Is Invalid");
        if (!foundUser.getPassword().equals(loginRequest.getPassword())) throw new InvalidDetailsException(
                "invalid DetailS");
        foundUser.setLocked(true);
        userRepository.save(foundUser);
    }
    @Override
    public void addIncome(AddIncomeRequest addIncomeRequest) {
        if (!userExist(addIncomeRequest.getEmail())) throw new UserNotFoundException(
                addIncomeRequest.getEmail()+" Doesn't Exist ");
        User founduser = userRepository.findByEmail(addIncomeRequest.getEmail());
        validateIfUserIsActive(founduser);
        Income income = incomeService.addIncome(addIncomeRequest.getIncome(),addIncomeRequest.getCategory(),founduser.getId());
        founduser.setBalance(founduser.getBalance().add(income.getAmountOfIncome()));
        founduser.getHistory().getIncomeList().add(income);
        founduser.getHistory().setIncomeList(founduser.getHistory().getIncomeList());
        founduser.getHistory().setAllTransaction(Collections.singletonList((founduser.getHistory().getIncomeList().toString())));
        userRepository.save(founduser);
    }
    private static void validateIfUserIsActive(User founduser) {
        if (!founduser.isLocked()){
            throw new AppLockedException("Kindly login");
        }
    }

    @Override
    public BigDecimal getBalance(String email) {
        if (!userExist(email)) throw new UserNotFoundException(
                email+" Doesn't Exist ");
        User user = userRepository.findByEmail(email);
        validateIfUserIsActive(user);
        return  user.getBalance();
    }
    @Override
    public void addExpenses(AddExpensesRequest addExpensesRequest) {
        if (!userExist(addExpensesRequest.getUserEmail())) throw new UserNotFoundException(
                addExpensesRequest.getUserEmail()+" Doesn't Exist ");
        User foundUser = userRepository.findByEmail(addExpensesRequest.getUserEmail());
        validateIfUserIsActive(foundUser);
        Expenses expenses = expensesService.addExpenses(addExpensesRequest.getAmount(),addExpensesRequest.getCategory(),
                foundUser.getId());
        int compareTo = foundUser.getBudget().getAmount().compareTo(expenses.getAmount());
        if (compareTo < 0) {
            throw new IsExceedBudgetLimitException("Budget Limit Exceeded");
        }
        foundUser.setBalance(foundUser.getBalance().subtract(expenses.getAmount()));
        foundUser.getHistory().getExpensesList().add(expenses);
        foundUser.getHistory().setExpensesList(foundUser.getHistory().getExpensesList());
        foundUser.getHistory().setAllTransaction(Collections.singletonList(foundUser.getHistory().getExpensesList().toString()));
        userRepository.save(foundUser);
    }
    @Override
    public void addBudget(AddBudgetRequest addBudgetRequest) {
        if (!userExist(addBudgetRequest.getEmail())) throw new UserNotFoundException(
                addBudgetRequest.getEmail()+" Doesn't Exist ");
        User foundUser = userRepository.findByEmail(addBudgetRequest.getEmail());
        Budget budget = budgetService.addBudget(addBudgetRequest.getBudgetAmount(),addBudgetRequest.getCategory(),
                foundUser.getId());
        int compareTo = foundUser.getBalance().compareTo(budget.getAmount());
        if (compareTo < 0) throw new BudgetCannotBeMoreThanIncomeException(budget.getAmount()+" is more than income");
        foundUser.setBudget(budget);
        userRepository.save(foundUser);
    }
    @Override
    public History getHistory(String mail) {
        if (!userExist(mail)) throw new UserNotFoundException(
                mail+" Doesn't Exist ");
        User foundUser = userRepository.findByEmail(mail);
        validateIfUserIsActive(foundUser);
        return foundUser.getHistory();
    }
    @Override
    public List<Income> getAllIncomeList(String mail) {
        if (!userExist(mail)) throw new UserNotFoundException(
                mail+" Doesn't Exist ");
        User foundUser = userRepository.findByEmail(mail);
        validateIfUserIsActive(foundUser);
        List<Income> incomes = incomeService.getAllIncome();
        historyService.saveAllIncome(incomes);
        userRepository.save(foundUser);
        return foundUser.getHistory().getIncomeList();
    }
    @Override
    public List<Expenses> getAllExpenses(String mail) {
        if (!userExist(mail)) throw new UserNotFoundException(
                mail+" Doesn't Exist ");
        User foundUser = userRepository.findByEmail(mail);
        validateIfUserIsActive(foundUser);
        List<Expenses> expenses = expensesService.getAllExpenses();
        History history = historyService.saveAllExpenses(expenses);
        foundUser.setHistory(history);
        userRepository.save(foundUser);
        return foundUser.getHistory().getExpensesList();
    }
    private boolean userExist(String email) {
        User foundUser = userRepository.findByEmail(email);
        return foundUser != null;
    }
}
