package com.example.testfinal.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.example.testfinal.bean.ShangPin;
import java.util.ArrayList;
import java.util.List;

/**
 * ShangPinDao
 *
 * @author fangyuan
 * @date 2019-06-28
 */
public class ShangPinDao {
    private DBOpenHelper helper;
    private SQLiteDatabase db;
    
    public ShangPinDao(Context context) {
        helper = new DBOpenHelper(context);
        db = helper.getWritableDatabase();
    }
    
    // 增
    public void add(ShangPin shangPin) {
        try {
            db.beginTransaction(); // 以事物的方式插入数据库，这样数据库只需要打开关闭一次
            ContentValues values = new ContentValues();
            values.put("name", shangPin.name);
            values.put("price", shangPin.price);
            values.put("count", shangPin.count);
            db.insert("tb_shangpin", null, values);
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
    
    public List<ShangPin> queryAllData() {
        String sql = "select * from tb_shangpin";
        Cursor cursor = null;
        List<ShangPin> contactList = new ArrayList<ShangPin>();
        try {
            cursor = db.rawQuery(sql, null);
            while (cursor.moveToNext()) {
                ShangPin shangPin = new ShangPin();
                shangPin.name = cursor.getString(cursor.getColumnIndex("name"));
                shangPin.price = cursor.getString(cursor.getColumnIndex("price"));
                shangPin.count = cursor.getString(cursor.getColumnIndex("count"));
                contactList.add(shangPin);
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
