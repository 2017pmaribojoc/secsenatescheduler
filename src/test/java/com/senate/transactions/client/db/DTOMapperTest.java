package com.senate.transactions.client.db;


import com.senate.transactions.model.Record;
import com.senate.transactions.model.Transaction;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.junit.jupiter.api.Assertions;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {DTOMapperImpl.class})
public class DTOMapperTest {

    @Autowired DTOMapper mapper;

    @Test
    public void testTransform() {
        Transaction transaction = new Transaction();
        Record record = new Record();
        transaction.setTicker("ticker");
        record.setFirstName("firstName");

        com.senate.transactions.client.db.model.Transaction target = mapper.transform(transaction, record);
        Assertions.assertEquals("ticker", target.getTicker());
        Assertions.assertEquals("firstName", target.getFirstName());

    }
}
