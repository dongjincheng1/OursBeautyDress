package com.example.beautydress.Activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.beautydress.R;
import com.example.beautydress.fragment.TabCartFragment;
import com.example.beautydress.fragment.TabHomeFragment;
import com.example.beautydress.fragment.TabMineFragment;
import com.example.beautydress.fragment.TabNineFragment;

public class MainActivity extends AppCompatActivity {
    private RadioGroup rg_guide;
    private LinearLayout ll_cotainer;
    private RadioButton rb_home;
    private RadioButton rb_9kuai9;
    private RadioButton rb_cart;
    private RadioButton rb_mine;
    private FragmentTransaction transaction;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initWidgets();
        aboutRadioGroupGuide();



    }
    /**
     * 初始化控件
     */
    private void  initWidgets(){
        rg_guide=(RadioGroup)findViewById(R.id.rg_guide_id);
        ll_cotainer=(LinearLayout) findViewById(R.id.ll_container_id);
        transaction=getSupportFragmentManager().beginTransaction();
        repacleView(new TabHomeFragment());
    }

    /**
     * 关于radiogroup的操作
     */
    private void aboutRadioGroupGuide() {
        rg_guide.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i){
                    case R.id.rb_home_id:
                        repacleView(new TabHomeFragment());
                        break;
                    case R.id.rb_9kuai9_id:
                        repacleView(new TabNineFragment());
                        break;
                    case R.id.rb_cart_id:
                        repacleView(new TabCartFragment());
                        break;
                    case R.id.rb_space_id:
                        repacleView(new TabMineFragment());
                        break;

                }
            }
        });
    }
    private void repacleView(Fragment fragment){
        transaction=getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.ll_container_id,fragment).commit();
        }
}
