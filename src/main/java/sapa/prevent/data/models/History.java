package sapa.prevent.data.models;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.ArrayList;
import java.util.List;

@Document
@Data
public class History {
    private String id;
    private List<Income> incomeList = new ArrayList<>();
    private List<Expenses> expensesList = new ArrayList<>();
    private List<Object> allTransaction = new ArrayList<>();
}
