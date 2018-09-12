package com.leslia.search.solr;

import org.apache.solr.client.solrj.impl.HttpSolrClient;

public class Solr {

    private static final String solrUrl="http://localhost:8983/solr/bless";

    public static HttpSolrClient client;

    static{
        client=new HttpSolrClient.Builder(solrUrl)
                .withConnectionTimeout(10000)
                .withSocketTimeout(60000).build();
    }


    public static HttpSolrClient getClient() {
        return client;
    }

    public static void setClient(HttpSolrClient client) {
        Solr.client = client;
    }


}
