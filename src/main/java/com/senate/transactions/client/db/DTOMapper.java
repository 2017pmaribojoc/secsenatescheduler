package com.senate.transactions.client.db;

import com.senate.transactions.client.db.model.Transaction;
import com.senate.transactions.model.Record;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface DTOMapper {

    Transaction transform(com.senate.transactions.model.Transaction transaction, @Context Record record);

    @AfterMapping
    default void mapRecordFields(@MappingTarget Transaction transaction, @Context Record record){
        transaction.setFirstName(record.getFirstName());
        transaction.setLastName(record.getLastName());
        transaction.setOffice(record.getOffice());
        transaction.setPtrLink(record.getPtrLink());
        transaction.setDateReceived(record.getDateReceived());
    }

}
