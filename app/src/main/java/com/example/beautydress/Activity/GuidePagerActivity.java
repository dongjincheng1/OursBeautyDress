package com.example.beautydress.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.example.beautydress.R;

/**
 * 这个是开始的导航页
 */
public class GuidePagerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide_pager);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(GuidePagerActivity.this,MainActivity.class));
                GuidePagerActivity.this.finish();
            }
        },2000);

    }
}
