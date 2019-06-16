package io.github.laucherish.purezhihud.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import io.github.laucherish.purezhihud.App;
import io.github.laucherish.purezhihud.bean.User;
import java.util.ArrayList;
import java.util.List;

/**
 * LoginActivity
 *
 * @author fangyuan
 * @date 2019/6/15
 */
public class DbUtils {
    
    public static void saveUser(User user) {
        Context context = App.getContext();
        if (context == null) {
            return;
        }
        SqliteDBHelper dbHelper = null;
        SQLiteDatabase db = null;
        try {
            dbHelper = new SqliteDBHelper(context);
            db = dbHelper.getWritableDatabase();
            db.beginTransaction(); // 以事物的方式插入数据库，这样数据库只需要打开关闭一次
            ContentValues values = new ContentValues();
            values.put("name", user.name);
            values.put("pwd", user.pwd);
            db.insert("users", null, values);
            db.setTransactionSuccessful(); // 事物成功， 一次写入数据库， 这一句真正到数据库里
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                db.endTransaction();
                db.close();
                dbHelper.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
    
    /**
     * 删除表
     */
    public static void dropUserTable() {
        Context context = App.getContext();
        if (context == null) {
            return;
        }
        SqliteDBHelper dbHelper = null;
        SQLiteDatabase db = null;
        String sql = "delete from users";
        try {
            dbHelper = new SqliteDBHelper(context);
            db = dbHelper.getWritableDatabase();
            db.execSQL(sql);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                db.close();
                dbHelper.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
    public static List<User> queryAllUsers() {
        Context context = App.getContext();
        if (context == null) {
            return null;
        }
        SqliteDBHelper dbHelper = null;
        String sql = "select * from users";
        SQLiteDatabase db = null;
        Cursor cursor = null;
        List<User> userList = new ArrayList<>();
        try {
            dbHelper = new SqliteDBHelper(context);
            db = dbHelper.getReadableDatabase();
            cursor = db.rawQuery(sql, null);
            while (cursor.moveToNext()) {
                User user = new User();
                user.name = cursor.getString(cursor.getColumnIndex("name"));
                user.pwd = cursor.getString(cursor.getColumnIndex("pwd"));
                userList.add(user);
            }
            return userList;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                cursor.close();
                db.close();
                dbHelper.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
