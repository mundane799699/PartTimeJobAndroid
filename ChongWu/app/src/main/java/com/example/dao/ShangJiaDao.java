package com.example.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.example.bean.ShangJia;
import java.util.ArrayList;
import java.util.List;

/**
 * ShangJiaDao
 *
 * @author fangyuan
 * @date 2019-06-28
 */
public class ShangJiaDao {
    
    private DBOpenHelper helper;
    private SQLiteDatabase db;
    
    public ShangJiaDao(Context context) {
        helper = new DBOpenHelper(context);
        db = helper.getWritableDatabase();
    }
    
    // 增
    public void add(ShangJia shangJia) {
        try {
            db.beginTransaction(); // 以事物的方式插入数据库，这样数据库只需要打开关闭一次
            ContentValues values = new ContentValues();
            values.put("name", shangJia.name);
            values.put("address", shangJia.address);
            values.put("phone", shangJia.phone);
            db.insert("tb_shangjia", null, values);
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
    
    public List<ShangJia> queryAllData() {
        String sql = "select * from tb_shangjia";
        Cursor cursor = null;
        List<ShangJia> contactList = new ArrayList<ShangJia>();
        try {
            cursor = db.rawQuery(sql, null);
            while (cursor.moveToNext()) {
                ShangJia shangJia = new ShangJia();
                shangJia.name = cursor.getString(cursor.getColumnIndex("name"));
                shangJia.address = cursor.getString(cursor.getColumnIndex("address"));
                shangJia.phone = cursor.getString(cursor.getColumnIndex("phone"));
                contactList.add(shangJia);
            }
            return contactList;
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
