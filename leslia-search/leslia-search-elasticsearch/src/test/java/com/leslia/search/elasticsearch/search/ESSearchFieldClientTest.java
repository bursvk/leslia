package com.leslia.search.elasticsearch.search;

import org.apache.http.HttpHost;
import org.elasticsearch.action.ActionListener;
import org.elasticsearch.action.fieldcaps.FieldCapabilities;
import org.elasticsearch.action.fieldcaps.FieldCapabilitiesRequest;
import org.elasticsearch.action.fieldcaps.FieldCapabilitiesResponse;
import org.elasticsearch.action.support.IndicesOptions;
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
import java.util.Map;

public class ESSearchFieldClientTest {

    private Logger logger= LoggerFactory.getLogger(ESSearchFieldClientTest.class);

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
    public void field() throws IOException{
        FieldCapabilitiesRequest request = new FieldCapabilitiesRequest()
                .fields("user","title")
                .indices("posts", "authors", "contributors");
        request.indicesOptions(IndicesOptions.lenientExpandOpen());
        FieldCapabilitiesResponse response = client.fieldCaps(request, RequestOptions.DEFAULT);

        Map<String, FieldCapabilities> userResponse = response.getField("user");

        logger.info(userResponse.toString());
    }

    @Test
    public void field2() throws IOException{
        FieldCapabilitiesRequest request = new FieldCapabilitiesRequest()
                .fields("user")
                .indices("posts", "authors", "contributors");
        request.indicesOptions(IndicesOptions.lenientExpandOpen());

        ActionListener<FieldCapabilitiesResponse> listener = new ActionListener<FieldCapabilitiesResponse>() {
            @Override
            public void onResponse(FieldCapabilitiesResponse response) {
                logger.info(response+"");
            }

            @Override
            public void onFailure(Exception e) {
                e.printStackTrace();
            }
        };
        client.fieldCapsAsync(request, RequestOptions.DEFAULT, listener);
        System.in.read();
    }



}
