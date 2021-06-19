package com.senate.transactions.client.db;

import com.senate.transactions.client.db.model.Transaction;

import java.util.List;

public interface DAO {

    void update(List<Transaction> records);

}
