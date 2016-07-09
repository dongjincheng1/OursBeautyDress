package com.example.beautydress.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSONException;
import com.example.beautydress.Adapter.MyBaseAdapter;
import com.example.beautydress.Adapter.ViewHolder;
import com.example.beautydress.R;
import com.example.beautydress.bean.JingPin;
import com.example.beautydress.common.Uris;
import com.example.beautydress.utils.ParseJSONUtils;
import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;

import java.util.List;

/**
 * Created by Djc on 2016/7/6.
 */
public class WomenDressFragment extends Fragment {
    private View view;
    private GridView women_dress_gv1;
    private GridView women_dress_gv2;
    private HttpUtils hUtils;
    private BitmapUtils bitmapUtils;
    private String jingPingUrl;
    private List<JingPin> jingPinList;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.women_dress_layout,null);
//        women_dress_gv1=(GridView)view.findViewById(R.id.wd_gv1_id);
        women_dress_gv2=(GridView)view.findViewById(R.id.wd_gv2_id);
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        hUtils = new HttpUtils();
        bitmapUtils = new BitmapUtils(getActivity());
        jingPingUrl = Uris.HOME_RECOMMEND_URI.toString();

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        aboutWomenDressGridView(jingPingUrl);
        super.onActivityCreated(savedInstanceState);

    }
    private void aboutWomenDressGridView(String jingPinUrl) {
        hUtils.send(HttpRequest.HttpMethod.GET, jingPinUrl, new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                try {
                    jingPinList = ParseJSONUtils.parseJingPin(responseInfo.result);
                    women_dress_gv2.setAdapter(new MyBaseAdapter<JingPin>(jingPinList, getActivity(), R.layout.jingpin_simple_item_layout) {
                        @Override
                        public void setData(ViewHolder viewHolder, int position) {
                            TextView tv_title = (TextView) viewHolder.findViewById(R.id.jp_item_title_id);
                            TextView tv_selling_price = (TextView) viewHolder.findViewById(R.id.jp_item_selling_price_id);
                            TextView tv_sales_volume = (TextView) viewHolder.findViewById(R.id.jp_item_sales_volume_id);
                            ImageView iv_img = (ImageView) viewHolder.findViewById(R.id.jp_item_img_id);
                            tv_title.setText(jingPinList.get(position).getTitle());
                            tv_selling_price.setText("¥" + jingPinList.get(position).getSelling_price() + "");
                            tv_sales_volume.setText(jingPinList.get(position).getSales_volume() + "");
                            bitmapUtils.display(iv_img, jingPinList.get(position).getPic_url());
                        }
                    });

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(HttpException error, String msg) {
            }
        });
    }
}
