package com.example.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.example.bean.YouyangYundong;
import java.util.ArrayList;
import java.util.List;

public class YouYangDAO {
    private DBOpenHelper helper;
    private SQLiteDatabase db;
    
    public YouYangDAO(Context context) {
        helper = new DBOpenHelper(context);
        db = helper.getWritableDatabase();
    }
    
    public List<YouyangYundong> queryAllFood() {
        String sql = "select * from tb_youyang";
        Cursor cursor = null;
        List<YouyangYundong> list = new ArrayList<YouyangYundong>();
        try {
            cursor = db.rawQuery(sql, null);
            while (cursor.moveToNext()) {
                YouyangYundong yundong = new YouyangYundong();
                yundong.name = cursor.getString(cursor.getColumnIndex("name"));
                yundong.describe = cursor.getString(cursor.getColumnIndex("describe"));
                yundong.effect = cursor.getString(cursor.getColumnIndex("effect"));
                list.add(yundong);
            }
            return list;
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
    
    // 增
    public void add(YouyangYundong yundong) {
        try {
            db.beginTransaction(); // 以事物的方式插入数据库，这样数据库只需要打开关闭一次
            ContentValues values = new ContentValues();
            values.put("name", yundong.name);
            values.put("describe", yundong.describe);
            values.put("effect", yundong.effect);
            db.insert("tb_youyang", null, values);
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
}
