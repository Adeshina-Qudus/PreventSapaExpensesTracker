package sapa.prevent.data.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.ArrayList;
import java.util.List;

@Document
@Data
@ToString(exclude = {"incomeList", "expensesList"})
public class History {
    private String id;
    @JsonIgnore
    private List<Income> incomeList = new ArrayList<>();
    @JsonIgnore
    private List<Expenses> expensesList = new ArrayList<>();
    private List<Object> allTransaction = new ArrayList<>();
}
