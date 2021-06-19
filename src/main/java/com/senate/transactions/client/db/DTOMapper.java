package com.senate.transactions.client.db;

import com.senate.transactions.client.db.model.Transaction;
import com.senate.transactions.model.Record;
import org.mapstruct.*;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring")
public interface DTOMapper {

    default List<Transaction> transform(List<Record> records){
        List<Transaction> dtoTransactions = new ArrayList<>();
        System.out.println("Transforming records: " + records.size());
        records.forEach(record -> {
            List<com.senate.transactions.model.Transaction> transactions = record.getTransactions();
            transactions.forEach(transaction -> {
                dtoTransactions.add(transform(transaction, record));
            });
        });
        System.out.println("Returning records: " + dtoTransactions.size());

        return dtoTransactions;
    }

    Transaction transform(com.senate.transactions.model.Transaction source, @Context Record record);

    @AfterMapping
    default void mapRecordFields(@MappingTarget Transaction transaction, @Context Record record){
        transaction.setFirstName(record.getFirstName());
        transaction.setLastName(record.getLastName());
        transaction.setOffice(record.getOffice());
        transaction.setPtrLink(record.getPtrLink());
        transaction.setDateReceived(record.getDateReceived());
    }

}
