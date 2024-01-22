package sapa.prevent.data.models;


import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Document
public class User {
    @Id
    private String id;
    private String email;
    private String password;
    private LocalDateTime dateTime = LocalDateTime.now();
    private BigDecimal balance = new BigDecimal(0) ;
    private ArrayList<Income> incomeList = new ArrayList<Income>();
    private List<Expenses> expensesList = new ArrayList<Expenses>();
    private Budget budget;
    private boolean isLocked = true;
    private boolean isExceedBudget;


}
