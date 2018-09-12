package com.leslia.search.elasticsearch.search;

import org.apache.http.HttpHost;
import org.elasticsearch.action.ActionListener;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.client.*;
import org.elasticsearch.script.ScriptType;
import org.elasticsearch.script.mustache.SearchTemplateRequest;
import org.elasticsearch.script.mustache.SearchTemplateResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ESSearchTemplateClientTest {


    private static Logger logger= LoggerFactory.getLogger(ESSearchTemplateClientTest.class);

    private RestHighLevelClient client;

    private RestClient restClient;

    @Before
    public void init(){
        HttpHost httpHost=new HttpHost("localhost", 9200, "http");
        RestClientBuilder clientBuilder= RestClient.builder(httpHost);
        client=new RestHighLevelClient(clientBuilder);
        restClient = RestClient.builder(new HttpHost("localhost", 9200, "http")).build();
    }

    @After
    public void close() throws IOException {
        client.close();
        restClient.close();
    }

    @Test
    public void searchTemple() throws IOException{
        SearchTemplateRequest request = new SearchTemplateRequest();
        request.setRequest(new SearchRequest("posts"));

        request.setScriptType(ScriptType.INLINE);
        request.setScript(
                "{" +
                        "  \"query\": { \"match\" : { \"{{field}}\" : \"{{value}}\" } }," +
                        "  \"size\" : \"{{size}}\"" +
                        "}");

        Map<String, Object> scriptParams = new HashMap<>();
        scriptParams.put("field", "title");
        scriptParams.put("value", "elasticsearch");
        scriptParams.put("size", 5);
        request.setScriptParams(scriptParams);
        SearchTemplateResponse response = client.searchTemplate(request, RequestOptions.DEFAULT);
        logger.info(response+"");
    }

    @Test
    public void searchTemple1() throws IOException{
        SearchTemplateRequest request = new SearchTemplateRequest();
        request.setRequest(new SearchRequest("posts"));

        request.setScriptType(ScriptType.STORED);
        request.setScript("title_search");

        Map<String, Object> params = new HashMap<>();
        params.put("field", "title");
        params.put("value", "elasticsearch1");
        params.put("size", 5);
        request.setScriptParams(params);
        SearchTemplateResponse response = client.searchTemplate(request, RequestOptions.DEFAULT);
        logger.info(response+"");
    }


    @Test
    public void searchTemple2() throws IOException{
        SearchTemplateRequest request = new SearchTemplateRequest();
        request.setRequest(new SearchRequest("posts"));

        request.setScriptType(ScriptType.STORED);
        request.setScript("title_search");

        Map<String, Object> params = new HashMap<>();
        params.put("field", "title");
        params.put("value", "elasticsearch");
        params.put("size", 5);
        request.setScriptParams(params);

        ActionListener<SearchTemplateResponse> listener = new ActionListener<SearchTemplateResponse>() {
            @Override
            public void onResponse(SearchTemplateResponse response) {
                logger.info(response+"");
            }

            @Override
            public void onFailure(Exception e) {
                e.printStackTrace();
            }
        };
        client.searchTemplateAsync(request, RequestOptions.DEFAULT, listener);
        System.in.read();
    }

    @Test
    public void searchTemple3() throws IOException{
        Request scriptRequest = new Request("POST", "_scripts/title_search");
        scriptRequest.setJsonEntity(
                "{" +
                        "  \"script\": {" +
                        "    \"lang\": \"mustache\"," +
                        "    \"source\": {" +
                        "      \"query\": { \"match\" : { \"{{field}}\" : \"{{value}}\" } }," +
                        "      \"size\" : \"{{size}}\"" +
                        "    }" +
                        "  }" +
                        "}");
        Response scriptResponse = restClient.performRequest(scriptRequest);
        logger.info(scriptResponse+"");
    }




}
