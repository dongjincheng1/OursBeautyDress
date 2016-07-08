package com.example.beautydress.common;

/**
 * Created by Administrator on 2016/7/7.
 */
public class Uris {

    // 基本URI
    public static final String BASE_URI = "http://api.yuike.com/";

    //首页标签名
    public static final StringBuffer HOME_TAGNAME_URI = new StringBuffer(BASE_URI)
            .append("gmall/api/1.0/home/floor_list.php?mid=457465&yk_pid=3&yk_appid=1&yk_cc=baidu&yk_cvc=311");

    //首页的branner
    public static final StringBuffer HOME_BRANNER_URI = new StringBuffer(BASE_URI)
            .append("gmall/api/1.0/home/banner_list.php?mid=457465&yk_pid=3&yk_appid=1&yk_cc=baidu&yk_cvc=311&cursor=0&count=40");


    //首页的中间部分(3)modil_list
    public static final StringBuffer HOME_MODIL_URI = new StringBuffer(BASE_URI)
            .append("gmall/api/1.0/home/model_list.php?mid=457465&yk_pid=3&yk_appid=1&yk_cc=baidu&yk_cvc=311&cursor=0&count=40");


    //首页的的精品推荐(所有商品)
    public static final StringBuffer HOME_RECOMMEND_URI = new StringBuffer(BASE_URI)
            .append("gmall/api/1.0/allbuy/list.php?mid=457465&type=dress&category_ids=4796%2C4797%2C4805%2C4838%2C4839%2C4840%2C4841%2C4843&sort=default&yk_pid=3&yk_appid=1&yk_cc=baidu&yk_cvc=311&cursor=0&count=40\n");


    //首页的的女装分类
    public static final StringBuffer HOME_GIRL_CATE_URI = new StringBuffer(BASE_URI)
            .append("gmall/api/1.0/allbuy/category_list.php?mid=457465&type=dress&yk_pid=3&yk_appid=1&yk_cc=baidu&yk_cvc=311&cursor=0&count=40");

    //首页的的女装商品
    public static final StringBuffer HOME_GIRL_COMMOTY_URI = new StringBuffer(BASE_URI)
            .append("gmall/api/1.0/allbuy/list.php?mid=457465&type=dress&category_ids=4796%2C4797%2C4805%2C4838%2C4839%2C4840%2C4841%2C4843&sort=default&yk_pid=3&yk_appid=1&yk_cc=baidu&yk_cvc=311&cursor=0&count=40");

    //首页的的鞋包分类
    public static final StringBuffer HOME_SHOSE__URI = new StringBuffer(BASE_URI)
            .append("gmall/api/1.0/allbuy/category_list.php?mid=457465&type=shoes&yk_pid=3&yk_appid=1&yk_cc=baidu&yk_cvc=311&cursor=0&count=40");

    //首页的的鞋包列表
    public static final StringBuffer HOME_SHOSE_URI = new StringBuffer(BASE_URI)
            .append("gmall/api/1.0/allbuy/list.php?mid=457465&type=shoes&category_ids=0&sort=default&yk_pid=3&yk_appid=1&yk_cc=baidu&yk_cvc=311&cursor=0&count=40");


    //精选
    public static final StringBuffer JINGXUAN_URI = new StringBuffer(BASE_URI)
            .append("1.0/product/quality.php?mid=457465&type=choice&sid=c171660f23cb1eea5ebb8857003b5846&yk_pid=3&yk_appid=1&yk_cc=baidu&yk_cvc=311&cursor=0&count=40");


    //我
    public static final StringBuffer MINE_URI = new StringBuffer(BASE_URI)
            .append("1.0/user/detail_ex.php?sid=c171660f23cb1eea5ebb8857003b5846&yk_pid=3&yk_appid=1&yk_cc=baidu&yk_cvc=311&mid=457465");


    //城市(location)
    public static final StringBuffer CITY_URI = new StringBuffer(BASE_URI)
            .append("1.0/address/location_list.php?sid=c171660f23cb1eea5ebb8857003b5846&yk_pid=3&yk_appid=1&yk_cc=baidu&yk_cvc=311&mid=457465");

}
