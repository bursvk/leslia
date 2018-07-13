package com.leslia.inter.pojo;

public class HelpCategory implements java.io.Serializable {


    private int helpCategoryId;

    private String name;

    private int parentCategoryId;

    private String url;


    public int getHelpCategoryId() {
        return helpCategoryId;
    }

    public void setHelpCategoryId(int helpCategoryId) {
        this.helpCategoryId = helpCategoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getParentCategoryId() {
        return parentCategoryId;
    }

    public void setParentCategoryId(int parentCategoryId) {
        this.parentCategoryId = parentCategoryId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }


}
