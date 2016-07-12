package com.example.beautydress.Activity;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.example.beautydress.R;

public class AddAddressActivity extends AppCompatActivity {
    private Toolbar addAddress_toolbar;
    //Toolbar对应的Actionbar
    private ActionBar actionbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_address);

        addAddress_toolbar = (Toolbar) findViewById(R.id.addAddress_toolbar);
        //设置自定义Toolbar
        setSupportActionBar(addAddress_toolbar);
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
    public void addAddress(View view){

    }
}
