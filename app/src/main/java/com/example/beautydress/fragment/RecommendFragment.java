package com.example.beautydress.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.example.beautydress.R;
import com.example.beautydress.bean.BrannerItem;
import com.example.beautydress.common.Uris;
import com.lidroid.xutils.BitmapUtils;
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
    private BitmapUtils bitmapUtils;
    private  String brannerUrl;
    private  List<BrannerItem>  brannerItems;
    private List<ImageView> ds;
    private LinearLayout ll_container_id;
    private Handler mHandler;

    private static final String TAG = "RecommendFragment";
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        hUtils = new HttpUtils();
        bitmapUtils = new BitmapUtils(getActivity());
        brannerItems=new LinkedList<>();
        brannerUrl = Uris.HOME_BRANNER_URI.toString();
        Log.i(TAG, "onCreate: "+brannerUrl);
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.recommend_layout,null);
        vp_top = (ViewPager)view.findViewById(R.id.vp_top_id);
        ll_container_id = (LinearLayout)view.findViewById(R.id.ll_container_id);
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
            mHandler = new Handler(){
                @Override
                public void handleMessage(Message msg) {
                    Log.i(TAG, "handleMessage: "+msg);
                    int currentItem = vp_top.getCurrentItem();
                    if (currentItem<ds.size() -1){
                        currentItem++;
                    }else {
                        currentItem = 0;
                    }
                    vp_top.setCurrentItem(currentItem);
                    mHandler.sendEmptyMessageDelayed(0,3000);
                }
            };
            mHandler.sendEmptyMessageDelayed(0,3000);
        }

        super.onActivityCreated(savedInstanceState);
    }

    /**
     * 关于ViewPager的操作
     *
     */
    private void aboutViewPager() {

        //数据源
        int[] imageIds = new int[]{R.mipmap.ban1,R.mipmap.ban2,R.mipmap.ban3,R.mipmap.ban4};
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

            return  ds.size();
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
                Log.i("TAG", "onSuccess: "+result);
                 brannerItems = com.example.beautydress.utils.ParseJSONUtils.parseBranner(result);

            }

            @Override
            public void onFailure(HttpException error, String msg) {
                Log.i("TAG", "onFailure: "+msg);
            }
        });
    }

    /**
     * 关于小圆点
     *
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


}
