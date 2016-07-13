package com.example.beautydress.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.alibaba.fastjson.JSONException;
import com.bumptech.glide.Glide;
import com.example.beautydress.Activity.ShowActivity;
import com.example.beautydress.Adapter.MyBaseAdapter;
import com.example.beautydress.Adapter.ViewHolder;
import com.example.beautydress.R;
import com.example.beautydress.bean.BrannerItem;
import com.example.beautydress.bean.JingPin;
import com.example.beautydress.common.Uris;
import com.example.beautydress.utils.ParseJSONUtils;
import com.example.beautydress.view.MyGirdView;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;

import java.util.LinkedList;
import java.util.List;


/**
 * Created by jyx on 2016/7/8
 */
public class RecommendFragment extends Fragment {
    private View view;
    private ViewPager vp_top;
    private ListView lv_detail;
    private HttpUtils hUtils;
//    private BitmapUtils bitmapUtils;
    private String brannerUrl;
    private List<BrannerItem> brannerItems;
    private List<ImageView> ds;
    private LinearLayout ll_container_id;
    private Handler mHandler;
    private FloatingActionButton topButton;

    private String jingPingUrl;
    private List<JingPin> jingPinList;


    private static final String TAG = "RecommendFragment";
    private MyGirdView jingPin_GV;
    private ScrollView sv_content;
    private MyBaseAdapter<JingPin> JinPinAdapter;
    private MyBitmapUtils myBitmapUtils;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        hUtils = new HttpUtils();
//        bitmapUtils = new BitmapUtils(getActivity());
        brannerItems = new LinkedList<>();
        brannerUrl = Uris.HOME_BRANNER_URI.toString();
        jingPingUrl = Uris.HOME_RECOMMEND_URI.toString();
        Log.i(TAG, "onCreate: " + brannerUrl);
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.recommend_layout,null);
        vp_top = (ViewPager) view.findViewById(R.id.vp_top_id);
        jingPin_GV = (MyGirdView)view.findViewById(R.id.jp_gv_id);
        ll_container_id = (LinearLayout) view.findViewById(R.id.ll_container_id);
        //topButton = (FloatingActionButton) view.findViewById(R.id.to_top_button);
        sv_content = (ScrollView) view.findViewById(R.id.sv_content_id);
        sv_content.scrollTo(0,0);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        //数据源
        // loadDataFromServer();

        // 关于小圆点
        aboutDots();

        //关于ViewPager的操作
        aboutViewPager();

        //自动切换
        if (mHandler == null) {
            mHandler = new Handler() {
                @Override
                public void handleMessage(Message msg) {
                    Log.i(TAG, "handleMessage: " + msg);
                    int currentItem = vp_top.getCurrentItem();
                    if (currentItem < ds.size() - 1) {
                        currentItem++;
                    } else {
                        currentItem = 0;
                    }
                    vp_top.setCurrentItem(currentItem);
                    mHandler.sendEmptyMessageDelayed(0, 3000);
                }
            };
            mHandler.sendEmptyMessageDelayed(0, 3000);
        }
        aboutJingPingGridView(jingPingUrl);
        //aboutToTopButton();
        jingPin_GV.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        break;
                    case MotionEvent.ACTION_MOVE:
                        break;
                    case MotionEvent.ACTION_UP:
                        if (jingPin_GV.getLastVisiblePosition() == JinPinAdapter.getCount() - 1) {
                        }
                        break;
                }

                return true;
            }
        });
        aboutModleList(view);

//       jingPin_GV.setOnScrollListener(new AbsListView.OnScrollListener() {
//           @Override
//           public void onScrollStateChanged(AbsListView absListView, int i) {
//
//               if(jingPin_GV.getLastVisiblePosition()==jingPinList.size()/2){
//
//                   Toast.makeText(getActivity(),"滑动到最后了",Toast.LENGTH_LONG).show();
//                  hUtils.send(HttpRequest.HttpMethod.GET, "http://api.yuike.com/gmall/api/1.0/allbuy/list.php?mid=457465&type=dress&category_ids=4796%2C4797%2C4805%2C4838%2C4839%2C4840%2C4841%2C4843&sort=default&yk_pid=3&yk_appid=1&yk_cc=baidu&yk_cvc=311&cursor=120&count=40", new RequestCallBack<String>() {
//                        @Override
//                        public void onFailure(HttpException error, String msg) {
//                            Toast.makeText(getActivity(),"加载失败",Toast.LENGTH_LONG).show();
//                        }
//
//                        @Override
//                        public void onSuccess(ResponseInfo<String> responseInfo) {
//                            List<JingPin> jingPinMore=ParseJSONUtils.parseJingPin(responseInfo.result);
//                            jingPinList.addAll(jingPinMore);
//                            JinPinAdapter.notifyDataSetChanged();
//
//                        }
//                    });
//               }
//           }
//
//           @Override
//           public void onScroll(AbsListView absListView, int i, int i1, int i2) {

//           }
//       });
//
        super.onActivityCreated(savedInstanceState);
    }

    /**
     * 关于ViewPager的操作
     */
    private void aboutViewPager() {

        //数据源11
        int[] imageIds = new int[]{R.mipmap.ban1, R.mipmap.ban2, R.mipmap.ban3, R.mipmap.ban4};
        ds = new LinkedList<>();
//        for (BrannerItem brannerItem : brannerItems) {
//            String picUrl = brannerItem.getPicUrl();
//           // imageUrls.add(picUrl);
//            ImageView iv = new ImageView(getActivity());
//            bitmapUtils.display(iv,picUrl);
//            ds.add(iv);
//        }
        for (int imageId : imageIds) {
            // 构建ImageView的对象
            ImageView iv = new ImageView(getActivity());
            iv.setScaleType(ImageView.ScaleType.FIT_XY);
            iv.setImageResource(imageId);
            // 将对象添加到数据源中
            ds.add(iv);
        }

        //适配器
        PagerAdapter adapter = new MyPagerAdapter();

        //设置适配器
        vp_top.setAdapter(adapter);

        //添加监听
        vp_top.setOnPageChangeListener(new MyOnPageChangeListener());
    }

    /**
     * 自定义OnPageChangeListener实现类
     */
    private final class MyOnPageChangeListener implements ViewPager.OnPageChangeListener {

        @Override
        public void onPageScrolled(int position, float positionOffset,
                                   int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            // 思路：
            // ViewPager决定小圆点的状态
            // 1、将小圆点的状态都复原（初始）（循环）
            for (int i = 0; i < ll_container_id.getChildCount(); i++) {
                // 1）找到每个孩子（ImageView）
                ImageView iv = (ImageView) ll_container_id.getChildAt(i);
                // 将所有的子控件设置为enable状态
                iv.setEnabled(true);
            }

            // 2、将当前页面对应的小圆点状态置为不可点击
            ll_container_id.getChildAt(position).setEnabled(false);

        }


        @Override
        public void onPageScrollStateChanged(int state) {

            int draging = ViewPager.SCROLL_STATE_DRAGGING;// 手指在屏幕上拖动页面时
            int idle = ViewPager.SCROLL_STATE_IDLE;// 空闲状态，页面固定了
            int settling = ViewPager.SCROLL_STATE_SETTLING;// 到达目的界面惯性，有一个上抛的动作
        }

    }

    /**
     * PageAdapter 自定义子类
     */
    private final class MyPagerAdapter extends PagerAdapter {

        @Override
        public int getCount() {

            return ds.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {

            return view == object;
        }


        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            // ①从数据源中取出相应位置的视图视图
            ImageView iv = ds.get(position);

            // ②添加到ViewPager
            container.addView(iv);// 此处容易忽略！不要忘了！作用：在ViewPager容器控件中添加上相应的视图。
            return iv;
        }


        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {

            // 方式一：移除instantiateItem（xxx）执行后的返回值
            // container.removeView((View) object);

            // 方式2：移除数据源中对应位置的控件实例
            container.removeView(ds.get(position));
        }

    }

    private void loadDataFromServer() {
        hUtils.send(HttpRequest.HttpMethod.GET, brannerUrl, new RequestCallBack<String>() {

            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                String result = responseInfo.result;
                Log.i("TAG", "onSuccess: " + result);
                brannerItems = com.example.beautydress.utils.ParseJSONUtils.parseBranner(result);

            }

            @Override
            public void onFailure(HttpException error, String msg) {
                Log.i("TAG", "onFailure: " + msg);
            }
        });
    }

    /**
     * 关于小圆点
     */
    private void aboutDots() {

        // 监听器实例准备
        MyOnClickListener listener = new MyOnClickListener();

        // 通过循环分析父控件中的子控件
        // 1、循环体：
        for (int i = 0; i < ll_container_id.getChildCount(); i++) {
            // 1）找到每个孩子（ImageView）
            ImageView iv = (ImageView) ll_container_id.getChildAt(i);
            // 将所有的子控件设置为enable状态
            iv.setEnabled(true);

            // 2）给每个孩子都要添加监听器
            iv.setOnClickListener(listener);
            iv.setTag(i);

        }

        // 2、默认第一个小圆点被选中
        ll_container_id.getChildAt(0).setEnabled(false);

    }

    /**
     * 自定义的OnClickListener接口的实现类
     */
    private final class MyOnClickListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            // 决定ViewPager中目前显示的是哪张图片
            vp_top.setCurrentItem((Integer) (v.getTag()));

        }

    }

    /**
     * 精品推荐列表
     *
     * @param jingPinUrl
     */
    private void aboutJingPingGridView(String jingPinUrl) {
        hUtils.send(HttpRequest.HttpMethod.GET, jingPinUrl, new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                try {
                    jingPinList = ParseJSONUtils.parseJingPin(responseInfo.result);
                    jingPin_GV.setAdapter(new MyBaseAdapter<JingPin>(jingPinList, getActivity(), R.layout.shangpin_simple_item_layout) {
                        @Override
                        public void setData(ViewHolder viewHolder, int position) {
                            TextView tv_title = (TextView) viewHolder.findViewById(R.id.jp_item_title_id);
                            TextView tv_selling_price = (TextView) viewHolder.findViewById(R.id.jp_item_selling_price_id);
                            TextView tv_sales_volume = (TextView) viewHolder.findViewById(R.id.jp_item_sales_volume_id);
                            ImageView iv_img = (ImageView) viewHolder.findViewById(R.id.jp_item_img_id);
                            tv_title.setText(jingPinList.get(position).getTitle());
                            tv_selling_price.setText("¥"+jingPinList.get(position).getSelling_price()+"");
                            tv_sales_volume.setText(jingPinList.get(position).getSales_volume()+"");
//                            bitmapUtils.display(iv_img,jingPinList.get(position).getPic_url());
                            Glide.with(getActivity()).load(jingPinList.get(position).getPic_url())
                                    .override(600,200)
                                    .into(iv_img);
                        }
                    });
                    jingPin_GV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                            //点击事件
                            Intent intent = new Intent(getActivity(), ShowActivity.class);
                            intent.putExtra("detailUrl",jingPinList.get(i).getUrl().toString());
                            startActivity(intent);
                        }
                    });
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(HttpException error, String msg) {
                Log.i(TAG, "onFailure: " + msg);
            }
        });

    }

//    private void aboutToTopButton() {
//        topButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                sv_content.fullScroll(ScrollView.FOCUS_UP);
//                sv_content.scrollTo(10, 10);
//            }
//        });
//    }

    private void aboutModleList(View view) {
        String[] img_id=new String[]{Uris.MODLE_LIST_1,Uris.MODLE_LIST_2,Uris.MODLE_LIST_3,Uris.MODLE_LIST_4
                ,Uris.MODLE_LIST_5,Uris.MODLE_LIST_6,Uris.MODLE_LIST_7};
            ImageView img1=(ImageView)view.findViewById(R.id.rec_tm_img1_id);
            ImageView img2=(ImageView)view.findViewById(R.id.rec_tm_img2_id);
            ImageView img3=(ImageView)view.findViewById(R.id.rec_tm_img3_id);
            ImageView img4=(ImageView)view.findViewById(R.id.rec_tm_img4_id);
            ImageView img5=(ImageView)view.findViewById(R.id.rec_tm_img5_id);
            ImageView img6=(ImageView)view.findViewById(R.id.rec_tm_img6_id);
            ImageView img7=(ImageView)view.findViewById(R.id.rec_tm_img7_id);
            myBitmapUtils.display(img1,img_id[0]);
            myBitmapUtils.display(img2,img_id[1]);
            myBitmapUtils.display(img3,img_id[2]);
            myBitmapUtils.display(img4,img_id[3]);
            myBitmapUtils.display(img5,img_id[5]);
            myBitmapUtils.display(img6,img_id[4]);
            myBitmapUtils.display(img7,img_id[6]);
    }

}
