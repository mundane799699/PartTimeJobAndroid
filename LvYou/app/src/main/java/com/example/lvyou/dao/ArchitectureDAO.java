package com.example.lvyou.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.example.lvyou.bean.Architecture;
import java.util.ArrayList;
import java.util.List;

public class ArchitectureDAO {
    private DBOpenHelper helper;
    private SQLiteDatabase db;
    
    public ArchitectureDAO(Context context) {
        helper = new DBOpenHelper(context);
        db = helper.getWritableDatabase();
    }
    
    public List<Architecture> queryAllArchitecture() {
        String sql = "select * from tb_architecture";
        Cursor cursor = null;
        List<Architecture> ArchitectureList = new ArrayList<Architecture>();
        try {
            cursor = db.rawQuery(sql, null);
            while (cursor.moveToNext()) {
                Architecture Architecture = new Architecture();
                Architecture.name = cursor.getString(cursor.getColumnIndex("name"));
                Architecture.describe = cursor.getString(cursor.getColumnIndex("describe"));
                Architecture.drawableName = cursor.getString(cursor.getColumnIndex("drawableName"));
                ArchitectureList.add(Architecture);
            }
            return ArchitectureList;
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
    public void add(Architecture Architecture) {
        try {
            db.beginTransaction(); // 以事物的方式插入数据库，这样数据库只需要打开关闭一次
            ContentValues values = new ContentValues();
            values.put("name", Architecture.name);
            values.put("describe", Architecture.describe);
            values.put("drawableName", Architecture.drawableName);
            db.insert("tb_architecture", null, values);
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
