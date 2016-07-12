package com.example.beautydress.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.beautydress.Activity.ShowActivity;
import com.example.beautydress.Adapter.MyBaseAdapter;
import com.example.beautydress.Adapter.ViewHolder;
import com.example.beautydress.R;
import com.example.beautydress.bean.JingXuan;
import com.example.beautydress.common.Uris;
import com.example.beautydress.utils.MyBitmapUtils;
import com.example.beautydress.utils.ParseJSONUtils;
import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Djc on 2016/7/6.
 */
public class TabNineFragment extends Fragment {
    private HttpUtils utils;
    private String jingPinUrlStr;
    private List<JingXuan> listJingXuan;
    private GridView gv;
    private BitmapUtils bitmapUtils;
    private MyBitmapUtils myBitmapUtils;
    private static final String TAG = "---TabNineFragment----";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        utils = new HttpUtils();
        bitmapUtils = new BitmapUtils(getActivity());
        listJingXuan = new ArrayList<JingXuan>();
        myBitmapUtils=new MyBitmapUtils();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = (View) inflater.inflate(R.layout.tab_9kuai9_layout, null);
        gv = (GridView) view.findViewById(R.id.jx_gv_id);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {

        jingPinUrlStr = Uris.JINGXUAN_URI.toString();
        utils.send(HttpRequest.HttpMethod.GET, jingPinUrlStr, new RequestCallBack<String>() {
            @Override
            public void onFailure(HttpException arg0, String arg1) {
                Toast.makeText(getActivity(), "加载失败", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                String jsonStr = responseInfo.result;
                try {
                    listJingXuan = ParseJSONUtils.parseJingXuan(jsonStr);
                    gv.setAdapter(new MyBaseAdapter<JingXuan>(listJingXuan, getActivity(), R.layout.jingxuan_simple_item_layout) {
                        @Override
                        public void setData(ViewHolder viewHolder, int position) {
                            TextView tv_price = (TextView) viewHolder.findViewById(R.id.jx_item_price_id);
                            TextView tv_selling_price = (TextView) viewHolder.findViewById(R.id.jx_item_selling_price_id);
                            TextView tv_discount = (TextView) viewHolder.findViewById(R.id.jx_item_discount_id);
                            TextView tv_title = (TextView) viewHolder.findViewById(R.id.jx_item_title_id);
                            ImageView iv_img = (ImageView) viewHolder.findViewById(R.id.jx_item_img_id);

                            tv_price.setText( "¥"+listJingXuan.get(position).getTaobao_price()+"");
                            tv_selling_price.setText("¥"+listJingXuan.get(position).getTaobao_selling_price()+"");
                            tv_discount.setText(listJingXuan.get(position).getDiscount()+"");
                            tv_title.setText(listJingXuan.get(position).getTaobao_title()+"");
                            myBitmapUtils.display(iv_img,listJingXuan.get(position).getTaobao_pic_url());
                        }
                    });
                    gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                            Toast.makeText(getActivity(),listJingXuan.get(i).getTaobao_title(),Toast.LENGTH_LONG).show();
                            //点击事件
                            Intent intent = new Intent(getActivity(), ShowActivity.class);
                            intent.putExtra("detailUrl",listJingXuan.get(i).getTaobao_url());
                            Log.i(TAG, "onItemClick: "+listJingXuan.get(i).getTaobao_url());
                            startActivity(intent);
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        super.onActivityCreated(savedInstanceState);
    }
}
