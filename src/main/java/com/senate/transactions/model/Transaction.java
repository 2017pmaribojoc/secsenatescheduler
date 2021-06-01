package com.senate.transactions.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Transaction {
    @JsonProperty("transaction_date")
    private String transactionDate;
    private String owner;
    private String ticker;
    @JsonProperty("asset_description")
    private String assetDescription;
    @JsonProperty("asset_type")
    private String assetType;
    private String type;
    private String amount;
    private String comment;
}
