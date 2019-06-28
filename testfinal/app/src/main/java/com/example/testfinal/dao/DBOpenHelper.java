package com.example.testfinal.dao;

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
        
    
        String shangpinSql = "create table tb_shangpin (name text, price text, count text)";
        db.execSQL(shangpinSql);
    
        String shangjiaSql = "create table tb_shangjia (name text, address text, phone text)";
        db.execSQL(shangjiaSql);
    }
    
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    
    }
}
