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
        
        String userSql = "create table tb_user (_id integer primary key AUTOINCREMENT, user varchar(20),password varchar(20))";
        db.execSQL(userSql);
        
        String secneSql = "create table tb_chinese_dongman (name text, describe text, date text)";
        db.execSQL(secneSql);
    
        String foodSql = "create table tb_foreign_dongman (name text, describe text, date text)";
        db.execSQL(foodSql);
    
        String arcSql = "create table tb_architecture (name text, describe text, drawableName text)";
        db.execSQL(arcSql);
    
        String bianjianSql = "create table tb_bianjian (title text, describe text, date text)";
        db.execSQL(bianjianSql);
    }
    
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    
    }
}
