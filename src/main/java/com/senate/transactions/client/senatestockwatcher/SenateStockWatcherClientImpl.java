package com.senate.transactions.client.senatestockwatcher;

import com.senate.transactions.model.Contents;
import com.senate.transactions.model.ListBucketFileMap;
import com.senate.transactions.model.Record;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Component
public class SenateStockWatcherClientImpl implements SenateStockWatcherClient {

    @Value("${base.url}")
    private String baseUrl;

    @Value("${filemap.xml.url}")
    private String filemapXMLUrl;

    private String baseDailyTransactionsUrl;

    private final RestTemplate restTemplate;

    public SenateStockWatcherClientImpl(RestTemplateBuilder restTemplateBuilder){
        this.restTemplate = restTemplateBuilder.build();
    }

    @Override
    public ListBucketFileMap getFilemapList() {
        String url = baseUrl + filemapXMLUrl;
        ListBucketFileMap listBucketFileMap = new ListBucketFileMap();
        Contents[] list = restTemplate.getForObject(url, Contents[].class);
        if (list != null)
            listBucketFileMap.setContents(Arrays.asList(list));
        return listBucketFileMap;
    }

    @Override
    public List<Record> getDailyTransactions(String jsonFilename) {
        String url = baseUrl + jsonFilename;

        List<Record> records = Arrays.asList(restTemplate.getForObject(url, Record[].class));
        return records;
    }
}
