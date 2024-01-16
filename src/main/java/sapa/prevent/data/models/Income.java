package sapa.prevent.data.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Document
public class Income {

    @Id
    private String id;
    private BigDecimal amountOfIncome;
    private LocalDateTime dateOfIncome;
    private User user;
}
