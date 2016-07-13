package com.example.beautydress.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.beautydress.R;
import com.example.beautydress.db.UserDB;
import com.example.beautydress.db.UserDao;
import com.lidroid.xutils.DbUtils;

import java.util.List;

public class RegisterActivity extends AppCompatActivity {
    private  EditText  et_1;
    private EditText et_2;
    private Button button;
    private DbUtils dbUtils;
    private UserDao userDao;//自己的类工具Dao

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        et_1 = (EditText) findViewById(R.id.et_name_id);
        et_2 = (EditText) findViewById(R.id.et_password);
        button = (Button) findViewById(R.id.bt_register);
       dbUtils=DbUtils.create(this);  //初始化xutils中的数据库操作工具
        userDao = new UserDao(dbUtils);//初始化数据库操作工具

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("reg","点击事件执行了");
                String name = et_1.getText().toString();
                Log.e("name",name);
                String pwd = et_2.getText().toString();
                Log.e("pwdDB",pwd);
                if(!TextUtils.isEmpty(name)&&!TextUtils.isEmpty(pwd)) {
                   List<UserDB> users= userDao.query(name);

                    if(users.size()==0) {
                        userDao.add(new UserDB(name,pwd));

                        Toast.makeText(RegisterActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);
                        startActivity(intent);
                    }else{
                        Toast.makeText(RegisterActivity.this, "该用户已存在", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(RegisterActivity.this, "注册失败，用户名或密码不能为空", Toast.LENGTH_SHORT).show();
                }
           }
        });
    }
}
