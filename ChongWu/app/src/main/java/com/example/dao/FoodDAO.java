package com.example.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.example.bean.Food;
import java.util.ArrayList;
import java.util.List;

public class FoodDAO {
    private DBOpenHelper helper;
    private SQLiteDatabase db;
    
    public FoodDAO(Context context) {
        helper = new DBOpenHelper(context);
        db = helper.getWritableDatabase();
    }
    
    public List<Food> queryAllFood() {
        String sql = "select * from tb_food";
        Cursor cursor = null;
        List<Food> foodList = new ArrayList<Food>();
        try {
            cursor = db.rawQuery(sql, null);
            while (cursor.moveToNext()) {
                Food food = new Food();
                food.name = cursor.getString(cursor.getColumnIndex("name"));
                food.describe = cursor.getString(cursor.getColumnIndex("describe"));
                food.drawableName = cursor.getString(cursor.getColumnIndex("drawableName"));
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
    public void add(Food food) {
        try {
            db.beginTransaction(); // 以事物的方式插入数据库，这样数据库只需要打开关闭一次
            ContentValues values = new ContentValues();
            values.put("name", food.name);
            values.put("describe", food.describe);
            values.put("drawableName", food.drawableName);
            db.insert("tb_food", null, values);
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
