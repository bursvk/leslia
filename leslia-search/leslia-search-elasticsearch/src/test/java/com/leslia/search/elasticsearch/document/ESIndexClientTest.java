package com.leslia.search.elasticsearch.document;

import org.apache.http.HttpHost;
import org.elasticsearch.ElasticsearchException;
import org.elasticsearch.action.ActionListener;
import org.elasticsearch.action.DocWriteRequest;
import org.elasticsearch.action.DocWriteResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.Strings;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.get.GetResult;
import org.elasticsearch.rest.RestStatus;
import org.elasticsearch.search.fetch.subphase.FetchSourceContext;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class ESIndexClientTest {

    private static Logger logger= LoggerFactory.getLogger(ESIndexClientTest.class);

    private RestHighLevelClient client;




    @Before
    public void init(){
        HttpHost httpHost=new HttpHost("localhost", 9200, "http");
        RestClientBuilder clientBuilder=RestClient.builder(httpHost);
        client=new RestHighLevelClient(clientBuilder);
    }

    @After
    public void close() throws IOException{
        client.close();
    }

    @Test
    public void index1() throws IOException{
        String jsonString = "{" +
                "\"user\":\"kimchy\"," +
                "\"postDate\":\"2013-01-30\"," +
                "\"message\":\"trying out Elasticsearch\"" +
                "}";
        IndexRequest request = new IndexRequest("posts", "doc", "1");
        request.source(jsonString, XContentType.JSON);
        IndexResponse indexResponse = client.index(request, RequestOptions.DEFAULT);
        logger.info(indexResponse.toString());
    }


    @Test
    public void index2() throws IOException{
        Map<String, Object> jsonMap = new HashMap<>();
        jsonMap.put("user", "kimchy");
        jsonMap.put("postDate", new Date());
        jsonMap.put("message", "trying out Elasticsearch");
        IndexRequest indexRequest = new IndexRequest("posts", "doc", "1");
        indexRequest.source(jsonMap);
        IndexResponse indexResponse = client.index(indexRequest, RequestOptions.DEFAULT);
        logger.info(indexResponse.toString());
    }


    @Test
    public void index3() throws IOException{
        XContentBuilder builder = XContentFactory.jsonBuilder();
        builder.startObject();
        {
            builder.field("user", "kimchy");
            builder.timeField("postDate", new Date());
            builder.field("message", "trying out Elasticsearch");
        }
        builder.endObject();
        IndexRequest indexRequest = new IndexRequest("posts", "doc", "1");
        indexRequest.source(builder);
        IndexResponse indexResponse = client.index(indexRequest, RequestOptions.DEFAULT);
        logger.info(indexResponse.toString());
    }

    @Test
    public void index4() throws IOException{
        IndexRequest indexRequest = new IndexRequest("posts", "doc", "1");
        indexRequest.timeout(TimeValue.timeValueSeconds(10));
        indexRequest.opType(DocWriteRequest.OpType.CREATE);
        indexRequest.source("user", "kimchy",
                        "postDate", new Date(),
                        "message", "trying out Elasticsearch");
        IndexResponse indexResponse = client.index(indexRequest, RequestOptions.DEFAULT);
        logger.info(indexResponse.toString());
    }

    @Test
    public void index5() throws Exception{
        IndexRequest indexRequest = new IndexRequest("posts", "doc", "1");
        indexRequest.version(10);
        indexRequest.timeout(TimeValue.timeValueSeconds(10));
        indexRequest.opType(DocWriteRequest.OpType.INDEX);
        indexRequest.source("user", "kimchy",
                "postDate", new Date(),
                "message", "trying out Elasticsearch1");

        ActionListener<IndexResponse> listener=new ActionListener<IndexResponse>() {
            @Override
            public void onResponse(IndexResponse indexResponse) {
                logger.info("执行成功");
                logger.info(indexResponse.toString());
            }
            @Override
            public void onFailure(Exception e) {
                logger.info("执行失败");
                logger.info(e.toString());
            }
        };
        client.indexAsync(indexRequest,RequestOptions.DEFAULT, listener);
        System.in.read();
    }


    @Test
    public void get1() throws IOException{
        GetRequest request = new GetRequest("posts", "doc", "1");
        //
        //request.fetchSourceContext(FetchSourceContext.DO_NOT_FETCH_SOURCE);

      /*  String[] includes = Strings.EMPTY_ARRAY;
        String[] excludes = new String[]{"user"};
        FetchSourceContext fetchSourceContext =
                new FetchSourceContext(true, includes, excludes);
        request.fetchSourceContext(fetchSourceContext);*/

        //request.storedFields("message");
        GetResponse getResponse = client.get(request, RequestOptions.DEFAULT);
        logger.info(getResponse.toString());
        //logger.info(getResponse.getSource().get("message")+"");
    }


    @Test
    public void get2() throws Exception{
        GetRequest request = new GetRequest("posts", "doc", "1");
        ActionListener<GetResponse> listener=new ActionListener<GetResponse>() {
            @Override
            public void onResponse(GetResponse getResponse) {
                logger.info("执行成功");
                logger.info(getResponse.toString());
            }
            @Override
            public void onFailure(Exception e) {
                logger.info("执行失败");
                logger.info(e.toString());
            }
        };
        client.getAsync(request,RequestOptions.DEFAULT, listener);
        System.in.read();
    }

    @Test
    public void exists1() throws Exception{
        GetRequest getRequest = new GetRequest("posts", "doc", "1");
        getRequest.fetchSourceContext(new FetchSourceContext(false));
        getRequest.storedFields("_none_");
        boolean exists = client.exists(getRequest, RequestOptions.DEFAULT);
        logger.info(exists+"");
    }

    @Test
    public void exists2() throws IOException{
        GetRequest getRequest = new GetRequest("posts", "doc", "1");
        getRequest.fetchSourceContext(new FetchSourceContext(false));
        getRequest.storedFields("_none_");
        ActionListener<Boolean> listener = new ActionListener<Boolean>() {
            @Override
            public void onResponse(Boolean exists) {
                logger.info(exists+"");
            }

            @Override
            public void onFailure(Exception e) {
                logger.error(e+"");
            }
        };
        client.existsAsync(getRequest, RequestOptions.DEFAULT, listener);
        System.in.read();
    }

    @Test
    public void delete1() throws IOException{
        try {
            DeleteRequest request = new DeleteRequest("posts", "doc", "3");
            //request.version(10);
            DeleteResponse deleteResponse = client.delete(request, RequestOptions.DEFAULT);
            logger.info(deleteResponse.toString());
            if (deleteResponse.getResult() == DocWriteResponse.Result.NOT_FOUND) {
                logger.info("not found ....");
            }
        }catch (ElasticsearchException exception){
            if (exception.status() == RestStatus.CONFLICT) {
                logger.error("version fail ....");
            }
        }
    }

    @Test
    public void delete2() throws IOException{
        DeleteRequest request = new DeleteRequest("posts", "doc", "1");
        request.version(10);
        ActionListener<DeleteResponse> listener = new ActionListener<DeleteResponse>() {
            @Override
            public void onResponse(DeleteResponse deleteResponse) {
                logger.info(deleteResponse+"");
                if (deleteResponse.getResult() == DocWriteResponse.Result.NOT_FOUND) {
                    logger.info("not found ....");
                }
            }

            @Override
            public void onFailure(Exception e) {
                ElasticsearchException exception=(ElasticsearchException)e;
                if (exception.status() == RestStatus.CONFLICT) {
                    logger.info("version fail ....");
                }
            }
        };
        client.deleteAsync(request, RequestOptions.DEFAULT, listener);
        System.in.read();
    }

    @Test
    public void update1() throws IOException{
        try {
            Map<String,Object> map=new HashMap<>();
            map.put("key1","value1");
            map.put("key2","value2");
            map.put("key3","value3");
            Map<String, Object> jsonMap = new HashMap<>();
            jsonMap.put("user","luca");
            jsonMap.put("updated", new Date());
            jsonMap.put("reason", "daily update1");
            jsonMap.put("innerObject",map);
            UpdateRequest request = new UpdateRequest("posts", "doc", "4").doc(jsonMap);
            String[] includes = Strings.EMPTY_ARRAY;
            String[] excludes = new String[]{"updated"};
            request.fetchSource(new FetchSourceContext(true, includes, excludes));
            //若指定文档不存在 则创建
            request.docAsUpsert(true);
            UpdateResponse updateResponse = client.update(request, RequestOptions.DEFAULT);
            if (updateResponse.getResult() == DocWriteResponse.Result.CREATED) {
                logger.info("处理首次创建文档的情况");
            } else if (updateResponse.getResult() == DocWriteResponse.Result.UPDATED) {
                logger.info("处理文档更新的案例");
            } else if (updateResponse.getResult() == DocWriteResponse.Result.DELETED) {
                logger.info("处理删除文档的情况");
            } else if (updateResponse.getResult() == DocWriteResponse.Result.NOOP) {
                logger.info("处理文档未受更新影响的情况，即未对文档执行任何操作（noop）");
            }
            GetResult result = updateResponse.getGetResult();
            if (result!=null) {
                String sourceAsString = result.sourceAsString();
                logger.info("sourceAsString:{}",sourceAsString);
                Map<String, Object> sourceAsMap = result.sourceAsMap();
                logger.info("sourceAsMap:{}",sourceAsMap);
                byte[] sourceAsBytes = result.source();
                logger.info("sourceAsBytes:{}",sourceAsBytes);
            } else {
                logger.info("result null ....");
            }
        }catch (ElasticsearchException  e){
            if (e.status() == RestStatus.NOT_FOUND) {
                logger.error("文档不存在");
            }
            if (e.status() == RestStatus.CONFLICT) {
                logger.error("版本冲突");
            }
        }
    }


    @Test
    public void update2() throws IOException{
        Map<String, Object> jsonMap = new HashMap<>();
        jsonMap.put("updated", new Date());
        jsonMap.put("reason", "daily update2");
        UpdateRequest request = new UpdateRequest("posts", "doc", "1")
                .doc(jsonMap);
        String[] includes = Strings.EMPTY_ARRAY;
        String[] excludes = new String[]{"updated"};
        request.fetchSource(new FetchSourceContext(true, includes, excludes));
        ActionListener<UpdateResponse> listener = new ActionListener<UpdateResponse>() {
            @Override
            public void onResponse(UpdateResponse updateResponse) {
                GetResult result = updateResponse.getGetResult();
                Map<String, Object> sourceAsMap = result!=null?result.sourceAsMap():null;
                logger.info(sourceAsMap+"");
            }
            @Override
            public void onFailure(Exception e) {
                logger.error(e+"");
            }
        };
        client.updateAsync(request, RequestOptions.DEFAULT, listener);
        System.in.read();
    }













}
