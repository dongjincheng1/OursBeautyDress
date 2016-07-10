package com.example.beautydress.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.beautydress.Activity.MessageActivity;
import com.example.beautydress.Activity.SearchActivity;
import com.example.beautydress.Adapter.HomeAdapter;
import com.example.beautydress.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Djc on 2016/7/6.
 */
public class TabHomeFragment extends Fragment implements View.OnClickListener{
    private View view;
    private TabLayout tl;
    private ViewPager viewPager;
    private List<Fragment> fragments;
    private  RecommendFragment recommendFragment;
    private WomenDressFragment womenDressFragment;
    private ShoesBagFragment shoesBagFragment;
    private MenDressFragment menDressFragment;
    private OlderFragment olderFragment;
    private  ChildFragment childFragmen;
    private   BeautyFragment beautyFragment;
    private TextilesFragment textilesFragment;
    private RelativeLayout search_bar;
    private ImageView message_bar;

    private LinearLayout ll_hor_tab;
    private LinearLayout ll_ver_tab;
    private ImageView iv_down;
    private ImageView iv_top;
    private PopupWindow mPopWindow;





    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        recommendFragment = new RecommendFragment();
        womenDressFragment = new WomenDressFragment();
        shoesBagFragment = new ShoesBagFragment();
        menDressFragment = new MenDressFragment();
        olderFragment = new OlderFragment();
        childFragmen = new ChildFragment();
        beautyFragment = new BeautyFragment();
        textilesFragment=new TextilesFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = (View) inflater.inflate(R.layout.tab_home_layout,null);
        initView();
        fragments=new ArrayList<Fragment>();
        search_bar= (RelativeLayout) view.findViewById(R.id.search_bar);
        message_bar= (ImageView) view.findViewById(R.id.message_bar);
        aboutOnClick();
        return view;

    }
    private void aboutOnClick(){
        search_bar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(getActivity(),SearchActivity.class);
                startActivity(intent);
            }
        });
        message_bar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getContext(), MessageActivity.class);
                startActivity(intent);
            }
        });
    }
    private void initView() {
        tl = (TabLayout) view.findViewById(R.id.tl_id);
        ll_hor_tab = (LinearLayout)view.findViewById(R.id.ll_hor_tab_id);
        ll_ver_tab = (LinearLayout)view.findViewById(R.id.ll_ver_tab_id);
        iv_down = (ImageView)view.findViewById(R.id.iv_down_id);
        iv_top = (ImageView)view.findViewById(R.id.iv_top_id);
        viewPager=(ViewPager)view.findViewById(R.id.home_viewpager);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        String[] tabsName= getResources().getStringArray(R.array.tabsName);
        for (String tabName:tabsName) {
            tl.addTab(tl.newTab().setText(tabName));
        }

        fragments.add(recommendFragment);
        fragments.add(womenDressFragment);
        fragments.add(shoesBagFragment);
        fragments.add(menDressFragment);
        fragments.add(olderFragment);
        fragments.add(childFragmen);
        fragments.add(beautyFragment);
        fragments.add(textilesFragment);
        tl.setupWithViewPager(viewPager);
        viewPager.setAdapter(new HomeAdapter(getChildFragmentManager(),tabsName,fragments));

        iv_down.setOnClickListener(this);
        iv_top.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.iv_down_id:
                ll_hor_tab.setVisibility(View.GONE);
                ll_ver_tab.setVisibility(View.VISIBLE);
                showPopupWindow();
                break;

            case R.id.iv_top_id:
                ll_hor_tab.setVisibility(View.VISIBLE);
                ll_ver_tab.setVisibility(View.GONE);
                mPopWindow.dismiss();
                break;
        }
    }

    private void showPopupWindow() {
        View contentView = LayoutInflater.from(getActivity()).inflate(R.layout.tab_ver_item,null);
        mPopWindow = new PopupWindow(contentView);
        mPopWindow.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        mPopWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        TextView tv1 = (TextView)contentView.findViewById(R.id.tv_tab_tj_id);
        TextView tv2 = (TextView)contentView.findViewById(R.id.tv_tab_girl_id);
        TextView tv3 = (TextView)contentView.findViewById(R.id.tv_tab_shose_id);
        TextView tv4 = (TextView)contentView.findViewById(R.id.tv_tab_men_id);
        TextView tv5 = (TextView)contentView.findViewById(R.id.tv_tab_older_id);
        TextView tv6 = (TextView)contentView.findViewById(R.id.tv_tab_child_id);
        TextView tv7 = (TextView)contentView.findViewById(R.id.tv_tab_meizhuang_id);
        TextView tv8 = (TextView)contentView.findViewById(R.id.tv_tab_jiafang_id);

        MyOnclickListenr listener = new MyOnclickListenr();
        tv1.setOnClickListener(listener);
        tv2.setOnClickListener(listener);
        tv3.setOnClickListener(listener);
        tv4.setOnClickListener(listener);
        tv5.setOnClickListener(listener);
        tv6.setOnClickListener(listener);
        tv7.setOnClickListener(listener);
        tv8.setOnClickListener(listener);

        mPopWindow.showAsDropDown(ll_ver_tab);

    }

    /**
     * 自定义监听子类,实现每个标签点击跳转到相应界面
     */
    private class  MyOnclickListenr  implements View.OnClickListener{

        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case  R.id.tv_tab_tj_id:
                    viewPager.setCurrentItem(0);
                    ll_hor_tab.setVisibility(View.VISIBLE);
                    ll_ver_tab.setVisibility(View.GONE);
                    mPopWindow.dismiss();
                    break;
                case  R.id.tv_tab_girl_id:
                    viewPager.setCurrentItem(1);
                    ll_hor_tab.setVisibility(View.VISIBLE);
                    ll_ver_tab.setVisibility(View.GONE);
                    mPopWindow.dismiss();
                    break;
                case  R.id.tv_tab_shose_id:
                    viewPager.setCurrentItem(2);
                    ll_hor_tab.setVisibility(View.VISIBLE);
                    ll_ver_tab.setVisibility(View.GONE);
                    mPopWindow.dismiss();
                    break;
                case  R.id.tv_tab_men_id:
                    viewPager.setCurrentItem(3);
                    ll_hor_tab.setVisibility(View.VISIBLE);
                    ll_ver_tab.setVisibility(View.GONE);
                    mPopWindow.dismiss();
                    break;
                case  R.id.tv_tab_older_id:
                    viewPager.setCurrentItem(4);
                    ll_hor_tab.setVisibility(View.VISIBLE);
                    ll_ver_tab.setVisibility(View.GONE);
                    mPopWindow.dismiss();
                    break;
                case  R.id.tv_tab_child_id:
                    viewPager.setCurrentItem(5);
                    ll_hor_tab.setVisibility(View.VISIBLE);
                    ll_ver_tab.setVisibility(View.GONE);
                    mPopWindow.dismiss();
                    break;
                case  R.id.tv_tab_meizhuang_id:
                    viewPager.setCurrentItem(6);
                    ll_hor_tab.setVisibility(View.VISIBLE);
                    ll_ver_tab.setVisibility(View.GONE);
                    mPopWindow.dismiss();
                    break;
                case  R.id.tv_tab_jiafang_id:
                    viewPager.setCurrentItem(7);
                    ll_hor_tab.setVisibility(View.VISIBLE);
                    ll_ver_tab.setVisibility(View.GONE);
                    mPopWindow.dismiss();
                    break;
        }
    }

    }

}