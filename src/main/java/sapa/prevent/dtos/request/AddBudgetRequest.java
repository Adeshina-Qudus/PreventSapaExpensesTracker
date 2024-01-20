package sapa.prevent.dtos.request;

import lombok.Data;
import sapa.prevent.data.models.Category;

import java.math.BigDecimal;

@Data
public class AddBudgetRequest {
    private Category category;
    private BigDecimal budgetAmount;
    private String email;
}
