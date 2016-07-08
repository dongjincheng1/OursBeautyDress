package com.example.beautydress.bean;

/**
 * Created by Djc on 2016/7/8- 10:21
 */
public class JingXuan {
    private String taobao_title;    //标题
    private double taobao_price;    //原价
    private double taobao_selling_price;    //卖价
    private String discount;    //折扣
    private String taobao_pic_url;  //图片地址
    private String taobao_url;  //淘宝地址

    public JingXuan() {
    }

    public JingXuan(String discount, String taobao_pic_url, double taobao_price, double taobao_selling_price, String taobao_title, String taobao_url) {
        this.discount = discount;
        this.taobao_pic_url = taobao_pic_url;
        this.taobao_price = taobao_price;
        this.taobao_selling_price = taobao_selling_price;
        this.taobao_title = taobao_title;
        this.taobao_url = taobao_url;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getTaobao_pic_url() {
        return taobao_pic_url;
    }

    public void setTaobao_pic_url(String taobao_pic_url) {
        this.taobao_pic_url = taobao_pic_url;
    }

    public double getTaobao_price() {
        return taobao_price;
    }

    public void setTaobao_price(double taobao_price) {
        this.taobao_price = taobao_price;
    }

    public double getTaobao_selling_price() {
        return taobao_selling_price;
    }

    public void setTaobao_selling_price(double taobao_selling_price) {
        this.taobao_selling_price = taobao_selling_price;
    }

    public String getTaobao_title() {
        return taobao_title;
    }

    public void setTaobao_title(String taobao_title) {
        this.taobao_title = taobao_title;
    }

    public String getTaobao_url() {
        return taobao_url;
    }

    public void setTaobao_url(String taobao_url) {
        this.taobao_url = taobao_url;
    }

    @Override
    public String toString() {
        return "JingXuan{" +
                "discount='" + discount + '\'' +
                ", taobao_title='" + taobao_title + '\'' +
                ", taobao_price=" + taobao_price +
                ", taobao_selling_price=" + taobao_selling_price +
                ", taobao_pic_url='" + taobao_pic_url + '\'' +
                ", taobao_url='" + taobao_url + '\'' +
                '}';
    }
}
