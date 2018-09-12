package com.leslia.search.elasticsearch.search;

import org.apache.http.HttpHost;
import org.elasticsearch.action.ActionListener;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.script.ScriptType;
import org.elasticsearch.script.mustache.MultiSearchTemplateRequest;
import org.elasticsearch.script.mustache.MultiSearchTemplateResponse;
import org.elasticsearch.script.mustache.SearchTemplateRequest;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ESSearchMultiTempleClientTest {

    private static Logger logger= LoggerFactory.getLogger(ESSearchMultiTempleClientTest.class);
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
    public void searchMultiTemple() throws IOException{
        String [] searchTerms = {"elasticsearch", "logstash", "kibana"};

        MultiSearchTemplateRequest multiRequest = new MultiSearchTemplateRequest();
        for (String searchTerm : searchTerms) {
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
            scriptParams.put("value", searchTerm);
            scriptParams.put("size", 5);
            request.setScriptParams(scriptParams);

            multiRequest.add(request);
        }
        MultiSearchTemplateResponse multiResponse = client.msearchTemplate(multiRequest, RequestOptions.DEFAULT);
        logger.info(multiResponse+"");
    }


    @Test
    public void searchMultiTemple2() throws IOException{
        String [] searchTerms = {"elasticsearch", "logstash", "kibana"};
        MultiSearchTemplateRequest multiRequest = new MultiSearchTemplateRequest();
        for (String searchTerm : searchTerms) {
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
            scriptParams.put("value", searchTerm);
            scriptParams.put("size", 3);
            request.setScriptParams(scriptParams);

            multiRequest.add(request);
        }

        ActionListener<MultiSearchTemplateResponse> listener = new ActionListener<MultiSearchTemplateResponse>() {
            @Override
            public void onResponse(MultiSearchTemplateResponse response) {
                logger.info(response+"");
            }

            @Override
            public void onFailure(Exception e) {
                e.printStackTrace();
            }
        };

        client.msearchTemplateAsync(multiRequest, RequestOptions.DEFAULT, listener);
        System.in.read();
    }






}
