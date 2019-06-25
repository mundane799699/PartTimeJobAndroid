package com.example.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.example.bean.BianJian;
import java.util.ArrayList;
import java.util.List;

/**
 * BianjianDao
 *
 * @author fangyuan
 * @date 2019-06-25
 */
public class BianjianDao {
    private DBOpenHelper helper;
    private SQLiteDatabase db;
    
    public BianjianDao(Context context) {
        helper = new DBOpenHelper(context);
        db = helper.getWritableDatabase();
    }
    
    // 增
    public void add(BianJian data) {
        try {
            db.beginTransaction(); // 以事物的方式插入数据库，这样数据库只需要打开关闭一次
            ContentValues values = new ContentValues();
            values.put("title", data.title);
            values.put("describe", data.describe);
            values.put("date", data.date);
            db.insert("tb_bianjian", null, values);
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
    
    public List<BianJian> queryAllData() {
        String sql = "select * from tb_bianjian";
        Cursor cursor = null;
        List<BianJian> list = new ArrayList<BianJian>();
        try {
            cursor = db.rawQuery(sql, null);
            while (cursor.moveToNext()) {
                BianJian contact = new BianJian();
                contact.title = cursor.getString(cursor.getColumnIndex("title"));
                contact.describe = cursor.getString(cursor.getColumnIndex("describe"));
                contact.date = cursor.getString(cursor.getColumnIndex("date"));
                list.add(contact);
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
}
