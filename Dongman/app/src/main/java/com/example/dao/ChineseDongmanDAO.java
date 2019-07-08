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
    
    public List<ChineseDongman> queryAll() {
        String sql = "select * from tb_chinese_dongman";
        Cursor cursor = null;
        List<ChineseDongman> list = new ArrayList<ChineseDongman>();
        try {
            cursor = db.rawQuery(sql, null);
            while (cursor.moveToNext()) {
                ChineseDongman dongman = new ChineseDongman();
                dongman.id = cursor.getInt(cursor.getColumnIndex("_id"));
                dongman.name = cursor.getString(cursor.getColumnIndex("name"));
                dongman.name = cursor.getString(cursor.getColumnIndex("name"));
                dongman.describe = cursor.getString(cursor.getColumnIndex("describe"));
                dongman.date = cursor.getString(cursor.getColumnIndex("date"));
                list.add(dongman);
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
    public void add(ChineseDongman dongman) {
        try {
            db.beginTransaction(); // 以事物的方式插入数据库，这样数据库只需要打开关闭一次
            ContentValues values = new ContentValues();
            values.put("name", dongman.name);
            values.put("describe", dongman.describe);
            values.put("date", dongman.date);
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
    
    // 删除
    public void deleteDongman(ChineseDongman dongman) {
        db.execSQL("delete from tb_chinese_dongman where _id=?", new String[] { String.valueOf(dongman.id) }); //？？？
    }
    
    // 改
    public void updateDongman(ChineseDongman dongman) {
        db.execSQL("update tb_chinese_dongman set name=?, describe=?, date=? where _id=?", new Object[] { dongman.name, dongman.describe, dongman.date, dongman.id });
    }
}
