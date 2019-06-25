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
    
    public long getCount() {
        Cursor cursor = db.rawQuery("select count(password)from tb_user", null);
        if (cursor.moveToNext()) {
            return cursor.getLong(0);
        }
        cursor.close();
        return 0;
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
    public void add(String user, String password) {
        try {
            db.beginTransaction(); // 以事物的方式插入数据库，这样数据库只需要打开关闭一次
            ContentValues values = new ContentValues();
            values.put("user", user);
            values.put("password", password);
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
    public void update(User user) {
        db.execSQL("update tb_user set password=?", new Object[] { user.password });
    }
    
    public List<User> queryAllUsers() {
        String sql = "select * from tb_user";
        Cursor cursor = null;
        List<User> userList = new ArrayList<User>();
        try {
            cursor = db.rawQuery(sql, null);
            while (cursor.moveToNext()) {
                User user = new User();
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
