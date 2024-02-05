package sapa.prevent.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sapa.prevent.dtos.request.*;
import sapa.prevent.dtos.response.*;
import sapa.prevent.exception.ExpensesTrackerException;
import sapa.prevent.services.UserServices;

@RestController
public class UserController {

    @Autowired
    private UserServices userServices;

    @PostMapping("registration")
    public ResponseEntity<?> registration(@RequestBody RegistrationRequest registrationRequest){
        RegistrationResponse registrationResponse = new RegistrationResponse();
        try {
            userServices.registration(registrationRequest);
            registrationResponse.setMessage("Congratulations "+ registrationRequest.getEmail()+ " Your registration is complete. Welcome to ");
            return new ResponseEntity<>(new ApiResponse(true,registrationResponse), HttpStatus.CREATED);
        }catch (ExpensesTrackerException exception){
            registrationResponse.setMessage(exception.getMessage());
            return new ResponseEntity<>(new ApiResponse(false,registrationResponse),HttpStatus.BAD_REQUEST);
        }
    }
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest){
        LoginResponse loginResponse = new LoginResponse();
        try {
            userServices.login(loginRequest);
            loginResponse.setMessage("Welcome back,"+ loginRequest.getEmail()+ "! We're glad to see you again.");
            return new ResponseEntity<>(new ApiResponse(true,loginResponse),HttpStatus.ACCEPTED);
        }catch (ExpensesTrackerException expensesTrackerException){
            loginResponse.setMessage(expensesTrackerException.getMessage());
            return new ResponseEntity<>(new ApiResponse(false,loginResponse),HttpStatus.BAD_REQUEST);
        }
    }
    @PostMapping("/addIncome")
    public ResponseEntity<?> addIncome(@RequestBody AddIncomeRequest addIncomeRequest){
        AddIncomeResponse addIncomeResponse = new AddIncomeResponse();
        try {
            userServices.addIncome(addIncomeRequest);
            addIncomeResponse.setMessage( "Income added successfully." +
                    " Your records have been updated."+
                    "Your Balance is "+userServices.getBalance(addIncomeRequest.getEmail()));
            return new ResponseEntity<>(new ApiResponse(true,addIncomeResponse),HttpStatus.OK);
        }catch (ExpensesTrackerException exception){
            addIncomeResponse.setMessage(exception.getMessage());
            return new ResponseEntity<>(new ApiResponse(false,addIncomeResponse),HttpStatus.NOT_IMPLEMENTED);
        }
    }
    @GetMapping("/getBalance/{email}")
    public ResponseEntity<?> getBalance(@PathVariable("email") String email){
        GetBalanceResponse getBalanceResponse = new GetBalanceResponse();
        try {
            getBalanceResponse.setBalance(userServices.getBalance(email));
            return new ResponseEntity<>(new ApiResponse(true,getBalanceResponse),HttpStatus.OK);
        }catch(ExpensesTrackerException exception){
            return new ResponseEntity<>(new ApiResponse(false,getBalanceResponse),HttpStatus.BAD_REQUEST);
        }
    }
    @PostMapping("/addExpenses")
    public ResponseEntity<?> addExpenses(@RequestBody AddExpensesRequest addExpensesRequest){
        AddExpensesResponse addExpensesResponse = new AddExpensesResponse();
        try {
            userServices.addExpenses(addExpensesRequest);
            addExpensesResponse.setMessage("Expense added successfully. " +
                    "Your records have been updated."+
                    "Your Balance is "+userServices.getBalance(addExpensesRequest.getUserEmail()));
            return new ResponseEntity<>(new ApiResponse(true,addExpensesResponse),HttpStatus.OK);
        }catch (ExpensesTrackerException exception){
            addExpensesResponse.setMessage(exception.getMessage());
            return new ResponseEntity<>(new ApiResponse(false,addExpensesResponse),HttpStatus.NOT_ACCEPTABLE);
        }
    }
    @PostMapping("/addBudget")
    public ResponseEntity<?> addBudget(@RequestBody AddBudgetRequest addBudgetRequest){
        AddBudgetResponse addBudgetResponse = new AddBudgetResponse();
        try {
            addBudgetResponse.setMessage("Your budget has been added successfully. Let's start managing your finances!");
            addBudgetResponse.setBudget(userServices.addBudget(addBudgetRequest));
            return new ResponseEntity<>(new ApiResponse(true,addBudgetResponse),HttpStatus.CREATED);
        }catch (ExpensesTrackerException exception){
            addBudgetResponse.setMessage(exception.getMessage());
            return new ResponseEntity<>(new ApiResponse(false,addBudgetResponse),HttpStatus.NOT_ACCEPTABLE);
        }
    }
}
