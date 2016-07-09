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
import com.example.beautydress.bean.Classify;
import com.example.beautydress.bean.ShangPin;
import com.example.beautydress.common.Uris;
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
public class ShoesBagFragment extends Fragment {
    private View view;
    private GridView shoes_bag_classify_gv;
    private GridView shoes_bag_gv;
    private HttpUtils hUtils;
    private BitmapUtils bitmapUtils;
    private List<ShangPin> shoes_bag_List;
    private List<Classify> shoes_bag_classify_list;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.shoes_bag_layout,null);
        shoes_bag_classify_gv=(GridView)view.findViewById(R.id.shoes_bag_classify_gv_id);
        shoes_bag_gv=(GridView)view.findViewById(R.id.shoes_bag_gv_id);
        return view;
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        hUtils = new HttpUtils();
        bitmapUtils = new BitmapUtils(getActivity());
        shoes_bag_classify_list=new ArrayList<Classify>();
        shoes_bag_List=new ArrayList<ShangPin>();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        aboutShoesBagGridView(Uris.HOME_SHOSE_URI.toString());
        aaboutShoesBagClassifyGridView( Uris.HOME_SHOSE__URI.toString());
        super.onActivityCreated(savedInstanceState);

    }
    private void aaboutShoesBagClassifyGridView(String classifyUrl){
        hUtils.send(HttpRequest.HttpMethod.GET, classifyUrl, new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                shoes_bag_classify_list= ParseJSONUtils.parseClassify(responseInfo.result);
                shoes_bag_classify_gv.setAdapter(new MyBaseAdapter<Classify>(shoes_bag_classify_list,getActivity(),R.layout.classify_simple_item_layout) {
                    @Override
                    public void setData(ViewHolder viewHolder, int position) {
                        ImageView wd_cl_iv= (ImageView) viewHolder.findViewById(R.id.cl_item_iv_id);
                        TextView wd_cl_tv=(TextView)viewHolder.findViewById(R.id.cl_item_tv_id);
                        bitmapUtils.display(wd_cl_iv,shoes_bag_classify_list.get(position).getPic_url());
                        wd_cl_tv.setText(shoes_bag_classify_list.get(position).getTitle());
                    }
                });
            }
            @Override
            public void onFailure(HttpException error, String msg) {;
            }
        });
    }

    private void aboutShoesBagGridView(String jingPinUrl) {
        hUtils.send(HttpRequest.HttpMethod.GET, jingPinUrl, new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                try {
                    shoes_bag_List = ParseJSONUtils.parseShangPin(responseInfo.result);
                    shoes_bag_gv.setAdapter(new MyBaseAdapter<ShangPin>(shoes_bag_List, getActivity(), R.layout.shangpin_simple_item_layout) {
                        @Override
                        public void setData(ViewHolder viewHolder, int position) {
                            TextView tv_title = (TextView) viewHolder.findViewById(R.id.jp_item_title_id);
                            TextView tv_selling_price = (TextView) viewHolder.findViewById(R.id.jp_item_selling_price_id);
                            TextView tv_sales_volume = (TextView) viewHolder.findViewById(R.id.jp_item_sales_volume_id);
                            ImageView iv_img = (ImageView) viewHolder.findViewById(R.id.jp_item_img_id);
                            tv_title.setText(shoes_bag_List.get(position).getTitle());
                            tv_selling_price.setText("¥" + shoes_bag_List.get(position).getSelling_price() + "");
                            tv_sales_volume.setText(shoes_bag_List.get(position).getSales_volume() + "");
                            bitmapUtils.display(iv_img, shoes_bag_List.get(position).getPic_url());
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
