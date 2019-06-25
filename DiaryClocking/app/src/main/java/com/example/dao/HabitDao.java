package com.example.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.example.model.Habit;
import java.util.ArrayList;
import java.util.List;

/**
 * HabitDao
 *
 * @author fangyuan
 * @date 2019-06-24
 */
public class HabitDao {
    private DBOpenHelper helper;
    private SQLiteDatabase db;
    
    public HabitDao(Context context) {
        helper = new DBOpenHelper(context);
        db = helper.getWritableDatabase();
    }
    
    public void updateHabit(Habit habit) {
        updateCardTimes(habit);
        updateTodayStatus(habit);
        updateStatus(habit);
        updateName(habit);
        updateWords(habit);
    }
    
    private void updateWords(Habit habit) {
        String sql = "update tb_habit set words=? where _id =?";
        db.execSQL(sql, new Object[] { habit.words, habit._id });
    }
    
    private void updateName(Habit habit) {
        db.execSQL("update tb_habit set name=?where _id=?", new Object[] { habit.name, habit._id });
    }
    
    // 改(主要是修改次数)
    private void updateCardTimes(Habit habit) {
        db.execSQL("update tb_habit set cardtimes=?where _id=?", new Object[] { habit.cardtimes, habit._id });
    }
    
    // 改(主要是修改状态)
    private void updateStatus(Habit habit) {
        db.execSQL("update tb_habit set status=?where _id=?", new Object[] { habit.status, habit._id });
    }
    
    // 修改今天是否打卡
    private void updateTodayStatus(Habit habit) {
        db.execSQL("update tb_habit set todayStatus=?where _id=?", new Object[] { habit.todayStatus, habit._id });
    }
    
    // 查询所有
    public List<Habit> queryAllHabits() {
        String sql = "select * from tb_habit";
        Cursor cursor = null;
        List<Habit> list = new ArrayList<>();
        try {
            cursor = db.rawQuery(sql, null);
            while (cursor.moveToNext()) {
                Habit habit = new Habit();
                habit._id = cursor.getInt(cursor.getColumnIndex("_id"));
                habit.name = cursor.getString(cursor.getColumnIndex("name"));
                habit.words = cursor.getString(cursor.getColumnIndex("words"));
                habit.status = cursor.getInt(cursor.getColumnIndex("status"));
                habit.cardtimes = cursor.getInt(cursor.getColumnIndex("cardtimes"));
                habit.todayStatus = cursor.getInt(cursor.getColumnIndex("todayStatus"));
                list.add(habit);
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
    
    //增
    public void add(Habit habit) {
        try {
            db.beginTransaction(); // 以事物的方式插入数据库，这样数据库只需要打开关闭一次
            ContentValues values = new ContentValues();
            values.put("_id", getMaxId() + 1);
            values.put("name", habit.name);
            values.put("words", habit.words);
            values.put("status", habit.status);
            values.put("cardtimes", habit.cardtimes);
            db.insert("tb_habit", null, values);
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
    
    // 获取编号最大的数
    public int getMaxId() {
        Cursor cursor = db.rawQuery("select max(_id)from tb_habit", null);
        while (cursor.moveToLast()) {
            return cursor.getInt(0);
        }
        cursor.close();
        return 0;
    }
    
    //删
    public void detele(Habit habit) {
        int id = habit._id;
        db.execSQL("delete from tb_habit where _id=?", new String[] { String.valueOf(id) });
    }
}
