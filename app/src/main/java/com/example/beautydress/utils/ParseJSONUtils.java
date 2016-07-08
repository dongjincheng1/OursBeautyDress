package com.example.beautydress.utils;

import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.example.beautydress.bean.BrannerItem;
import com.example.beautydress.bean.JingPin;
import com.example.beautydress.bean.JingXuan;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Djc on 2016/7/7.
 */
public class ParseJSONUtils {

    private static final String TAG = "ParseJSONUtils";
    public static String getJingPing(String url){
        return null;
    }
    public static List<JingXuan> parseJingXuan(String jingXuanStr) {
        List<JingXuan> jingXuanList = null;
        try {
            jingXuanList=new ArrayList<JingXuan>();
            JSONObject jsonStr = new JSONObject(jingXuanStr);
            JSONObject data = jsonStr.getJSONObject("data");
            JSONArray products = data.getJSONArray("products");
            jingXuanList = JSON.parseArray(products.toString(), JingXuan.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jingXuanList;
    }
    public static List<JingPin> parseJingPin(String jingPinStr) {
        List<JingPin> jingPinList = null;
        try {
            jingPinList = new ArrayList<JingPin>();
            JSONObject jsonStr = new JSONObject(jingPinStr);
            JSONObject data = jsonStr.getJSONObject("data");
            JSONArray products = data.getJSONArray("products");
            jingPinList = JSON.parseArray(products.toString(), JingPin.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jingPinList;
    }


    public  static List<BrannerItem>  parseBranner(String jsonStr){
        List<BrannerItem> brannerItems = null;
        try {
            brannerItems = new ArrayList<>();
            JSONObject object = new JSONObject(jsonStr);
            JSONObject data = object.getJSONObject("data");
            JSONArray jsonArray = data.getJSONArray("items");
            brannerItems=JSON.parseArray(jsonArray.toString(),BrannerItem.class);
            Log.i("TAG", "parseBranner: "+brannerItems.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return  brannerItems;
    }
}
