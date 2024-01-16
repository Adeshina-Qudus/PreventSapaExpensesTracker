package sapa.prevent.data.models;


import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Data
@Document
public class User {
    @Id
    private String email;
    private String password;
    private String confirmPassword;
    private LocalDateTime stateDate = LocalDateTime.now();
    private LocalDateTime endDate;
    private List<Income> incomeList;
    private List<Expenses> expensesList;
}
