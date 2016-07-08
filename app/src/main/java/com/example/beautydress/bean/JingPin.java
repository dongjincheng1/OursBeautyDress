package com.example.beautydress.bean;

/**
 * Created by Djc on 2016/7/8- 19:34
 */
public class JingPin {
    private double selling_price;
    private String  title;
    private int sales_volume;
    private string pic_url;
    private string url;

    public JingPin() {
    }

    public string getPic_url() {
        return pic_url;
    }

    public JingPin(string pic_url, int sales_volume, double selling_price, String title, string url) {
        this.pic_url = pic_url;
        this.sales_volume = sales_volume;
        this.selling_price = selling_price;
        this.title = title;
        this.url = url;
    }

    public void setPic_url(string pic_url) {
        this.pic_url = pic_url;
    }

    public int getSales_volume() {
        return sales_volume;
    }

    public void setSales_volume(int sales_volume) {
        this.sales_volume = sales_volume;
    }

    public double getSelling_price() {
        return selling_price;
    }

    public void setSelling_price(double selling_price) {
        this.selling_price = selling_price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public string getUrl() {
        return url;
    }

    public void setUrl(string url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "JingPin{" +
                "pic_url=" + pic_url +
                ", selling_price=" + selling_price +
                ", title='" + title + '\'' +
                ", sales_volume=" + sales_volume +
                ", url=" + url +
                '}';
    }
}
