package sapa.prevent.data.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

@Data
@Document
public class Income {

    @Id
    private String id;
    private BigDecimal amountOfIncome = new BigDecimal(0);
    private LocalDateTime dateOfIncome = LocalDateTime.now();
    private Category category;
    private String userId;

}
