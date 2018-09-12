package com.leslia.search.elasticsearch.document;

import org.apache.http.HttpHost;
import org.elasticsearch.ElasticsearchException;
import org.elasticsearch.action.ActionListener;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.get.MultiGetItemResponse;
import org.elasticsearch.action.get.MultiGetRequest;
import org.elasticsearch.action.get.MultiGetResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.Strings;
import org.elasticsearch.search.fetch.subphase.FetchSourceContext;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Map;

public class ESMultiGetClientTest {

    private Logger logger= LoggerFactory.getLogger(ESMultiGetClientTest.class);

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
    public void multiGet1() throws IOException{
        MultiGetRequest request = new MultiGetRequest();
        String[] includes = Strings.EMPTY_ARRAY;
        String[] excludes = new String[] {"title"};
        FetchSourceContext fetchSourceContext =
                new FetchSourceContext(true, includes, excludes);
        request.add(new MultiGetRequest.Item("posts", "doc", "10").fetchSourceContext(fetchSourceContext));
        request.add(new MultiGetRequest.Item("posts", "doc", "2"));
        MultiGetResponse response = client.mget(request, RequestOptions.DEFAULT);

        for(MultiGetItemResponse firstItem:response){
        if(firstItem.isFailed()){
            Exception e = firstItem.getFailure().getFailure();
            ElasticsearchException ee = (ElasticsearchException) e;
            logger.info(ee+"");
        }
        GetResponse firstGet = firstItem.getResponse();
        String index = firstItem.getIndex();
        String type = firstItem.getType();
        String id = firstItem.getId();
        if (firstGet.isExists()) {
            long version = firstGet.getVersion();
            logger.info("version:"+version);
            String sourceAsString = firstGet.getSourceAsString();
            logger.info("sourceAsString:"+sourceAsString);
            Map<String, Object> sourceAsMap = firstGet.getSourceAsMap();
            logger.info("sourceAsMap:"+sourceAsMap);
            byte[] sourceAsBytes = firstGet.getSourceAsBytes();
            logger.info("sourceAsBytes:"+sourceAsBytes);
        } else {
        }
    }

    }

    @Test
    public void multiGet2() throws IOException{
        MultiGetRequest request = new MultiGetRequest();
        request.add(new MultiGetRequest.Item("posts", "doc", "1"));
        request.add(new MultiGetRequest.Item("posts", "doc", "2"));
        ActionListener<MultiGetResponse> listener = new ActionListener<MultiGetResponse>() {
            @Override
            public void onResponse(MultiGetResponse response) {
                for(MultiGetItemResponse firstItem:response){
                    GetResponse firstGet = firstItem.getResponse();
                    Map<String, Object> sourceAsMap = firstGet.getSourceAsMap();
                    logger.info("sourceAsMap:"+sourceAsMap);
                }
            }
            @Override
            public void onFailure(Exception e) {
                logger.info(e+"");
            }
        };
        client.mgetAsync(request, RequestOptions.DEFAULT, listener);
        System.in.read();
    }





}
