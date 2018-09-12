package com.leslia.search.elasticsearch.search;

import org.apache.http.HttpHost;
import org.elasticsearch.action.ActionListener;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.ShardSearchFailure;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.Strings;
import org.elasticsearch.common.unit.Fuzziness;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.rest.RestStatus;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class ESSearchClientTest {

    private static Logger logger= LoggerFactory.getLogger(ESSearchClientTest.class);

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
    public void search1() throws IOException{
        SearchRequest searchRequest = new SearchRequest("posts");
        searchRequest.types("doc");
     /*   searchRequest.routing("routing");
        searchRequest.indicesOptions(IndicesOptions.lenientExpandOpen());
        searchRequest.preference("_local");*/

        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        sourceBuilder.fetchSource(false);
        QueryBuilder matchQueryBuilder = QueryBuilders.matchQuery("user", "kimchy")
                .fuzziness(Fuzziness.AUTO)
                .prefixLength(3)
                .maxExpansions(10);

        sourceBuilder.query(matchQueryBuilder);
        //sourceBuilder.query(QueryBuilders.termQuery("user", "kimchy"));
        sourceBuilder.from(0);
        sourceBuilder.size(10);
        sourceBuilder.timeout(new TimeValue(60, TimeUnit.SECONDS));
        //sourceBuilder.sort(new ScoreSortBuilder().order(SortOrder.DESC));
        sourceBuilder.sort(new FieldSortBuilder("_uid").order(SortOrder.DESC));

        String[] includeFields = Strings.EMPTY_ARRAY;
        String[] excludeFields = new String[] {"innerObject"};
        sourceBuilder.fetchSource(includeFields, excludeFields);
        searchRequest.source(sourceBuilder);
        SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
        logger.info(searchResponse.toString());
    }


    @Test
    public void search2() throws IOException{
        SearchRequest searchRequest = new SearchRequest("posts");
        searchRequest.types("doc");

        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        sourceBuilder.query(QueryBuilders.matchAllQuery());
        sourceBuilder.timeout(new TimeValue(60, TimeUnit.SECONDS));      //高亮字段展示
        HighlightBuilder highlightBuilder = new HighlightBuilder();
        HighlightBuilder.Field highlightTitle =
                new HighlightBuilder.Field("title");
        highlightTitle.highlighterType("unified");
        highlightBuilder.field(highlightTitle);
        HighlightBuilder.Field highlightUser = new HighlightBuilder.Field("user");
        highlightBuilder.field(highlightUser);
        sourceBuilder.highlighter(highlightBuilder);

        searchRequest.source(sourceBuilder);

        ActionListener<SearchResponse> listener = new ActionListener<SearchResponse>() {
            @Override
            public void onResponse(SearchResponse searchResponse) {
                logger.info(searchResponse+"");
                RestStatus status = searchResponse.status();
                TimeValue took = searchResponse.getTook();
                Boolean terminatedEarly = searchResponse.isTerminatedEarly();
                boolean timedOut = searchResponse.isTimedOut();

                int totalShards = searchResponse.getTotalShards();
                int successfulShards = searchResponse.getSuccessfulShards();
                int failedShards = searchResponse.getFailedShards();
                for (ShardSearchFailure failure : searchResponse.getShardFailures()) {
                    // failures should be handled here
                }

                SearchHits hits = searchResponse.getHits();
                long totalHits = hits.getTotalHits();
                float maxScore = hits.getMaxScore();
                SearchHit[] searchHits = hits.getHits();
                for (SearchHit hit : searchHits) {
                    // do something with the SearchHit
                    String index = hit.getIndex();
                    String type = hit.getType();
                    String id = hit.getId();
                    float score = hit.getScore();

                    String sourceAsString = hit.getSourceAsString();
                    Map<String, Object> sourceAsMap = hit.getSourceAsMap();
                    String documentTitle = (String) sourceAsMap.get("title");
                    //List<Object> users = (List<Object>) sourceAsMap.get("user");
                    Map<String, Object> innerObject =
                            (Map<String, Object>) sourceAsMap.get("innerObject");

                    Map<String, HighlightField> highlightFields = hit.getHighlightFields();
                    HighlightField highlight = highlightFields.get("title");
                    logger.info("highlight:{}",highlight);
                }

            }
            @Override
            public void onFailure(Exception e) {
                e.printStackTrace();
            }

        };
        client.searchAsync(searchRequest, RequestOptions.DEFAULT, listener);
        System.in.read();

    }




/*    {
        try{
            SearchRequest searchRequest = new SearchRequest("posts");
            SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
            TermsAggregationBuilder aggregation = AggregationBuilders.terms("by_company")
                    .field("company.keyword");
            aggregation.subAggregation(AggregationBuilders.avg("average_age")
                    .field("age"));
            searchSourceBuilder.aggregation(aggregation);
            SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);

            Aggregations aggregations = searchResponse.getAggregations();
            Terms byCompanyAggregation = aggregations.get("by_company");
            Terms.Bucket elasticBucket = byCompanyAggregation.getBucketByKey("Elastic");
            Avg averageAge = elasticBucket.getAggregations().get("average_age");
            double avg = averageAge.getValue();

            Map<String, Aggregation> aggregationMap = aggregations.getAsMap();
            Terms companyAggregation = (Terms) aggregationMap.get("by_company");

            List<Aggregation> aggregationList = aggregations.asList();


        }catch (IOException e){
            e.printStackTrace();
        }

    }*/



/*    {
        try{
            SearchRequest searchRequest = new SearchRequest("posts");
            SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
            SuggestionBuilder termSuggestionBuilder =
                    SuggestBuilders.termSuggestion("user").text("kmichy");
            SuggestBuilder suggestBuilder = new SuggestBuilder();
            suggestBuilder.addSuggestion("suggest_user", termSuggestionBuilder);
            searchSourceBuilder.suggest(suggestBuilder);
            SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
            Suggest suggest = searchResponse.getSuggest();
            TermSuggestion termSuggestion = suggest.getSuggestion("suggest_user");
            for (TermSuggestion.Entry entry : termSuggestion.getEntries()) {
                for (TermSuggestion.Entry.Option option : entry) {
                    String suggestText = option.getText().string();
                }
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }*/




 /*   {
        try{
            SearchRequest searchRequest = new SearchRequest("posts");
            SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
            searchSourceBuilder.profile(true);
            SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
            Map<String, ProfileShardResult> profilingResults =
                    searchResponse.getProfileResults();
            for (Map.Entry<String, ProfileShardResult> profilingResult : profilingResults.entrySet()) {
                String key = profilingResult.getKey();
                ProfileShardResult profileShardResult = profilingResult.getValue();
                List<QueryProfileShardResult> queryProfileShardResults = profileShardResult.getQueryProfileResults();
                for (QueryProfileShardResult queryProfileResult : queryProfileShardResults) {
                    for (ProfileResult profileResult : queryProfileResult.getQueryResults()) {
                        String queryName = profileResult.getQueryName();
                        long queryTimeInMillis = profileResult.getTime();
                        List<ProfileResult> profiledChildren = profileResult.getProfiledChildren();
                    }
                    CollectorResult collectorResult = queryProfileResult.getCollectorResult();
                    String collectorName = collectorResult.getName();
                    Long collectorTimeInMillis = collectorResult.getTime();
                    List<CollectorResult> profiledChildren = collectorResult.getProfiledChildren();
                }

                AggregationProfileShardResult aggsProfileResults =
                        profileShardResult.getAggregationProfileResults();
                for (ProfileResult profileResult : aggsProfileResults.getProfileResults()) {
                    String aggName = profileResult.getQueryName();
                    long aggTimeInMillis = profileResult.getTime();
                    List<ProfileResult> profiledChildren = profileResult.getProfiledChildren();
                }

            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }*/


}
