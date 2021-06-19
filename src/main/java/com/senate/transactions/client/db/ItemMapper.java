package com.senate.transactions.client.db;

import com.amazonaws.services.dynamodbv2.document.Item;
import com.senate.transactions.client.db.model.Transaction;
import org.mapstruct.Mapper;

import java.util.UUID;

@Mapper(componentModel = "spring")
public interface ItemMapper {

    default Item transform(Transaction transaction){
        return new Item()
                .withString("id", "" + transaction.getDateReceived() + "" +
                        transaction.getTransactionDate() + "" + transaction.getOwner() + "" + UUID.randomUUID().toString())
                .withString("firstName", transaction.getFirstName() == null ? "" : transaction.getFirstName())
                .withString("lastName", transaction.getLastName() == null ? "" : transaction.getLastName())
                .withString("office", transaction.getOffice() == null ? "" : transaction.getOffice() )
                .withString("ptrLink", transaction.getPtrLink() == null ? "" : transaction.getPtrLink())
                .withString("dateReceived", transaction.getDateReceived() == null ? "" : transaction.getDateReceived())
                .withString("transactionDate", transaction.getTransactionDate() == null ? "" : transaction.getTransactionDate())
                .withString("owner", transaction.getOwner() == null ? "" : transaction.getOwner())
                .withString("ticker", transaction.getTicker() == null ? "" : transaction.getTicker())
                .withString("assetDescription", transaction.getAssetDescription() == null ? "" : transaction.getAssetDescription())
                .withString("assetType", transaction.getAssetType() == null ? "" : transaction.getAssetType())
                .withString("type", transaction.getType() == null ? "" : transaction.getType())
                .withString("amount", transaction.getAmount() == null ? "" : transaction.getAmount())
                .withString("comment", transaction.getComment() == null ? "" : transaction.getComment());

    }
}
