package com.example.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.example.bean.User;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {
    private DBOpenHelper helper;
    private SQLiteDatabase db;
    
    public UserDAO(Context context) {
        helper = new DBOpenHelper(context);
        db = helper.getWritableDatabase();
    }
    
    public User find(String user, String password) {
        Cursor cursor = db.rawQuery("select * from tb_user where user=? and password=?", new String[] { user, password });
        if (cursor.moveToNext()) {
            return new User(cursor.getString(cursor.getColumnIndex("user")), cursor.getString(cursor.getColumnIndex("password")));
        }
        cursor.close();
        return null;
    }
    
    // 增
    public void insert(User user) {
        try {
            db.beginTransaction(); // 以事物的方式插入数据库，这样数据库只需要打开关闭一次
            ContentValues values = new ContentValues();
            values.put("user", user.name);
            values.put("password", user.password);
            db.insert("tb_user", null, values);
            db.setTransactionSuccessful(); // 事物成功， 一次写入数据库， 这一句真正到数据库里
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                db.endTransaction();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
    // 改
    public void updateUser(User user) {
        db.execSQL("update tb_user set user=?, password=? where _id=?", new Object[] { user.name, user.password, user.id });
    }
    
    // 删
    public void deteleUser(User user) {
        db.execSQL("delete from tb_user where _id=?", new String[] { String.valueOf(user.id) }); //？？？
    }
    
    public List<User> queryAllUsers() {
        String sql = "select * from tb_user";
        Cursor cursor = null;
        List<User> userList = new ArrayList<>();
        try {
            cursor = db.rawQuery(sql, null);
            while (cursor.moveToNext()) {
                User user = new User();
                user.id = cursor.getInt(cursor.getColumnIndex("_id"));
                user.name = cursor.getString(cursor.getColumnIndex("user"));
                user.password = cursor.getString(cursor.getColumnIndex("password"));
                userList.add(user);
            }
            return userList;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                cursor.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
