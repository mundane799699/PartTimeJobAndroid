package com.example.daliantear.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.example.daliantear.bean.Shop;
import java.util.ArrayList;
import java.util.List;

/**
 * DataDao
 *
 * @author fangyuan
 * @date 2019-06-30
 */
public class ShopDao {
    private DBOpenHelper helper;
    private SQLiteDatabase db;
    
    public ShopDao(Context context) {
        helper = new DBOpenHelper(context);
        db = helper.getWritableDatabase();
    }
    
    public List<Shop> queryAll() {
        String sql = "select * from tb_shop";
        Cursor cursor = null;
        List<Shop> list = new ArrayList<Shop>();
        try {
            cursor = db.rawQuery(sql, null);
            while (cursor.moveToNext()) {
                Shop shop = new Shop();
                shop.name = cursor.getString(cursor.getColumnIndex("name"));
                shop.address = cursor.getString(cursor.getColumnIndex("address"));
                shop.phone = cursor.getString(cursor.getColumnIndex("phone"));
                list.add(shop);
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
    public void add(Shop shop) {
        try {
            db.beginTransaction(); // 以事物的方式插入数据库，这样数据库只需要打开关闭一次
            ContentValues values = new ContentValues();
            values.put("name", shop.name);
            values.put("address", shop.address);
            values.put("phone", shop.phone);
            db.insert("tb_shop", null, values);
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
