package com.example.beautydress.utils;

import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.example.beautydress.bean.BrannerItem;

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
