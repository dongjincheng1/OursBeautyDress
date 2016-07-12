package com.example.beautydress.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSONException;
import com.example.beautydress.Adapter.MyBaseAdapter;
import com.example.beautydress.Adapter.ViewHolder;
import com.example.beautydress.R;
import com.example.beautydress.bean.Classify;
import com.example.beautydress.bean.ShangPin;
import com.example.beautydress.common.Uris;
import com.example.beautydress.utils.MyBitmapUtils;
import com.example.beautydress.utils.ParseJSONUtils;
import com.example.beautydress.view.MyGirdView;
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
public class BeautyFragment extends Fragment {
    private View view;
    private MyGirdView makeup_classify_gv;
    private MyGirdView  makeup_gv;
    private HttpUtils hUtils;
    private BitmapUtils bitmapUtils;
    private List<ShangPin>  makeup_List;
    private List<Classify>   makeup_classify_list;
    private  MyBitmapUtils myBitmapUtils;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.beauty_layout,null);
        makeup_classify_gv=(MyGirdView)view.findViewById(R.id.makeup_classify_gv_id);
        makeup_gv=(MyGirdView)view.findViewById(R.id.makeup_gv_id);

        return view;
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        hUtils = new HttpUtils();
        bitmapUtils = new BitmapUtils(getActivity());
        makeup_classify_list=new ArrayList<Classify>();
        makeup_List=new ArrayList<ShangPin>();
        myBitmapUtils=new MyBitmapUtils();
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        aboutMakeUpGridView(Uris.MAKEUP_LIST);
        aboutMakeUpClassifyGridView( Uris.MAKEUP_CATE);
        super.onActivityCreated(savedInstanceState);
    }

    private void aboutMakeUpClassifyGridView(String classifyUrl){
        hUtils.send(HttpRequest.HttpMethod.GET, classifyUrl, new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                makeup_classify_list= ParseJSONUtils.parseClassify(responseInfo.result);
                makeup_classify_gv.setAdapter(new MyBaseAdapter<Classify>(makeup_classify_list,getActivity(),R.layout.classify_simple_item_layout) {
                    @Override
                    public void setData(ViewHolder viewHolder, int position) {
                        ImageView wd_cl_iv= (ImageView) viewHolder.findViewById(R.id.cl_item_iv_id);
                        TextView wd_cl_tv=(TextView)viewHolder.findViewById(R.id.cl_item_tv_id);
                        wd_cl_tv.setText(makeup_classify_list.get(position).getTitle());
                        myBitmapUtils.display(wd_cl_iv,makeup_classify_list.get(position).getPic_url());
                    }
                });
            }
            @Override
            public void onFailure(HttpException error, String msg) {;

            }
        });
    }

    private void aboutMakeUpGridView(String jingPinUrl) {
        hUtils.send(HttpRequest.HttpMethod.GET, jingPinUrl, new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                try {
                    makeup_List = ParseJSONUtils.parseShangPin(responseInfo.result);
                    makeup_gv.setAdapter(new MyBaseAdapter<ShangPin>(makeup_List, getActivity(), R.layout.shangpin_simple_item_layout) {
                        @Override
                        public void setData(ViewHolder viewHolder, int position) {
                            TextView tv_title = (TextView) viewHolder.findViewById(R.id.jp_item_title_id);
                            TextView tv_selling_price = (TextView) viewHolder.findViewById(R.id.jp_item_selling_price_id);
                            TextView tv_sales_volume = (TextView) viewHolder.findViewById(R.id.jp_item_sales_volume_id);
                            ImageView iv_img = (ImageView) viewHolder.findViewById(R.id.jp_item_img_id);
                            tv_title.setText(makeup_List.get(position).getTitle());
                            tv_selling_price.setText("¥" + makeup_List.get(position).getSelling_price() + "");
                            tv_sales_volume.setText(makeup_List.get(position).getSales_volume() + "");
                           myBitmapUtils.display(iv_img, makeup_List.get(position).getPic_url());
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
