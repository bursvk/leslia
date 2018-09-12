package com.leslia.search.solr;

import com.leslia.search.solr.pojo.Bless;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.client.solrj.response.UpdateResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.apache.solr.common.params.MapSolrParams;
import org.junit.Test;

import java.util.*;

public class SolrTest {

    private static final String solrUrl="http://localhost:8983/solr/bless";

    //创建solrClient同时指定超时时间，不指定走默认配置
    private static HttpSolrClient client=new HttpSolrClient.Builder(solrUrl)
            .withConnectionTimeout(10000)
                .withSocketTimeout(60000).build();

    @Test
    public void querySolr2() throws Exception{
        //[2]封装查询参数
        Map<String, String> queryParamMap = new HashMap<>();
        queryParamMap.put("q", "*:*");
        //[3]添加到SolrParams对象
        MapSolrParams queryParams = new MapSolrParams(queryParamMap);
        //[4]执行查询返回QueryResponse
        QueryResponse response = client.query(queryParams);
        //[5]获取doc文档
        SolrDocumentList documents = response.getResults();
        //[6]内容遍历
        for(SolrDocument solrDocument:documents){
            System.out.println(solrDocument.get("id"));
            System.out.println(solrDocument.get("blessContent"));
            System.out.println(solrDocument.get("blessTime"));
        }
        client.close();
    }

    @Test
    public void querySolr() throws Exception{
        //封装查询参数
        SolrQuery query = new SolrQuery("*:*");
        //添加需要回显得内容
        query.addField("id");
        query.addField("blessContent");
        query.addField("blessTime");
        query.setRows(20);//设置每页显示多少条
        //执行查询返回QueryResponse
        QueryResponse response = client.query(query);
        //获取doc文档
        SolrDocumentList documents = response.getResults();
        for(SolrDocument solrDocument:documents){
            System.out.println(solrDocument.get("id"));
            System.out.println(solrDocument.get("blessContent"));
            System.out.println(solrDocument.get("blessTime"));
        }
        //关闭资源
        client.close();
    }

    @Test
    public void solrAdd() throws Exception{
        //创建文档doc
        SolrInputDocument doc = new SolrInputDocument();
        doc.addField("id", 21);
        doc.addField("blessContent", "solrj add");
        doc.addField("blessTime",new Date());
        //添加到client
        UpdateResponse updateResponse = client.add(doc);
        //索引文档必须commit
        client.commit();
    }

    /**
     * 4、单个id 的删除索引
     */
    @Test
    public void solrDelete() throws Exception{
        //1 通过id 删除
        client.deleteById("1");
        //2 通过id List 删除
        ArrayList<String> ids = new ArrayList<String>();
        ids.add("2");
        ids.add("3");
        client.deleteById(ids);
        //3 通过 查询信息删除
        client.deleteByQuery("id:4");
        // 提交
        client.commit();
        // 关闭资源
        client.close();
    }

    /**
     * 5、多个id 的list集合 删除索引
     */
    @Test
    public void solrDeleteList() throws Exception{
        //[1]获取连接
        //[2]通过id删除

        //[3]提交
        client.commit();
        //[4]关闭资源
        client.close();
    }

    @Test
    public void deleteByQuery() throws Exception{
        client.deleteByQuery("id:19");
        //[3]提交操作
        client.commit();
        //[4]关闭资源
        client.close();
    }

    @Test
    public void addBean() throws Exception{
        Bless bless=new Bless();
        bless.setId("19");
        bless.setBlessContent("solr java bean add");
        bless.setBlessTime(new Date());
        //添加对象
        UpdateResponse response = client.addBean(bless);
        //提交操作
        client.commit();
        //关闭资源
        client.close();
    }


    @Test
    public void queryBean() throws Exception{
        SolrQuery query = new SolrQuery("*:*");
        //[3]添加需要回显得内容
        query.addField("id");
        query.addField("blessContent");
        query.addField("blessTime");
        query.setRows(20);//设置每页显示多少条
        //[4]执行查询返回QueryResponse
        QueryResponse response = client.query(query);
        //[5]获取doc文档
        List<Bless> blessList=response.getBeans(Bless.class);
        for(Bless bless:blessList){
            System.out.println(bless.getId());
            System.out.println(bless.getBlessContent());
            System.out.println(bless.getBlessTime());
        }
        client.close();
    }


}
