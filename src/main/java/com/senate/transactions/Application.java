package com.senate.transactions;

import com.senate.transactions.client.db.DAO;
import com.senate.transactions.client.db.DTOMapper;
import com.senate.transactions.client.db.model.Transaction;
import com.senate.transactions.client.senatestockwatcher.RecordDataCleaner;
import com.senate.transactions.client.senatestockwatcher.SenateStockWatcherClient;
import com.senate.transactions.model.ListBucketFileMap;
import com.senate.transactions.model.Record;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.function.context.FunctionalSpringApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;
import java.util.function.Function;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		FunctionalSpringApplication.run(Application.class, args);
	}

	@Autowired
	private SenateStockWatcherClient client;

	@Autowired
	private RecordDataCleaner recordDataCleaner;

	@Autowired
	private DTOMapper dtoMapper;

	@Autowired
	private DAO dao;

	@Bean
	public Function<String, String> processStockFilings() {
		return value -> {
			ListBucketFileMap listBucketFileMap = client.getFilemapList();

			if (listBucketFileMap != null && listBucketFileMap.getContents() != null &&
					!listBucketFileMap.getContents().isEmpty()) {

				String key = listBucketFileMap.getContents().get(1).getKey();
				List<Record> records = client.getDailyTransactions(key);
				records.forEach(recordDataCleaner::cleanData);
				List<Transaction> dtoTransactions = dtoMapper.transform(records);
				dao.update(dtoTransactions);

				return records.get(0).getFirstName();
			} else {
				return "error processing stock filings";

			}
		};
	}

}
