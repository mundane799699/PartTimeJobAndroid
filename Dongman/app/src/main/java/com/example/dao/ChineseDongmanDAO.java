package com.example.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.example.bean.ChineseDongman;
import java.util.ArrayList;
import java.util.List;

public class ChineseDongmanDAO {
    private DBOpenHelper helper;
    private SQLiteDatabase db;
    
    public ChineseDongmanDAO(Context context) {
        helper = new DBOpenHelper(context);
        db = helper.getWritableDatabase();
    }
    
    public List<ChineseDongman> queryAllMovie() {
        String sql = "select * from tb_chinese_dongman";
        Cursor cursor = null;
        List<ChineseDongman> sceneList = new ArrayList<ChineseDongman>();
        try {
            cursor = db.rawQuery(sql, null);
            while (cursor.moveToNext()) {
                ChineseDongman scene = new ChineseDongman();
                scene.name = cursor.getString(cursor.getColumnIndex("name"));
                scene.describe = cursor.getString(cursor.getColumnIndex("describe"));
                scene.date = cursor.getString(cursor.getColumnIndex("date"));
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
    public void add(ChineseDongman scene) {
        try {
            db.beginTransaction(); // 以事物的方式插入数据库，这样数据库只需要打开关闭一次
            ContentValues values = new ContentValues();
            values.put("name", scene.name);
            values.put("describe", scene.describe);
            values.put("date", scene.date);
            db.insert("tb_chinese_dongman", null, values);
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
