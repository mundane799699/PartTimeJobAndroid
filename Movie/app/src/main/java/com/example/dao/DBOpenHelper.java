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
        
        String userSql = "create table tb_user (user varchar(20),password varchar(20))";
        db.execSQL(userSql);
        
        String actionMovieSql = "create table tb_action_movie (name text, describe text, date text)";
        db.execSQL(actionMovieSql);
    
        String loveMovieSql = "create table tb_love_movie (name text, describe text, date text)";
        db.execSQL(loveMovieSql);
    
        String bianjianSql = "create table tb_bianjian (title text, describe text, date text)";
        db.execSQL(bianjianSql);
    }
    
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    
    }
}
