package com.leslia.search.solr.pojo;

import org.apache.solr.client.solrj.beans.Field;

import java.util.Date;

public class Bless {

    @Field
    private String id;

    @Field
    private String blessContent;

    @Field
    private Date blessTime;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBlessContent() {
        return blessContent;
    }

    public void setBlessContent(String blessContent) {
        this.blessContent = blessContent;
    }

    public Date getBlessTime() {
        return blessTime;
    }

    public void setBlessTime(Date blessTime) {
        this.blessTime = blessTime;
    }


}
