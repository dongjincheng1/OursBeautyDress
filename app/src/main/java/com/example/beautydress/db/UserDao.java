package com.example.beautydress.db;

import com.lidroid.xutils.DbUtils;
import com.lidroid.xutils.db.sqlite.Selector;
import com.lidroid.xutils.exception.DbException;

import java.util.List;

/**
 * Created by JYX on 2016/7/8-18:07.
 */
public class UserDao {
    private DbUtils dbUtils;
    public UserDao(DbUtils dbUtils) {
        this.dbUtils = dbUtils;
    }
    /**
     * 添加行
     */
    public void add(UserDB userDb) {
        try {
            dbUtils.save(userDb);
        } catch (DbException e) {
            e.printStackTrace();
        }
    }

    /**
     * 删除
     * @param userDb 被删除的具体是对象
     */
    public void delete(UserDB userDb) {
        try {
            dbUtils.delete(userDb);
        } catch (DbException e) {
            e.printStackTrace();
        }
    }


    /**
     * 查询
     * @return
     */
    public List<UserDB> query(String value) {
        try {
            //return dbUtils.findAll(UserDb.class);
            /**
             * 第一个参数是  要查询的表
             * 第二个参数 where条件
             */
            return dbUtils.findAll(Selector.from(UserDB.class).where("name","=",value));
        } catch (DbException e) {
            e.printStackTrace();
        }
        return null;
    }


}
