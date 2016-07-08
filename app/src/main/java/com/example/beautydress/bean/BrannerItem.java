package com.example.beautydress.bean;

/**
 * Created by JYX on 2016/7/8.
 */
public class BrannerItem {
    private String picUrl;
    private  String  detailUrl;

    public BrannerItem() {
    }

    public BrannerItem(String detailUrl, String picUrl) {
        this.detailUrl = detailUrl;
        this.picUrl = picUrl;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public String getDetailUrl() {
        return detailUrl;
    }

    public void setDetailUrl(String detailUrl) {
        this.detailUrl = detailUrl;
    }

    @Override
    public String toString() {
        return "BrannerItem{" +
                "picUrl='" + picUrl + '\'' +
                ", detailUrl='" + detailUrl + '\'' +
                '}';
    }
}
