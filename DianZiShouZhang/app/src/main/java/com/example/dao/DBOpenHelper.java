package com.example.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
public class DBOpenHelper extends SQLiteOpenHelper {

	private static final int VERSION=1;
	private static final String DBNAME="account.db";
	public DBOpenHelper(Context context) {
		super(context,DBNAME,null,VERSION);
	}
	@Override
	public void onCreate(SQLiteDatabase db) {
		
		String userSql = "create table tb_user (user varchar(20),password varchar(20))";
		db.execSQL(userSql);
		String flagSql = "create table tb_flag (_id integer primary key, flag text, song_name text, song_singer text, song_path text)";
		db.execSQL(flagSql);
	}
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		
	}

}
