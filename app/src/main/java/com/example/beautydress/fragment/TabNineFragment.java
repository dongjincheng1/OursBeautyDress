package com.example.beautydress.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.beautydress.R;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;

/**
 * Created by Djc on 2016/7/6.
 */
public class TabNineFragment extends Fragment {
    private HttpUtils utils;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        utils=new HttpUtils();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=(View) inflater.inflate(R.layout.tab_9kuai9_layout,null);
        return view;
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        String urlStr = "";
        utils.send(HttpRequest.HttpMethod.GET, urlStr, new RequestCallBack<String>() {

            @Override
            public void onFailure(HttpException arg0, String arg1) {


            }

            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                String jsonStr = responseInfo.result;
//                try {
//                    List<News> newsesPerTime = new LinkedList<>();
//                    ParseJsonUtil.parseNews(jsonStr,newsesPerTime);
//                    System.out.println("pageIndex = " + pageIndex
//                            + "～～～～＞　" + newsesPerTime);
//
//                    newses.addAll(newsesPerTime);
//                    myAdapter.notifyDataSetChanged();
//                } catch (JSONException e) {
//
//                    e.printStackTrace();
//                }

            }
        });

        super.onActivityCreated(savedInstanceState);
    }
}
