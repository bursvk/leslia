package com.leslia.search.elasticsearch.search;

import org.apache.http.HttpHost;
import org.elasticsearch.action.ActionListener;
import org.elasticsearch.action.search.*;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.Strings;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.Scroll;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class ESSearchScrollClientTest {

    private static Logger logger= LoggerFactory.getLogger(ESSearchScrollClientTest.class);

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
    public void searchScroll1() throws IOException{
        final Scroll scroll = new Scroll(TimeValue.timeValueMinutes(1L));
        SearchRequest searchRequest = new SearchRequest("posts");
        searchRequest.scroll(scroll);
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.matchQuery("title", "Elasticsearch"));
        String[] includeFields = Strings.EMPTY_ARRAY;
        String[] excludeFields = new String[] {"innerObject"};
        searchSourceBuilder.fetchSource(includeFields, excludeFields);
        searchRequest.source(searchSourceBuilder);

        SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
        logger.info(searchResponse+"");
        String scrollId = searchResponse.getScrollId();
        SearchHit[] searchHits = searchResponse.getHits().getHits();

        while (searchHits != null && searchHits.length > 0) {
            SearchScrollRequest scrollRequest = new SearchScrollRequest(scrollId);
            scrollRequest.scroll(scroll);
            searchResponse = client.scroll(scrollRequest, RequestOptions.DEFAULT);
            scrollId = searchResponse.getScrollId();
            searchHits = searchResponse.getHits().getHits();
        }
        logger.info(searchResponse+"");
        ClearScrollRequest clearScrollRequest = new ClearScrollRequest();
        clearScrollRequest.addScrollId(scrollId);
        ClearScrollResponse clearScrollResponse = client.clearScroll(clearScrollRequest, RequestOptions.DEFAULT);
        boolean succeeded = clearScrollResponse.isSucceeded();
        logger.info(succeeded+"");
    }


    @Test
    public void searchScroll2() throws IOException{
        final Scroll scroll = new Scroll(TimeValue.timeValueMinutes(1L));
        SearchRequest searchRequest = new SearchRequest("posts");
        searchRequest.scroll(scroll);
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.matchQuery("title", "Elasticsearch"));
        String[] includeFields = Strings.EMPTY_ARRAY;
        String[] excludeFields = new String[] {"innerObject"};
        searchSourceBuilder.fetchSource(includeFields, excludeFields);
        searchRequest.source(searchSourceBuilder);

        SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
        logger.info(searchResponse+"");
        String scrollId = searchResponse.getScrollId();
        ClearScrollRequest request = new ClearScrollRequest();
        request.addScrollId(scrollId);
        ActionListener<ClearScrollResponse> listener = new ActionListener<ClearScrollResponse>() {
            @Override
            public void onResponse(ClearScrollResponse clearScrollResponse) {
                boolean success = clearScrollResponse.isSucceeded();
                int released = clearScrollResponse.getNumFreed();
                logger.info(success+"");
                logger.info(released+"");
            }

            @Override
            public void onFailure(Exception e) {
                e.printStackTrace();
            }
        };
        client.clearScrollAsync(request,RequestOptions.DEFAULT,listener);
        System.in.read();
    }



}
