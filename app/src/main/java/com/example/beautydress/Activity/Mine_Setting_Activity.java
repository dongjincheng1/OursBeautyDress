package com.example.beautydress.Activity;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.example.beautydress.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Mine_Setting_Activity extends AppCompatActivity {
    private ListView me_lv_03;
    private List<Map<String, Object>> data03 = new ArrayList<Map<String, Object>>();

    private Toolbar toolbar;
    //Toolbar对应的Actionbar
    private ActionBar actionbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine__setting_);
        me_lv_03= (ListView) findViewById(R.id.me_lv_03);
        toolbar = (Toolbar) findViewById(R.id.setting_toolbar);
        //设置自定义Toolbar
        setSupportActionBar(toolbar);
        //获取到Toolbar对应的Actionbar
        actionbar = getSupportActionBar();
        //添加返回按钮
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setDisplayShowTitleEnabled(false);
        aboutListView_03();

    }
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            //返回按钮
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
    private void aboutListView_03() {
        int[] image = new int[]{R.mipmap.yuike_settings_indicator_un, R.mipmap.yuike_settings_indicator_un, R.mipmap.yuike_settings_indicator_un, R.mipmap.yuike_settings_indicator_un, R.mipmap.yuike_settings_indicator_un, R.mipmap.yuike_settings_indicator_un, R.mipmap.yuike_settings_indicator_un};
        int[] imgsrc = new int[]{R.mipmap.kimager_clear_cache, R.mipmap.kimager_push, R.mipmap.kimager_sharet, R.mipmap.kimager_upgrade,
                R.mipmap.kimager_about};
        String itemName[] = getResources().getStringArray(R.array.itemName_03);

        for (int i = 0; i < imgsrc.length; i++) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("img", imgsrc[i]);
            map.put("text", itemName[i]);
            map.put("image", image[i]);

            data03.add(map);
        }

        SimpleAdapter adapter = new SimpleAdapter(Mine_Setting_Activity.this, data03, R.layout.me_item_layout,
                new String[]{"img", "text", "image"}, new int[]{R.id.me_lv_01_iv_01, R.id.me_lv_01_tv, R.id.me_lv_01_iv_02});
        me_lv_03.setAdapter(adapter);
    }
}
