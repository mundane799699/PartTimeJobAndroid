package com.example.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.example.bean.WuyangYundong;
import java.util.ArrayList;
import java.util.List;

public class WuyangDAO {
    private DBOpenHelper helper;
    private SQLiteDatabase db;
    
    public WuyangDAO(Context context) {
        helper = new DBOpenHelper(context);
        db = helper.getWritableDatabase();
    }
    
    public List<WuyangYundong> queryAll() {
        String sql = "select * from tb_wuyang";
        Cursor cursor = null;
        List<WuyangYundong> sceneList = new ArrayList<WuyangYundong>();
        try {
            cursor = db.rawQuery(sql, null);
            while (cursor.moveToNext()) {
                WuyangYundong scene = new WuyangYundong();
                scene.name = cursor.getString(cursor.getColumnIndex("name"));
                scene.describe = cursor.getString(cursor.getColumnIndex("describe"));
                scene.effect = cursor.getString(cursor.getColumnIndex("effect"));
                sceneList.add(scene);
            }
            return sceneList;
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
    public void add(WuyangYundong scene) {
        try {
            db.beginTransaction(); // 以事物的方式插入数据库，这样数据库只需要打开关闭一次
            ContentValues values = new ContentValues();
            values.put("name", scene.name);
            values.put("describe", scene.describe);
            values.put("effect", scene.effect);
            db.insert("tb_wuyang", null, values);
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
