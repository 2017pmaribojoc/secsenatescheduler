package com.senate.transactions.client.senatestockwatcher;

import com.senate.transactions.model.ListBucketFileMap;
import com.senate.transactions.model.Record;

import java.util.List;

public interface SenateStockWatcherClient {

    ListBucketFileMap getFilemapList();
    List<Record> getDailyTransactions(String jsonFilename);
}
