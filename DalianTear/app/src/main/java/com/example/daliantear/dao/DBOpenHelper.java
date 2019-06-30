package com.example.daliantear.dao;

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
        
    
        String fuzhuangSql = "create table tb_fuzhuang (name text, caizhi text, color text)";
        db.execSQL(fuzhuangSql);
    
        String shopSql = "create table tb_shop (name text, address text, phone text)";
        db.execSQL(shopSql);
    }
    
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    
    }
}
