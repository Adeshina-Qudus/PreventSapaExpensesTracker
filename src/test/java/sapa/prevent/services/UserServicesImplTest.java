package sapa.prevent.services;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import sapa.prevent.data.models.Category;
import sapa.prevent.data.models.User;
import sapa.prevent.data.repositories.UserRepository;
import sapa.prevent.dtos.request.AddExpensesRequest;
import sapa.prevent.dtos.request.AddIncomeRequest;
import sapa.prevent.dtos.request.LoginRequest;
import sapa.prevent.dtos.request.RegisterRequest;
import sapa.prevent.exception.EmptyDetailsException;
import sapa.prevent.exception.InvalidDetailsException;
import sapa.prevent.exception.UserAlreadyExistException;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserServicesImplTest {

    @Autowired
    private UserServices userServices;
    @Autowired
    private UserRepository userRepository;

    @AfterEach
    public void doThisAfterEachTest(){
        userRepository.deleteAll();
    }

    @Test
    public void userRegisterAndUserIsOneTest(){
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setEmail("qudusa55@Gmail.com");
        registerRequest.setPassword("Iniestajnr");
        registerRequest.setConfirmPassword("Iniestajnr");
        userServices.register(registerRequest);
        assertEquals(1,userRepository.count());
    }
    @Test
    public void userRegisterAndUserIsLockedTest(){
        User user = new User();
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setEmail("qudusa55@Gmail.com");
        registerRequest.setPassword("Iniestajnr");
        registerRequest.setConfirmPassword("Iniestajnr");
        userServices.register(registerRequest);
        assertTrue(user.isLocked());
    }
    @Test
    public void userRegisterWithAnEmptyEmailThrowExceptionTest(){
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setEmail("");
        registerRequest.setPassword("Iniestajnr");
        registerRequest.setConfirmPassword("Iniestajnr");
        assertThrows(EmptyDetailsException.class , ()-> userServices.register(registerRequest));
    }
    @Test
    public void userRegisterWithAnEmptyPasswordThrowExceptionTest(){
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setEmail("qudusa55@Gmail.com");
        registerRequest.setPassword("");
        registerRequest.setConfirmPassword("Iniestajnr");
        assertThrows(EmptyDetailsException.class , ()-> userServices.register(registerRequest));
    }
    @Test
    public void userRegisterWithOneEmailTwiceThrowExceptionTest(){
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setEmail("qudusa55@Gmail.com");
        registerRequest.setPassword("Iniestajnr");
        registerRequest.setConfirmPassword("Iniestajnr");
        userServices.register(registerRequest);
        registerRequest.setEmail("qudusa55@Gmail.com");
        registerRequest.setPassword("qudus");
        registerRequest.setConfirmPassword("qudus");
        assertThrows(UserAlreadyExistException.class , ()-> userServices.register(registerRequest));
    }
    @Test
    public void userLoginWithWrongPasswordThroeExceptionTest(){
        RegisterRequest registerRequest = new RegisterRequest();
        LoginRequest loginRequest = new LoginRequest();
        registerRequest.setEmail("qudusa55@Gmail.com");
        registerRequest.setPassword("Iniestajnr");
        registerRequest.setConfirmPassword("Iniestajnr");
        userServices.register(registerRequest);
        loginRequest.setEmail("qudusa55@Gmail.com");
        loginRequest.setPassword("Iniestajnr1");
        assertThrows(InvalidDetailsException.class, ()-> userServices.login(loginRequest));
    }
    @Test
    public void addIncomeTest(){
        RegisterRequest registerRequest = new RegisterRequest();
        LoginRequest loginRequest = new LoginRequest();
        registerRequest.setEmail("qudusa55@Gmail.com");
        registerRequest.setPassword("Iniestajnr");
        registerRequest.setConfirmPassword("Iniestajnr");
        userServices.register(registerRequest);
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
        RegisterRequest registerRequest = new RegisterRequest();
        LoginRequest loginRequest = new LoginRequest();
        registerRequest.setEmail("qudusa55@Gmail.com");
        registerRequest.setPassword("Iniestajnr");
        registerRequest.setConfirmPassword("Iniestajnr");
        userServices.register(registerRequest);
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
        RegisterRequest registerRequest = new RegisterRequest();
        LoginRequest loginRequest = new LoginRequest();
        registerRequest.setEmail("qudusa55@Gmail.com");
        registerRequest.setPassword("Iniestajnr");
        registerRequest.setConfirmPassword("Iniestajnr");
        userServices.register(registerRequest);
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
        AddExpensesRequest addExpensesRequest = new AddExpensesRequest();
        Category category1 = new Category();
        addExpensesRequest.setAmount(BigDecimal.valueOf(2000));
        category1.setNameOfCategory("Clubbing");
        addExpensesRequest.setCategory(category1);
        addExpensesRequest.setUserEmail("qudusa55@Gmail.com");
        userServices.addExpenses(addExpensesRequest);
        assertEquals(BigDecimal.valueOf(1000),userServices.getBalance("qudusa55@Gmail.com"));
    }

}
