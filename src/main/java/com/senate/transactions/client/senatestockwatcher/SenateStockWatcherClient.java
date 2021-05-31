package com.senate.transactions.client.senatestockwatcher;

import com.senate.transactions.model.ListBucketFileMap;

import java.util.List;

public interface SenateStockWatcherClient {

    ListBucketFileMap getFilemapList();
    List<Object> getDailyTransactions(String jsonFilename);
}
