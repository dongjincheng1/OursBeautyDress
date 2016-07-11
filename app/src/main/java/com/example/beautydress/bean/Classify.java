package com.example.beautydress.bean;

/**
 * Created by Djc on 2016/7/9- 20:39
 */
public class Classify {
    private String  title;
    private String pic_url;

    public Classify() {
    }

    public Classify(String pic_url, String title) {
        this.pic_url = pic_url;
        this.title = title;
    }

    public String getPic_url() {
        return pic_url;
    }

    public void setPic_url(String pic_url) {
        this.pic_url = pic_url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "Classify{" +
                "pic_url='" + pic_url + '\'' +
                ", title='" + title + '\'' +
                '}';
    }
}
