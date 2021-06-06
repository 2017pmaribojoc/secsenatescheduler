package com.senate.transactions.client.senatestockwatcher;

import com.senate.transactions.model.Record;
import com.senate.transactions.model.Transaction;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.function.Consumer;

@Component
public class RecordDataCleanerImpl implements RecordDataCleaner {

    @Override
    public void cleanData(Record record) {
        if (record.getTransactions() != null) {
            record.getTransactions().forEach(transaction -> cleanTransactionData(transaction));
        }
    }

    private void cleanTransactionData(Transaction transaction){
        transaction.setAssetDescription(cleanAssetDescription(transaction.getAssetDescription()));
        transaction.setTicker(cleanTicker(transaction.getTicker()));
    }

    private String cleanAssetDescription(String assetDescription){
        if (StringUtils.hasText(assetDescription) && assetDescription.contains("<")){
            return assetDescription.substring(0, assetDescription.indexOf("<") - 1);
        } else {
            return assetDescription;
        }
    }

    private String cleanTicker(String ticker){
        if (StringUtils.hasText(ticker) && ticker.contains("href")){
            return ticker.substring(ticker.indexOf(">") + 1, ticker.lastIndexOf("<"));
        } else {
            return ticker;
        }
    }

}
