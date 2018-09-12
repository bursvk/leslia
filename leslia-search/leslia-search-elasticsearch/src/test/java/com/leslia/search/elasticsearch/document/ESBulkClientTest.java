package com.leslia.search.elasticsearch.document;

import org.apache.http.HttpHost;
import org.elasticsearch.action.ActionListener;
import org.elasticsearch.action.DocWriteRequest;
import org.elasticsearch.action.DocWriteResponse;
import org.elasticsearch.action.bulk.BulkItemResponse;
import org.elasticsearch.action.bulk.BulkProcessor;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.function.BiConsumer;

public class ESBulkClientTest {

    private static Logger logger= LoggerFactory.getLogger(ESBulkClientTest.class);

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
    public void bulk1() throws IOException{
        BulkRequest request = new BulkRequest();
        request.add(new IndexRequest("posts", "doc", "1")
                .source(XContentType.JSON,"field", "foo"));
        request.add(new IndexRequest("posts", "doc", "2")
                .source(XContentType.JSON,"field", "bar"));
        request.add(new IndexRequest("posts", "doc", "3")
                .source(XContentType.JSON,"field", "baz"));
        request.add(new DeleteRequest("posts", "doc", "3"));
        request.add(new UpdateRequest("posts", "doc", "2")
                .doc(XContentType.JSON,"other", "test"));
        BulkResponse bulkResponse = client.bulk(request, RequestOptions.DEFAULT);
        for (BulkItemResponse bulkItemResponse : bulkResponse) {
            DocWriteResponse itemResponse = bulkItemResponse.getResponse();
            if (bulkItemResponse.getOpType() == DocWriteRequest.OpType.INDEX){
                IndexResponse indexResponse = (IndexResponse) itemResponse;
                logger.info("index ....");
            } else if(bulkItemResponse.getOpType() == DocWriteRequest.OpType.CREATE){
                IndexResponse indexResponse = (IndexResponse) itemResponse;
                logger.info("create ....");
            }else if (bulkItemResponse.getOpType() == DocWriteRequest.OpType.UPDATE) {
                UpdateResponse updateResponse = (UpdateResponse) itemResponse;
                logger.info("update ....");
            } else if (bulkItemResponse.getOpType() == DocWriteRequest.OpType.DELETE) {
                DeleteResponse deleteResponse = (DeleteResponse) itemResponse;
                logger.info("delete ....");
            }
        }
        if (bulkResponse.hasFailures()) {
            for (BulkItemResponse bulkItemResponse : bulkResponse) {
                if (bulkItemResponse.isFailed()) {
                    BulkItemResponse.Failure failure = bulkItemResponse.getFailure();
                    logger.info(failure+"");
                }
            }
        }
    }

    @Test
    public void bulk2() throws IOException{
        BulkRequest request = new BulkRequest();
        request.add(new IndexRequest("posts", "doc", "1")
                .source(XContentType.JSON,"field", "foo1"));
        request.add(new IndexRequest("posts", "doc", "2")
                .source(XContentType.JSON,"field", "bar1"));
        request.add(new IndexRequest("posts", "doc", "3")
                .source(XContentType.JSON,"field", "baz1"));

        ActionListener<BulkResponse> listener = new ActionListener<BulkResponse>() {
            @Override
            public void onResponse(BulkResponse bulkResponse) {
                for (BulkItemResponse bulkItemResponse : bulkResponse) {
                    logger.info(bulkItemResponse.getOpType()+" ....");
                }
            }
            @Override
            public void onFailure(Exception e) {
                logger.error(e+"");
            }
        };
        client.bulkAsync(request, RequestOptions.DEFAULT, listener);
        System.in.read();
    }



    @Test
    public void bulkProcessor() throws Exception{
        BulkProcessor.Listener listener = new BulkProcessor.Listener() {
            @Override
            public void beforeBulk(long executionId, BulkRequest request) {
                int numberOfActions = request.numberOfActions();
                logger.debug("Executing bulk [{}] with {} requests",
                        executionId, numberOfActions);
            }
            @Override
            public void afterBulk(long executionId, BulkRequest request,
                                  BulkResponse response) {
                if (response.hasFailures()) {
                    logger.warn("Bulk [{}] executed with failures", executionId);
                } else {
                    logger.debug("Bulk [{}] completed in {} milliseconds",
                            executionId, response.getTook().getMillis());
                }
            }
            @Override
            public void afterBulk(long executionId, BulkRequest request, Throwable failure) {
                logger.error("Failed to execute bulk", failure);
            }
        };

        BiConsumer<BulkRequest, ActionListener<BulkResponse>> bulkConsumer =
                (request, bulkListener) -> client.bulkAsync(request, RequestOptions.DEFAULT, bulkListener);

        BulkProcessor bulkProcessor = BulkProcessor.builder(bulkConsumer, listener).build();
        IndexRequest one = new IndexRequest("posts", "doc", "1").
                source(XContentType.JSON, "title",
                        "In which order are my Elasticsearch queries executed?");
        IndexRequest two = new IndexRequest("posts", "doc", "2")
                .source(XContentType.JSON, "title",
                        "Current status and upcoming changes in Elasticsearch");
        IndexRequest three = new IndexRequest("posts", "doc", "3")
                .source(XContentType.JSON, "title",
                        "The Future of Federated Search in Elasticsearch");

        bulkProcessor.add(one);
        bulkProcessor.add(two);
        bulkProcessor.add(three);
        boolean terminated = bulkProcessor.awaitClose(30L, TimeUnit.SECONDS);
        logger.info(terminated+"");
        //bulkProcessor.close();
        //System.in.read();
    }


}
