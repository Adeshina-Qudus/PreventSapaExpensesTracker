package sapa.prevent.data.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDateTime;


@Document
@Data
public class Expenses {
    @Id
    private String id;
    private Category category;
    private BigDecimal amount;
    private LocalDateTime localDateTime ;
    private User user;
}
