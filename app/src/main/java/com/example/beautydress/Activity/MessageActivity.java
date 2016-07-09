package com.example.beautydress.Activity;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.example.beautydress.R;

public class MessageActivity extends AppCompatActivity {
    private Toolbar message_toolbar;
    //Toolbar对应的Actionbar
    private ActionBar actionbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);

        message_toolbar = (Toolbar) findViewById(R.id.message_toolbar);
        //设置自定义Toolbar
        setSupportActionBar(message_toolbar);
        //获取到Toolbar对应的Actionbar
        actionbar = getSupportActionBar();
        //添加返回按钮
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setDisplayShowTitleEnabled(false);
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
}
