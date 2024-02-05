package sapa.prevent.services;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import sapa.prevent.data.models.Category;
import sapa.prevent.data.models.User;
import sapa.prevent.data.repositories.BudgetRepository;
import sapa.prevent.data.repositories.IncomeRepository;
import sapa.prevent.data.repositories.UserRepository;
import sapa.prevent.dtos.request.*;
import sapa.prevent.exception.*;

import java.math.BigDecimal;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserServicesImplTest {

    @Autowired
    private UserServices userServices;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BudgetRepository budgetRepository;
    @Autowired
    private IncomeRepository incomeRepository;

    @AfterEach
    public void doThisAfterEachTest(){
        userRepository.deleteAll();
        budgetRepository.deleteAll();
        incomeRepository.deleteAll();
    }

    @Test
    public void userRegisterAndUserIsOneTest(){
        RegistrationRequest registerRequest = new RegistrationRequest();
        registerRequest.setEmail("qudusa55@Gmail.com");
        registerRequest.setPassword("Iniestajnr");
        registerRequest.setConfirmPassword("Iniestajnr");
        userServices.registration(registerRequest);
        assertEquals(1,userRepository.count());
    }
    @Test
    public void userRegisterAndUserIsLockedTest(){
        User user = new User();
        RegistrationRequest registerRequest = new RegistrationRequest();
        registerRequest.setEmail("qudusa55@Gmail.com");
        registerRequest.setPassword("Iniestajnr");
        registerRequest.setConfirmPassword("Iniestajnr");
        userServices.registration(registerRequest);
        assertFalse(user.isLocked());
    }
    @Test
    public void userRegisterWithAnEmptyEmailThrowExceptionTest(){
        RegistrationRequest registerRequest = new RegistrationRequest();
        registerRequest.setEmail("");
        registerRequest.setPassword("Iniestajnr");
        registerRequest.setConfirmPassword("Iniestajnr");
        assertThrows(EmptyDetailsException.class , ()-> userServices.registration(registerRequest));
    }
    @Test
    public void userRegisterWithAnEmptyPasswordThrowExceptionTest(){
        RegistrationRequest registerRequest = new RegistrationRequest();
        registerRequest.setEmail("qudusa55@Gmail.com");
        registerRequest.setPassword("");
        registerRequest.setConfirmPassword("Iniestajnr");
        assertThrows(EmptyDetailsException.class , ()-> userServices.registration(registerRequest));
    }
    @Test
    public void userRegisterWithOneEmailTwiceThrowExceptionTest(){
        RegistrationRequest registerRequest = new RegistrationRequest();
        registerRequest.setEmail("qudusa55@Gmail.com");
        registerRequest.setPassword("Iniestajnr");
        registerRequest.setConfirmPassword("Iniestajnr");
        userServices.registration(registerRequest);
        registerRequest.setEmail("qudusa55@Gmail.com");
        registerRequest.setPassword("qudus");
        registerRequest.setConfirmPassword("qudus");
        assertThrows(UserAlreadyExistException.class , ()-> userServices.registration(registerRequest));
    }
    @Test
    public void userLoginWithWrongPasswordThroeExceptionTest(){
        RegistrationRequest registerRequest = new RegistrationRequest();
        LoginRequest loginRequest = new LoginRequest();
        registerRequest.setEmail("qudusa55@Gmail.com");
        registerRequest.setPassword("Iniestajnr");
        registerRequest.setConfirmPassword("Iniestajnr");
        userServices.registration(registerRequest);
        loginRequest.setEmail("qudusa55@Gmail.com");
        loginRequest.setPassword("Iniestajnr1");
        assertThrows(InvalidDetailsException.class, ()-> userServices.login(loginRequest));
    }
    @Test
    public void addIncomeTest(){
        RegistrationRequest registerRequest = new RegistrationRequest();
        LoginRequest loginRequest = new LoginRequest();
        registerRequest.setEmail("qudusa55@Gmail.com");
        registerRequest.setPassword("Iniestajnr");
        registerRequest.setConfirmPassword("Iniestajnr");
        userServices.registration(registerRequest);
        loginRequest.setEmail("qudusa55@Gmail.com");
        loginRequest.setPassword("Iniestajnr");
        userServices.login(loginRequest);
        AddIncomeRequest addIncomeRequest = new AddIncomeRequest();
        Category category = new Category();
        addIncomeRequest.setIncome(BigDecimal.valueOf(3922.0));
        category.setNameOfCategory("building");
        addIncomeRequest.setCategory(category);
        addIncomeRequest.setEmail("qudusa55@Gmail.com");
        userServices.addIncome(addIncomeRequest);
        assertEquals(BigDecimal.valueOf(3922.0),userServices.getBalance("qudusa55@Gmail.com"));
    }
    @Test
    public void addIncomeTwiceTest(){
        RegistrationRequest registerRequest = new RegistrationRequest();
        LoginRequest loginRequest = new LoginRequest();
        registerRequest.setEmail("qudusa55@Gmail.com");
        registerRequest.setPassword("Iniestajnr");
        registerRequest.setConfirmPassword("Iniestajnr");
        userServices.registration(registerRequest);
        loginRequest.setEmail("qudusa55@Gmail.com");
        loginRequest.setPassword("Iniestajnr");
        userServices.login(loginRequest);
        AddIncomeRequest addIncomeRequest = new AddIncomeRequest();
        Category category = new Category();
        addIncomeRequest.setIncome(BigDecimal.valueOf(300));
        category.setNameOfCategory("building");
        addIncomeRequest.setCategory(category);
        addIncomeRequest.setEmail("qudusa55@Gmail.com");
        userServices.addIncome(addIncomeRequest);
        AddIncomeRequest addIncomeRequest1 = new AddIncomeRequest();
        Category category1 = new Category();
        addIncomeRequest1.setIncome(BigDecimal.valueOf(400));
        category1.setNameOfCategory("shayo");
        addIncomeRequest1.setCategory(category1);
        addIncomeRequest1.setEmail("qudusa55@Gmail.com");
        userServices.addIncome(addIncomeRequest1);
        assertEquals(BigDecimal.valueOf(700),userServices.getBalance("qudusa55@Gmail.com"));
    }
    @Test
    public void addExpensesTest(){
        RegistrationRequest registerRequest = new RegistrationRequest();
        LoginRequest loginRequest = new LoginRequest();
        registerRequest.setEmail("qudusa55@Gmail.com");
        registerRequest.setPassword("Iniestajnr");
        registerRequest.setConfirmPassword("Iniestajnr");
        userServices.registration(registerRequest);
        loginRequest.setEmail("qudusa55@Gmail.com");
        loginRequest.setPassword("Iniestajnr");
        userServices.login(loginRequest);
        AddIncomeRequest addIncomeRequest = new AddIncomeRequest();
        Category category = new Category();
        addIncomeRequest.setIncome(BigDecimal.valueOf(3000));
        category.setNameOfCategory("building");
        addIncomeRequest.setCategory(category);
        addIncomeRequest.setEmail("qudusa55@Gmail.com");
        userServices.addIncome(addIncomeRequest);
        Category category3 = new Category();
        category3.setNameOfCategory("Spend Small Small");
        AddBudgetRequest addBudgetRequest = new AddBudgetRequest();
        addBudgetRequest.setCategory(category3);
        addBudgetRequest.setBudgetAmount(BigDecimal.valueOf(2500));
        addBudgetRequest.setEmail("qudusa55@Gmail.com");
        userServices.addBudget(addBudgetRequest);
        AddExpensesRequest addExpensesRequest = new AddExpensesRequest();
        Category category1 = new Category();
        addExpensesRequest.setAmount(BigDecimal.valueOf(2000));
        category1.setNameOfCategory("Clubbing");
        addExpensesRequest.setCategory(category1);
        addExpensesRequest.setUserEmail("qudusa55@Gmail.com");
        userServices.addExpenses(addExpensesRequest);
        assertEquals(BigDecimal.valueOf(1000),userServices.getBalance("qudusa55@Gmail.com"));
    }

    @Test
    public void addBudgetTest(){
        RegistrationRequest registerRequest = new RegistrationRequest();
        LoginRequest loginRequest = new LoginRequest();
        registerRequest.setEmail("qudusa55@Gmail.com");
        registerRequest.setPassword("Iniestajnr");
        registerRequest.setConfirmPassword("Iniestajnr");
        userServices.registration(registerRequest);
        loginRequest.setEmail("qudusa55@Gmail.com");
        loginRequest.setPassword("Iniestajnr");
        userServices.login(loginRequest);
        AddIncomeRequest addIncomeRequest = new AddIncomeRequest();
        Category category = new Category();
        addIncomeRequest.setIncome(BigDecimal.valueOf(30000));
        category.setNameOfCategory("building");
        addIncomeRequest.setCategory(category);
        addIncomeRequest.setEmail("qudusa55@Gmail.com");
        userServices.addIncome(addIncomeRequest);
        Category category1 = new Category();
        category1.setNameOfCategory("Spend Small Small");
        AddBudgetRequest addBudgetRequest = new AddBudgetRequest();
        addBudgetRequest.setCategory(category1);
        addBudgetRequest.setBudgetAmount(BigDecimal.valueOf(20000));
        addBudgetRequest.setEmail("qudusa55@Gmail.com");
        userServices.addBudget(addBudgetRequest);
        assertEquals(1,budgetRepository.count());
    }
    @Test
    public void budgetCannotBeMoreThanIncomeTest(){
        RegistrationRequest registerRequest = new RegistrationRequest();
        LoginRequest loginRequest = new LoginRequest();
        registerRequest.setEmail("qudusa55@Gmail.com");
        registerRequest.setPassword("Iniestajnr");
        registerRequest.setConfirmPassword("Iniestajnr");
        userServices.registration(registerRequest);
        loginRequest.setEmail("qudusa55@Gmail.com");
        loginRequest.setPassword("Iniestajnr");
        userServices.login(loginRequest);
        AddIncomeRequest addIncomeRequest = new AddIncomeRequest();
        Category category = new Category();
        addIncomeRequest.setIncome(BigDecimal.valueOf(20000));
        category.setNameOfCategory("building");
        addIncomeRequest.setCategory(category);
        addIncomeRequest.setEmail("qudusa55@Gmail.com");
        userServices.addIncome(addIncomeRequest);
        Category category1 = new Category();
        category1.setNameOfCategory("Spend Small Small");
        AddBudgetRequest addBudgetRequest = new AddBudgetRequest();
        addBudgetRequest.setCategory(category1);
        addBudgetRequest.setBudgetAmount(BigDecimal.valueOf(30000));
        addBudgetRequest.setEmail("qudusa55@Gmail.com");
        assertThrows(BudgetCannotBeMoreThanIncomeException.class,()->userServices.addBudget(addBudgetRequest));
    }
    @Test
    public void isExceedBudgetLimitTest(){
        RegistrationRequest registerRequest = new RegistrationRequest();
        LoginRequest loginRequest = new LoginRequest();
        registerRequest.setEmail("qudusa55@Gmail.com");
        registerRequest.setPassword("Iniestajnr");
        registerRequest.setConfirmPassword("Iniestajnr");
        userServices.registration(registerRequest);
        loginRequest.setEmail("qudusa55@Gmail.com");
        loginRequest.setPassword("Iniestajnr");
        userServices.login(loginRequest);
        AddIncomeRequest addIncomeRequest = new AddIncomeRequest();
        Category category = new Category();
        addIncomeRequest.setIncome(BigDecimal.valueOf(100));
        category.setNameOfCategory("building");
        addIncomeRequest.setCategory(category);
        addIncomeRequest.setEmail("qudusa55@Gmail.com");
        userServices.addIncome(addIncomeRequest);
        Category category1 = new Category();
        category1.setNameOfCategory("Spend Small Small");
        AddBudgetRequest addBudgetRequest = new AddBudgetRequest();
        addBudgetRequest.setCategory(category1);
        addBudgetRequest.setBudgetAmount(BigDecimal.valueOf(70));
        addBudgetRequest.setEmail("qudusa55@Gmail.com");
        userServices.addBudget(addBudgetRequest);
        AddExpensesRequest addExpensesRequest = new AddExpensesRequest();
        Category category2 = new Category();
        category2.setNameOfCategory("Shayo");
        addExpensesRequest.setCategory(category2);
        addExpensesRequest.setAmount(BigDecimal.valueOf(80));
        addExpensesRequest.setUserEmail("qudusa55@Gmail.com");
        assertThrows(IsExceedBudgetLimitException.class,
                ()->userServices.addExpenses(addExpensesRequest));
    }

}
