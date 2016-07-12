package com.example.beautydress.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSONException;
import com.bumptech.glide.Glide;
import com.example.beautydress.Activity.ShowActivity;
import com.example.beautydress.Adapter.MyBaseAdapter;
import com.example.beautydress.Adapter.ViewHolder;
import com.example.beautydress.R;
import com.example.beautydress.bean.Classify;
import com.example.beautydress.bean.ShangPin;
import com.example.beautydress.common.Uris;
import com.example.beautydress.utils.ParseJSONUtils;
import com.example.beautydress.view.MyGirdView;
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
public class MenDressFragment extends Fragment {
    private View view;
    private GridView men_dress_classify_gv;
    private GridView  men_dress_gv;
    private HttpUtils hUtils;
//    private BitmapUtils bitmapUtils;
    private List<ShangPin>  men_dress_List;
    private List<Classify>  men_dress_classify_list;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.men_dress_layout,null);
        men_dress_classify_gv=(MyGirdView)view.findViewById(R.id.men_dress_classify_gv_id);
        men_dress_gv=(MyGirdView)view.findViewById(R.id.men_dress_gv_id);
        return view;
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        hUtils = new HttpUtils();
//        bitmapUtils = new BitmapUtils(getActivity());
        men_dress_classify_list=new ArrayList<Classify>();
        men_dress_List=new ArrayList<ShangPin>();
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        aboutMenDressGridView(Uris.MEN_LIST);
        aboutMenDressClassifyGridView( Uris.MEN_CATE);
        super.onActivityCreated(savedInstanceState);
    }
    private void aboutMenDressClassifyGridView(String classifyUrl){
        hUtils.send(HttpRequest.HttpMethod.GET, classifyUrl, new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                men_dress_classify_list= ParseJSONUtils.parseClassify(responseInfo.result);
                men_dress_classify_gv.setAdapter(new MyBaseAdapter<Classify>(men_dress_classify_list,getActivity(),R.layout.classify_simple_item_layout) {
                    @Override
                    public void setData(ViewHolder viewHolder, int position) {
                        ImageView wd_cl_iv= (ImageView) viewHolder.findViewById(R.id.cl_item_iv_id);
                        TextView wd_cl_tv=(TextView)viewHolder.findViewById(R.id.cl_item_tv_id);
//                        bitmapUtils.display(wd_cl_iv,men_dress_classify_list.get(position).getPic_url());
                        Glide.with(getActivity())
                                .load(men_dress_classify_list.get(position).getPic_url()).override(600,200)
                               .into(wd_cl_iv);
                        wd_cl_tv.setText(men_dress_classify_list.get(position).getTitle());
                    }
                });
            }
            @Override
            public void onFailure(HttpException error, String msg) {;
            }
        });
    }

    private void aboutMenDressGridView(String jingPinUrl) {
        hUtils.send(HttpRequest.HttpMethod.GET, jingPinUrl, new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                try {
                    men_dress_List = ParseJSONUtils.parseShangPin(responseInfo.result);
                    men_dress_gv.setAdapter(new MyBaseAdapter<ShangPin>(men_dress_List, getActivity(), R.layout.shangpin_simple_item_layout) {
                        @Override
                        public void setData(ViewHolder viewHolder, int position) {
                            TextView tv_title = (TextView) viewHolder.findViewById(R.id.jp_item_title_id);
                            TextView tv_selling_price = (TextView) viewHolder.findViewById(R.id.jp_item_selling_price_id);
                            TextView tv_sales_volume = (TextView) viewHolder.findViewById(R.id.jp_item_sales_volume_id);
                            ImageView iv_img = (ImageView) viewHolder.findViewById(R.id.jp_item_img_id);
                            tv_title.setText(men_dress_List.get(position).getTitle());
                            tv_selling_price.setText("¥" + men_dress_List.get(position).getSelling_price() + "");
                            tv_sales_volume.setText(men_dress_List.get(position).getSales_volume() + "");
//                            bitmapUtils.display(iv_img, men_dress_List.get(position).getPic_url());
                            Glide.with(getActivity())
                                    .load(men_dress_List.get(position).getPic_url())
                                    .override(600,200)
                                    .into(iv_img);
                        }
                    });
                    men_dress_gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                            //点击事件
                            Intent intent = new Intent(getActivity(), ShowActivity.class);
                            intent.putExtra("detailUrl",men_dress_List.get(i).getUrl().toString());
                            startActivity(intent);
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
