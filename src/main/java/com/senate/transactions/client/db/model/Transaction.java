package com.senate.transactions.client.db.model;

import lombok.Data;

@Data
public class Transaction {

    private String firstName;
    private String lastName;
    private String office;
    private String ptrLink;
    private String dateReceived;
    private String transactionDate;
    private String owner;
    private String ticker;
    private String assetDescription;
    private String assetType;
    private String type;
    private String amount;
    private String comment;
}
