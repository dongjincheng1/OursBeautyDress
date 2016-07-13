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
            .append("gmall/api/1.0/allbuy/list.php?mid=457465&type=dress&category_ids=4796%2C4797%2C4805%2C4838%2C4839%2C4840%2C4841%2C4843&sort=default&yk_pid=3&yk_appid=1&yk_cc=baidu&yk_cvc=311&cursor=0&count=40");


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
    public static final StringBuffer JINGXUAN_URI = new StringBuffer("http://vapi.yuike.com/")
            .append("1.0/product/quality.php?mid=457465&type=choice&sid=c171660f23cb1eea5ebb8857003b5846&yk_pid=3&yk_appid=1&yk_cc=baidu&yk_cvc=311&cursor=0&count=40");


    //我
    public static final StringBuffer MINE_URI = new StringBuffer("http://vapi.yuike.com/")
            .append("1.0/user/detail_ex.php?sid=c171660f23cb1eea5ebb8857003b5846&yk_pid=3&yk_appid=1&yk_cc=baidu&yk_cvc=311&mid=457465");


    //城市(location)
    public static final StringBuffer CITY_URI = new StringBuffer("http://vapi.yuike.com/")
            .append("1.0/address/location_list.php?sid=c171660f23cb1eea5ebb8857003b5846&yk_pid=3&yk_appid=1&yk_cc=baidu&yk_cvc=311&mid=457465");
    //男装分类
    public static final String MEN_CATE="http://api.yuike.com/gmall/api/1.0/allbuy/category_list.php?mid=457465&type=men&yk_pid=3&yk_appid=1&yk_cc=baidu&yk_cvc=311&cursor=0&count=40";
    //男装列表
    public static final String MEN_LIST="http://api.yuike.com/gmall/api/1.0/allbuy/list.php?mid=457465&type=men&category_ids=0&sort=default&yk_pid=3&yk_appid=1&yk_cc=baidu&yk_cvc=311&cursor=0&count=40";
    //中老年分类
    public static final String OLDER_CATE="http://api.yuike.com/gmall/api/1.0/allbuy/category_list.php?mid=457465&type=agedness&yk_pid=3&yk_appid=1&yk_cc=baidu&yk_cvc=311&cursor=0&count=40";
    //中老年列表
    public static final String OLDER_LIST="http://api.yuike.com/gmall/api/1.0/allbuy/list.php?mid=457465&type=agedness&category_ids=0&sort=default&yk_pid=3&yk_appid=1&yk_cc=baidu&yk_cvc=311&cursor=0&count=40";

    //童装分类
    public static final String CHILDREN_CATE="http://api.yuike.com/gmall/api/1.0/allbuy/category_list.php?mid=457465&type=children&yk_pid=3&yk_appid=1&yk_cc=baidu&yk_cvc=311&cursor=0&count=40";
    //童装列表
    public static final String CHILDREN_LIST="http://api.yuike.com/gmall/api/1.0/allbuy/list.php?mid=457465&type=children&category_ids=0&sort=default&yk_pid=3&yk_appid=1&yk_cc=baidu&yk_cvc=311&cursor=0&count=40";
    //美妆分类
    public static final String MAKEUP_CATE="http://api.yuike.com/gmall/api/1.0/allbuy/category_list.php?mid=457465&type=makeup&yk_pid=3&yk_appid=1&yk_cc=baidu&yk_cvc=311&cursor=0&count=40";
    //美妆列表
    public static final String MAKEUP_LIST="http://api.yuike.com/gmall/api/1.0/allbuy/list.php?mid=457465&type=makeup&category_ids=0&sort=default&yk_pid=3&yk_appid=1&yk_cc=baidu&yk_cvc=311&cursor=0&count=40";
    //家纺分类
    public static final String TEXTILES_CATE="http://api.yuike.com/gmall/api/1.0/allbuy/category_list.php?mid=457465&type=textiles&yk_pid=3&yk_appid=1&yk_cc=baidu&yk_cvc=311&cursor=0&count=40";
    //家纺列表
    public static final String TEXTILES_LIST="http://api.yuike.com/gmall/api/1.0/allbuy/list.php?mid=457465&type=textiles&category_ids=0&sort=default&yk_pid=3&yk_appid=1&yk_cc=baidu&yk_cvc=311&cursor=0&count=40";

    public static final String MODLE_LIST_1="http://gouwu.cdn.yuike.com/data/img/457465/home_model/f58c9c05ae1e955274ed603344d24d35.jpg";
    public static final String MODLE_LIST_2="http://gouwu.cdn.yuike.com/data/img/457465/home_model/3866f680e23530b7242df71e128b3c59.jpg";
    public static final String MODLE_LIST_3="http://gouwu.cdn.yuike.com/data/img/457465/home_model/b94ee961e99e951c228e1cd01116710f.jpg";
    public static final String MODLE_LIST_4="http://gouwu.cdn.yuike.com/data/img/457465/home_model/0e77b8895f5fcc83273a36b03e876f6d.jpg";
    public static final String MODLE_LIST_5="http://gouwu.cdn.yuike.com/data/img/457465/home_model/3a47e2819a7f6de457f15f9af84611a2.jpg";
    public static final String MODLE_LIST_6="http://gouwu.cdn.yuike.com/data/img/457465/home_model/790afa8bf218370177e1126482919f37.jpg";
    public static final String MODLE_LIST_7="http://gouwu.cdn.yuike.com/data/img/457465/home_model/ff7f9df2a99b72d343569fd97d289d08.jpg";
}
