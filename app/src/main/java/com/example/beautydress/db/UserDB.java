package com.example.beautydress.db;

import com.lidroid.xutils.db.annotation.Column;
import com.lidroid.xutils.db.annotation.Table;

import java.io.Serializable;

/**
 * Created by JYX on 2016/7/7.
 */

@Table(name = "user")
public class UserDB  implements Serializable {

    private int id;//默认是主键 不需要注解,类型必须是int  变量名必须是id,自增的
    @Column(column = "name")
    private String name;
    @Column(column = "pwd")
    private String pwd;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public UserDB() {

    }

    public UserDB(String name, String pwd) {
        this.name = name;
        this.pwd = pwd;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
}
