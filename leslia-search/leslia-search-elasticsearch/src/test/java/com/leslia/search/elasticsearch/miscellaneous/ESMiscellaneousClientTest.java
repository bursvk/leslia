package com.leslia.search.elasticsearch.miscellaneous;

import org.apache.http.HttpHost;
import org.elasticsearch.Build;
import org.elasticsearch.Version;
import org.elasticsearch.action.main.MainResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.cluster.ClusterName;
import org.elasticsearch.protocol.xpack.XPackInfoRequest;
import org.elasticsearch.protocol.xpack.XPackInfoResponse;
import org.elasticsearch.protocol.xpack.XPackUsageRequest;
import org.elasticsearch.protocol.xpack.XPackUsageResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.EnumSet;

public class ESMiscellaneousClientTest {

    private static Logger logger= LoggerFactory.getLogger(ESMiscellaneousClientTest.class);

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
    public void test() throws IOException {
        MainResponse response = client.info(RequestOptions.DEFAULT);
        ClusterName clusterName = response.getClusterName();
        String clusterUuid = response.getClusterUuid();
        String nodeName = response.getNodeName();
        Version version = response.getVersion();
        Build build = response.getBuild();
        boolean response1 = client.ping(RequestOptions.DEFAULT);

        XPackInfoRequest request = new XPackInfoRequest();
        request.setVerbose(true);
        request.setCategories(EnumSet.of(
                XPackInfoRequest.Category.BUILD,
                XPackInfoRequest.Category.LICENSE,
                XPackInfoRequest.Category.FEATURES));
        XPackInfoResponse response2 = client.xpack().info(request, RequestOptions.DEFAULT);
        logger.info(response2+"");

        XPackUsageRequest request4 = new XPackUsageRequest();
        XPackUsageResponse response4 = client.xpack().usage(request4, RequestOptions.DEFAULT);

        logger.info(response4+"");

    }





}
