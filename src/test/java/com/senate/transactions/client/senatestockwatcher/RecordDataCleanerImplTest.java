package com.senate.transactions.client.senatestockwatcher;

import com.senate.transactions.model.Record;
import com.senate.transactions.model.Transaction;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RecordDataCleanerImplTest {

    @Autowired
    private RecordDataCleaner cleaner;

    @Test
    public void testTickerCleaning() {
        List<Record> records = new ArrayList<>();
        List<Transaction> transactions = new ArrayList<>();

        Transaction transaction = new Transaction();
        transaction.setTicker("<a href=\\\"https://finance.yahoo.com/q?s=QRTEA\\\" target=\\\"_blank\\\">QRTEA</a>");
        transactions.add(transaction);

        Record record = new Record();
        record.setTransactions(transactions);

        records.add(record);
        cleaner.cleanData(record);
        Assertions.assertEquals("QRTEA", records.get(0).getTransactions().get(0).getTicker());
    }

    @Test
    public void testAssetDescriptionCleaning() {
        List<Record> records = new ArrayList<>();
        List<Transaction> transactions = new ArrayList<>();

        Transaction transaction = new Transaction();
        transaction.setAssetDescription("HSBC Contingent Autocall BMY <div class=\\\"text-muted\\\"><em>Rate/Coupon:</em> 8.75%<br> <em>Matures:</em> 02/24/2023</div>");
        transactions.add(transaction);

        Record record = new Record();
        record.setTransactions(transactions);

        records.add(record);
        cleaner.cleanData(record);
        Assertions.assertEquals("HSBC Contingent Autocall BMY", records.get(0).getTransactions().get(0).getAssetDescription());
    }
}
