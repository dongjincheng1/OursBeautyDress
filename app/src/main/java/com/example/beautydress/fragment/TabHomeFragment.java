package com.example.beautydress.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SimpleAdapter;

import com.example.beautydress.Adapter.HomeAdapter;
import com.example.beautydress.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by  jyx on 2016/7/9
 */
public class TabHomeFragment extends Fragment implements View.OnClickListener{
    private View view;
    private TabLayout tl;
    private ViewPager viewPager;
    private List<Fragment> fragments;
    private LinearLayout ll_hor_tab;
    private LinearLayout ll_ver_tab;
    private ImageView iv_down;
    private ImageView iv_top;
    private GridView tab_gv;
    List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
    String[]  tab_ver ={"推荐","女装","鞋包","男装","中老年","童装","美妆","家纺"};


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = (View) inflater.inflate(R.layout.tab_home_layout,null);

        initView();
        fragments=new ArrayList<Fragment>();
        return view;
    }

    private void initView() {
        tl = (TabLayout) view.findViewById(R.id.tl_id);
        ll_hor_tab = (LinearLayout)view.findViewById(R.id.ll_hor_tab_id);
        ll_ver_tab = (LinearLayout)view.findViewById(R.id.ll_ver_tab_id);
        iv_down = (ImageView)view.findViewById(R.id.iv_down_id);
        iv_top = (ImageView)view.findViewById(R.id.iv_top_id);
        tab_gv = (GridView)view.findViewById(R.id.tb_gv_id);
        viewPager=(ViewPager)view.findViewById(R.id.home_viewpager);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        String[] tabsName= getResources().getStringArray(R.array.tabsName);
        for (String tabName:tabsName) {
            tl.addTab(tl.newTab().setText(tabName));
        }
        fragments.add(new RecommendFragment());
        fragments.add(new WomenDressFragment());
        fragments.add(new ShoesBagFragment());
        fragments.add(new MenDressFragment());
        fragments.add(new OlderFragment());
        fragments.add(new ChildFragment());
        fragments.add(new BeautyFragment());
        tl.setupWithViewPager(viewPager);
        viewPager.setAdapter(new HomeAdapter(getFragmentManager(),tabsName,fragments));

        aboutVerticalTab();

        iv_down.setOnClickListener(this);
        iv_top.setOnClickListener(this);
    }

    private void aboutVerticalTab() {
        for (int i = 0; i < tab_ver.length; i++) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("tab", tab_ver[i]);
            list.add(map);
        }
        SimpleAdapter adapter = new SimpleAdapter(getActivity(), list, R.layout.tab_ver_item, new String[] { "tab" },
                new int[] { R.id.tv_tab_item_id });
        tab_gv.setAdapter(adapter);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.iv_down_id:
                ll_hor_tab.setVisibility(View.GONE);
                ll_ver_tab.setVisibility(View.VISIBLE);
                break;

            case R.id.iv_top_id:
                ll_hor_tab.setVisibility(View.VISIBLE);
                ll_ver_tab.setVisibility(View.GONE);
                break;
        }
    }
}