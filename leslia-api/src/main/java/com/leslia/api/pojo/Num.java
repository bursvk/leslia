package com.leslia.api.pojo;

import java.util.Date;

public class Num implements java.io.Serializable {

    private int id;

    private String code;

    private int num;

    private Date date;



    public void setId(int id) {
        this.id = id;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

    public int getNum() {
        return num;
    }

    public Date getDate() {
        return date;
    }
}
