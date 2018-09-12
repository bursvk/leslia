package com.leslia.api.pojo;

import java.io.Serializable;

public class OrderCopy implements Serializable {

    private String BillNumber;

    private String BuildDate;

    private String Customer;

    private String GoodsName;

    private Double Amount;

    private Integer SaleMoney;


    public String getBillNumber() {
        return BillNumber;
    }

    public void setBillNumber(String billNumber) {
        BillNumber = billNumber;
    }

    public String getBuildDate() {
        return BuildDate;
    }

    public void setBuildDate(String buildDate) {
        BuildDate = buildDate;
    }

    public String getCustomer() {
        return Customer;
    }

    public void setCustomer(String customer) {
        Customer = customer;
    }

    public String getGoodsName() {
        return GoodsName;
    }

    public void setGoodsName(String goodsName) {
        GoodsName = goodsName;
    }

    public Double getAmount() {
        return Amount;
    }

    public void setAmount(Double amount) {
        Amount = amount;
    }

    public Integer getSaleMoney() {
        return SaleMoney;
    }

    public void setSaleMoney(Integer saleMoney) {
        SaleMoney = saleMoney;
    }
}