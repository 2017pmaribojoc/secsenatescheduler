package com.senate.transactions.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class Record {
    @JsonProperty("first_name")
    private String firstName;

    @JsonProperty("last_name")
    private String lastName;

    private String office;
    @JsonProperty("ptr_link")
    private String ptrLink;
    @JsonProperty("date_received")
    private String dateReceived;
    private List<Transaction> transactions;
}
