package com.leslia.search.elasticsearch.analyze;

import org.apache.http.HttpHost;
import org.elasticsearch.action.admin.indices.analyze.AnalyzeRequest;
import org.elasticsearch.action.admin.indices.analyze.AnalyzeResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class ESAnalyzeClientTest {

    private Logger logger= LoggerFactory.getLogger(ESAnalyzeClientTest.class);
    private RestHighLevelClient client;

    @Before
    public void init(){
        HttpHost httpHost=new HttpHost("localhost", 9200, "http");
        RestClientBuilder clientBuilder= RestClient.builder(httpHost);
        client=new RestHighLevelClient(clientBuilder);
    }

    @After
    public void close() throws IOException {
        client.close();
    }

    @Test
    public void analyze() throws IOException{
        AnalyzeRequest request = new AnalyzeRequest();
        request.text("Some text to analyze", "Some more text to analyze");
        request.analyzer("english");
        AnalyzeResponse response = client.indices().analyze(request, RequestOptions.DEFAULT);
        logger.info(response+"");
    }

    @Test
    public void analyze2() throws IOException{
        AnalyzeRequest request = new AnalyzeRequest();
        request.text("<b>Some text to analyze</b>");
        request.addCharFilter("html_strip");
        request.tokenizer("standard");
        request.addTokenFilter("lowercase");

     /*   Map<String, Object> stopFilter = new HashMap<>();
        stopFilter.put("type", "stop");
        stopFilter.put("stopwords", new String[]{ "to" });
        request.addTokenFilter(stopFilter);*/
        AnalyzeResponse response = client.indices().analyze(request, RequestOptions.DEFAULT);
        logger.info(response+"");
    }








}
