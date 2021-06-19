package com.senate.transactions.client.db;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.spec.PutItemSpec;
import com.senate.transactions.client.db.model.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DAOImpl implements DAO {

    private DynamoDB dynamoDb;

    private ItemMapper itemMapper;

    private final String SEC_TRANSACTION_RECORDS_TABLE_NAME = "sec_senate_transactions";

    @Autowired
    public DAOImpl(AmazonDynamoDB amazonDynamoDB, ItemMapper itemMapper){
        this.dynamoDb = new DynamoDB(amazonDynamoDB);
        this.itemMapper = itemMapper;
    }

    @Override
    public void update(List<Transaction> transactions) {
        System.out.println("Inserting " + transactions.size() + " elements into table");
        transactions.forEach(transaction -> dynamoDb.getTable(SEC_TRANSACTION_RECORDS_TABLE_NAME)
                .putItem(new PutItemSpec().withItem(itemMapper.transform(transaction))));
        System.out.println("Successfully saved transactions");

    }


}
