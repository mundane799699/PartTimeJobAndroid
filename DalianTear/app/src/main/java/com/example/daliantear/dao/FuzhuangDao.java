package com.example.daliantear.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.example.daliantear.bean.FuZhuang;
import java.util.ArrayList;
import java.util.List;

/**
 * DataDao
 *
 * @author fangyuan
 * @date 2019-06-30
 */
public class FuzhuangDao {
    private DBOpenHelper helper;
    private SQLiteDatabase db;
    
    public FuzhuangDao(Context context) {
        helper = new DBOpenHelper(context);
        db = helper.getWritableDatabase();
    }
    
    public List<FuZhuang> queryAll() {
        String sql = "select * from tb_fuzhuang";
        Cursor cursor = null;
        List<FuZhuang> list = new ArrayList<FuZhuang>();
        try {
            cursor = db.rawQuery(sql, null);
            while (cursor.moveToNext()) {
                FuZhuang fuZhuang = new FuZhuang();
                fuZhuang.name = cursor.getString(cursor.getColumnIndex("name"));
                fuZhuang.caizhi = cursor.getString(cursor.getColumnIndex("caizhi"));
                fuZhuang.color = cursor.getString(cursor.getColumnIndex("color"));
                list.add(fuZhuang);
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
    public void add(FuZhuang fuZhuang) {
        try {
            db.beginTransaction(); // 以事物的方式插入数据库，这样数据库只需要打开关闭一次
            ContentValues values = new ContentValues();
            values.put("name", fuZhuang.name);
            values.put("caizhi", fuZhuang.caizhi);
            values.put("color", fuZhuang.color);
            db.insert("tb_fuzhuang", null, values);
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
