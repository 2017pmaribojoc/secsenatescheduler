package com.senate.transactions;

import com.senate.transactions.client.db.DAO;
import com.senate.transactions.client.db.DTOMapper;
import com.senate.transactions.client.senatestockwatcher.RecordDataCleaner;
import com.senate.transactions.client.senatestockwatcher.SenateStockWatcherClient;
import com.senate.transactions.model.Contents;
import com.senate.transactions.model.ListBucketFileMap;
import com.senate.transactions.model.Record;
import org.junit.jupiter.api.BeforeEach;
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

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.asyncDispatch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@SpringBootTest
@AutoConfigureMockMvc
class ApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
    private SenateStockWatcherClient senateStockWatcherClientMock;

    @MockBean
    private RecordDataCleaner recordDataCleaner;

    @MockBean
    private DTOMapper dtoMapper;

    @MockBean
    private DAO dao;

    @BeforeEach
    public void setup(){
        doNothing().when(dao).update(any());
        doNothing().when(recordDataCleaner).cleanData(any());
        when(dtoMapper.transform(anyList())).thenReturn(null);

    }

	@Test
	public void doesContainsCloud() throws Exception {

        ListBucketFileMap map = new ListBucketFileMap();
        List<Contents> contents = new ArrayList<>();
        Contents content = new Contents();
        content.setKey("key");
        contents.add(content);
        contents.add(content);
        map.setContents(contents);
	    when(senateStockWatcherClientMock.getFilemapList()).thenReturn(map);

        List<Record> records = new ArrayList<>();
        Record record = new Record();
        record.setFirstName("first name");
        record.setLastName("last name");
        records.add(record);
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
