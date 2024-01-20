package sapa.prevent.data.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Data
@Document
public class Budget {

    @Id
    private String id;
    private String budgetName;
    private BigDecimal amount;

}
