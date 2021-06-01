package com.senate.transactions;

import com.senate.transactions.client.senatestockwatcher.SenateStockWatcherClient;
import com.senate.transactions.model.Contents;
import com.senate.transactions.model.ListBucketFileMap;
import com.senate.transactions.model.Record;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
class ApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
    private SenateStockWatcherClient senateStockWatcherClientMock;



	@Test
	public void doesContainsCloud() throws Exception {

        ListBucketFileMap map = new ListBucketFileMap();
        List<Contents> contents = new ArrayList<>();
        Contents content = new Contents();
        content.setKey("key");
        contents.add(content);
        map.setContents(contents);
	    when(senateStockWatcherClientMock.getFilemapList()).thenReturn(map);

        List<Record> records = new ArrayList<>();
        Record record = new Record();
        record.setFirstName("first name");
        record.setLastName("last name");
        records.add(record);
	    when(senateStockWatcherClientMock.getDailyTransactions(eq("key"))).thenReturn(records);
		MvcResult result = this.mockMvc.perform(post("/processStockFilings")
                .contentType(MediaType.TEXT_PLAIN).content("this is a cloud")).andReturn();
		mockMvc.perform(asyncDispatch(result)).andExpect(content().string("first name"));
	}

	@Test
	public void doesNotContainsCloud() throws Exception {
        when(senateStockWatcherClientMock.getFilemapList()).thenReturn(null);
        MvcResult result = this.mockMvc.perform(post("/processStockFilings")
                .contentType(MediaType.TEXT_PLAIN).content("this is a function")).andReturn();
		mockMvc.perform(asyncDispatch(result)).andExpect(content()
                .string("error processing stock filings"));
	}
}
