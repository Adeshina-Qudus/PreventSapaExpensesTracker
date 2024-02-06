package sapa.prevent.dtos.response;

import lombok.Data;

@Data
public class GetBalanceResponse {
    private String message;
    private Object balance;
}
