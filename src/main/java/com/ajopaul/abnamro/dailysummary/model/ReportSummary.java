package com.ajopaul.abnamro.dailysummary.model;

import com.opencsv.bean.CsvBindByName;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ReportSummary {
    public String getClientInformation() {
        return clientInformation;
    }

    public void setClientInformation(String clientInformation) {
        this.clientInformation = clientInformation;
    }

    public String getProductInformation() {
        return productInformation;
    }

    public void setProductInformation(String productInformation) {
        this.productInformation = productInformation;
    }

    public double getNetTotalTransactionAmount() {
        return netTotalTransactionAmount;
    }

    public void setNetTotalTransactionAmount(double netTotalTransactionAmount) {
        this.netTotalTransactionAmount = netTotalTransactionAmount;
    }

    @CsvBindByName(column = "Client_Information")
    private String clientInformation;
    @CsvBindByName(column = "Product_Information")
    private String productInformation;
    @CsvBindByName(column = "Total_Transaction_Amount")
    private double netTotalTransactionAmount;
}
