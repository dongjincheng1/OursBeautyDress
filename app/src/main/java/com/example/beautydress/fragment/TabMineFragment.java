package com.example.beautydress.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.SimpleAdapter;

import com.example.beautydress.Activity.LoginActivity;
import com.example.beautydress.Activity.Mine_Setting_Activity;
import com.example.beautydress.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Djc on 2016/7/6.
 */
public class TabMineFragment extends Fragment {
    private ListView me_lv_01, me_lv_02;
    private List<Map<String, Object>> data01 = new ArrayList<Map<String, Object>>();
    private List<Map<String, Object>> data02 = new ArrayList<Map<String, Object>>();
    private ScrollView sl;


    private Button me_btn_login;
    private FragmentTransaction transaction;
    private RelativeLayout mine_setting,me_rl_01,me_rl_02,me_rl_03,me_rl_04,me_rl_05;
    private ImageView image_top;



    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        transaction = getFragmentManager().beginTransaction();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = (View) inflater.inflate(R.layout.tab_mine_layout, null);
        me_lv_01 = (ListView) view.findViewById(R.id.me_lv_01);
        me_lv_02 = (ListView) view.findViewById(R.id.me_lv_02);
        me_rl_01 = (RelativeLayout) view.findViewById(R.id.me_rl_01);
        me_rl_02 = (RelativeLayout) view.findViewById(R.id.me_rl_02);
        me_rl_03 = (RelativeLayout) view.findViewById(R.id.me_rl_03);
        me_rl_04 = (RelativeLayout) view.findViewById(R.id.me_rl_04);
        me_rl_05 = (RelativeLayout) view.findViewById(R.id.me_rl_05);
        me_btn_login = (Button) view.findViewById(R.id.me_btn_login);
        mine_setting= (RelativeLayout) view.findViewById(R.id.mine_setting);
        image_top = (ImageView) view.findViewById(R.id.image_top);
      sl = (ScrollView) view.findViewById(R.id.fullScroll);
        aboutListView_01();
        aboutListView_02();


        aboutOnClick();



        return view;
    }


    private void aboutOnClick() {
        me_btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(getActivity(),LoginActivity.class);
                startActivity(intent);
            }
        });
        mine_setting.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(getActivity(),Mine_Setting_Activity.class);
                startActivity(intent);
            }
        });
        me_rl_01.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(getActivity(),LoginActivity.class);
                startActivity(intent);
            }
        });
        me_rl_02.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(getActivity(),LoginActivity.class);
                startActivity(intent);
            }
        });
        me_rl_03.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(getActivity(),LoginActivity.class);
                startActivity(intent);
            }
        });
        me_rl_04.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(getActivity(),LoginActivity.class);
                startActivity(intent);
            }
        });
        me_rl_05.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(getActivity(),LoginActivity.class);
                startActivity(intent);
            }
        });

        image_top.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
               sl.fullScroll(View.FOCUS_UP);
            }
        });
    }

    private void aboutListView_01() {
        int[] image = new int[]{R.mipmap.yuike_settings_indicator_un, R.mipmap.yuike_settings_indicator_un,
                R.mipmap.yuike_settings_indicator_un, R.mipmap.yuike_settings_indicator_un,
                R.mipmap.yuike_settings_indicator_un, R.mipmap.yuike_settings_indicator_un, R.mipmap.yuike_settings_indicator_un};
        int[] imgsrc = new int[]{R.mipmap.kimagel_addr_list, R.mipmap.kimagel_coupon,R.mipmap.kimagel_wallet, R.mipmap.kimagel_ykcoin, R.mipmap.kimagel_signcenter,
                 R.mipmap.kimagel_x_product ,R.mipmap.kimagel_x_brand};
        String itemName[] = getResources().getStringArray(R.array.itemName);

        for (int i = 0; i < imgsrc.length; i++) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("img", imgsrc[i]);
            map.put("text", itemName[i]);
            map.put("image", image[i]);

            data01.add(map);
        }

        SimpleAdapter adapter = new SimpleAdapter(getContext(), data01, R.layout.me_item_layout,
                new String[]{"img", "text", "image"}, new int[]{R.id.me_lv_01_iv_01, R.id.me_lv_01_tv, R.id.me_lv_01_iv_02});
        me_lv_01.setAdapter(adapter);
        me_lv_01.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent =new Intent(getActivity(),LoginActivity.class);
                startActivity(intent);
            }
        });
    }

    private void aboutListView_02() {
        int[] image02 = new int[]{R.mipmap.yuike_settings_indicator_un, R.mipmap.yuike_settings_indicator_un, R.mipmap.yuike_settings_indicator_un};
        int[] imgsrc02 = new int[]{R.mipmap.kimagel_user_invate, R.mipmap.kimagel_weixin_service, R.mipmap.kimagel_dafen};
        String itemName02[] = getResources().getStringArray(R.array.itemName_02);

        for (int i = 0; i < imgsrc02.length; i++) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("img", imgsrc02[i]);
            map.put("text", itemName02[i]);
            map.put("image", image02[i]);

            data02.add(map);
        }

        SimpleAdapter adapter = new SimpleAdapter(getContext(), data02, R.layout.me_item_layout,
                new String[]{"img", "text", "image"}, new int[]{R.id.me_lv_01_iv_01, R.id.me_lv_01_tv, R.id.me_lv_01_iv_02});
        me_lv_02.setAdapter(adapter);
        me_lv_02.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent =new Intent(getActivity(),LoginActivity.class);
                startActivity(intent);
            }
        });
    }




}

