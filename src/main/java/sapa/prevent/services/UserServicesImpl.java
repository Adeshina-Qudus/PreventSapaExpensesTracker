package sapa.prevent.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sapa.prevent.data.models.*;
import sapa.prevent.data.repositories.UserRepository;
import sapa.prevent.dtos.request.*;
import sapa.prevent.exception.*;

import java.math.BigDecimal;
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
        founduser.getIncomeList().add(income);
        founduser.setBalance(founduser.getBalance().add(income.getAmountOfIncome()));
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
        foundUser.getExpensesList().add(expenses);
        int compareTo = foundUser.getBudget().getAmount().compareTo(expenses.getAmount());
        if (compareTo < 0) {
            throw new IsExceedBudgetLimitException("Budget Limit Exceeded");
        }
        foundUser.setBalance(foundUser.getBalance().subtract(expenses.getAmount()));
        userRepository.save(foundUser);
    }
    @Override
    public Object addBudget(AddBudgetRequest addBudgetRequest) {
        if (!userExist(addBudgetRequest.getEmail())) throw new UserNotFoundException(
                addBudgetRequest.getEmail()+" Doesn't Exist ");
        User foundUser = userRepository.findByEmail(addBudgetRequest.getEmail());
        Budget budget = budgetService.addBudget(addBudgetRequest.getBudgetAmount(),addBudgetRequest.getCategory(),
                foundUser.getId());
        int compareTo = foundUser.getBalance().compareTo(budget.getAmount());
        if (compareTo < 0) throw new BudgetCannotBeMoreThanIncomeException(budget.getAmount()+" is more than income");
        foundUser.setBudget(budget);
        userRepository.save(foundUser);
        return foundUser.getBudget();
    }

    private boolean userExist(String email) {
        User foundUser = userRepository.findByEmail(email);
        return foundUser != null;
    }
}
