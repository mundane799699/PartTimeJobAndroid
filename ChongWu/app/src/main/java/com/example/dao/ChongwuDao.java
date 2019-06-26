package com.example.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.example.bean.Chongwu;
import java.util.ArrayList;
import java.util.List;

/**
 * SongDao
 *
 * @author fangyuan
 * @date 2019-06-25
 */
public class ChongwuDao {
    
    private DBOpenHelper helper;
    private SQLiteDatabase db;
    
    public ChongwuDao(Context context) {
        helper = new DBOpenHelper(context);
        db = helper.getWritableDatabase();
    }
    
    // 增
    public void add(Chongwu song) {
        try {
            db.beginTransaction(); // 以事物的方式插入数据库，这样数据库只需要打开关闭一次
            ContentValues values = new ContentValues();
            values.put("name", song.name);
            values.put("type", song.type);
            values.put("size", song.size);
            db.insert("tb_chongwu", null, values);
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
    
    public List<Chongwu> queryAllSongs() {
        String sql = "select * from tb_chongwu";
        Cursor cursor = null;
        List<Chongwu> list = new ArrayList<Chongwu>();
        try {
            cursor = db.rawQuery(sql, null);
            while (cursor.moveToNext()) {
                Chongwu contact = new Chongwu();
                contact.name = cursor.getString(cursor.getColumnIndex("name"));
                contact.type = cursor.getString(cursor.getColumnIndex("type"));
                contact.size = cursor.getString(cursor.getColumnIndex("size"));
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
