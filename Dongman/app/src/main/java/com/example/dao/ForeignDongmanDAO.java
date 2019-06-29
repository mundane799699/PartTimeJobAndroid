package com.example.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.example.bean.GuowaiDongman;
import java.util.ArrayList;
import java.util.List;

public class ForeignDongmanDAO {
    private DBOpenHelper helper;
    private SQLiteDatabase db;
    
    public ForeignDongmanDAO(Context context) {
        helper = new DBOpenHelper(context);
        db = helper.getWritableDatabase();
    }
    
    public List<GuowaiDongman> queryAll() {
        String sql = "select * from tb_foreign_dongman";
        Cursor cursor = null;
        List<GuowaiDongman> foodList = new ArrayList<GuowaiDongman>();
        try {
            cursor = db.rawQuery(sql, null);
            while (cursor.moveToNext()) {
                GuowaiDongman food = new GuowaiDongman();
                food.name = cursor.getString(cursor.getColumnIndex("name"));
                food.describe = cursor.getString(cursor.getColumnIndex("describe"));
                food.date = cursor.getString(cursor.getColumnIndex("date"));
                foodList.add(food);
            }
            return foodList;
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
    public void add(GuowaiDongman food) {
        try {
            db.beginTransaction(); // 以事物的方式插入数据库，这样数据库只需要打开关闭一次
            ContentValues values = new ContentValues();
            values.put("name", food.name);
            values.put("describe", food.describe);
            values.put("date", food.date);
            db.insert("tb_foreign_dongman", null, values);
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
