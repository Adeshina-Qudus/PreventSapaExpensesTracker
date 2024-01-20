package sapa.prevent.dtos.request;

import lombok.Data;
import sapa.prevent.data.models.Category;

import java.math.BigDecimal;
@Data
public class AddIncomeRequest {

    private Category category;
    private BigDecimal income;
    private String email;
}
