package com.ajopaul.abnamro.dailysummary.model;

import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class InputRecord {
    private String clientType;
    private String clientNumber;
    private String accountNumber;
    private String subAccountNumber;

    private String exchangeCode;
    private String productGroupCode;
    private String symbol;
    private String expirationDate;

    private double quantityLong;
    private double quantityShort;
}
