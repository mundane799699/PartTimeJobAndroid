package com.example.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBOpenHelper extends SQLiteOpenHelper {
    
    private static final int VERSION = 1;
    private static final String DBNAME = "account.db";
    
    public DBOpenHelper(Context context) {
        super(context, DBNAME, null, VERSION);
    }
    
    @Override
    public void onCreate(SQLiteDatabase db) {
        
        // status: 0表示未结束, 1表示已经结束
        // todayStatus: 0表示今天未打卡, 1表示今天已打卡
        // lastCardDate表示最后一次打卡该习惯的时间, 默认为空
        // 如果lastCardDate和今天的日期一样, 表示今天已经打卡
        String habitSql = "create table tb_habit (_id integer primary key, name varchar(200), words varchar(200), status integer, cardtimes integer, lastCardDate text)";
        
        db.execSQL(habitSql);
    }
    
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    
    }
}
