package com.example.lvyou.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.example.lvyou.bean.Scene;
import java.util.ArrayList;
import java.util.List;

public class SceneDAO {
    private DBOpenHelper helper;
    private SQLiteDatabase db;
    
    public SceneDAO(Context context) {
        helper = new DBOpenHelper(context);
        db = helper.getWritableDatabase();
    }
    
    public List<Scene> queryAllScene() {
        String sql = "select * from tb_scene";
        Cursor cursor = null;
        List<Scene> sceneList = new ArrayList<Scene>();
        try {
            cursor = db.rawQuery(sql, null);
            while (cursor.moveToNext()) {
                Scene scene = new Scene();
                scene.name = cursor.getString(cursor.getColumnIndex("name"));
                scene.describe = cursor.getString(cursor.getColumnIndex("describe"));
                scene.drawableName = cursor.getString(cursor.getColumnIndex("drawableName"));
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
    public void add(Scene scene) {
        try {
            db.beginTransaction(); // 以事物的方式插入数据库，这样数据库只需要打开关闭一次
            ContentValues values = new ContentValues();
            values.put("name", scene.name);
            values.put("describe", scene.describe);
            values.put("drawableName", scene.drawableName);
            db.insert("tb_scene", null, values);
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
