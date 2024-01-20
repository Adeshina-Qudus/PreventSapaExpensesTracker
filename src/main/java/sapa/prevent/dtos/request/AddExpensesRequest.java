package sapa.prevent.dtos.request;

import lombok.Data;
import sapa.prevent.data.models.Category;

import java.math.BigDecimal;

@Data
public class AddExpensesRequest {
    private Category category;
    private BigDecimal amount;
    private String userEmail;
}
