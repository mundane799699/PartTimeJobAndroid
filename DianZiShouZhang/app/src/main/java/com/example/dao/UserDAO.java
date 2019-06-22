package com.example.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.example.model.User;
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
    public User add(String user, String password) {
        Cursor cursor = db.rawQuery("insert into tb_user (user,password)values(?,?)", new String[] { user, password });
        if (cursor.moveToNext()) {
            return new User(cursor.getString(cursor.getColumnIndex("user")), cursor.getString(cursor.getColumnIndex("password")));
        }
        cursor.close();
        return null;
    }
    
    // 改
    public void update(User user) {
        db.execSQL("update tb_user set password=?", new Object[] { user.password });
    }
    
    public List<User> queryAllUsers() {
        String sql = "select * from tb_user";
        Cursor cursor = null;
        List<User> userList = new ArrayList<>();
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
