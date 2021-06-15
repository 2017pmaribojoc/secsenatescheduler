package com.senate.transactions.client.db;

import com.senate.transactions.model.Record;

import java.util.List;

public interface DAO {

    void update(List<Record> records);

}
