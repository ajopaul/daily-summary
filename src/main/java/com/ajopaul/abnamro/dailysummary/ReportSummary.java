package com.ajopaul.abnamro.dailysummary;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ReportSummary {
    private String clientInformation;
    private String productInformation;
    private Double netTotalTransactionAmount;
}
