package com.example.daliantear;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class DBHepler {
	private static final String DATABASE_NAME="mydb";
	private static final int DATABASE_VERSION=1;
	private static final String TABLE_NAME="user";
	private static final String[] COLUMNS= {"nameedit","passwordedit"};
	private static String sql="";
	private DBOpenHelper helper;
	private static SQLiteDatabase db;
	public DBHepler(Context context){
		sql="create table "+TABLE_NAME+" ("+COLUMNS[0]+" varchar(10) primary key," +COLUMNS[1]+" integer );";
		helper=new DBOpenHelper(context, DATABASE_NAME, DATABASE_VERSION, TABLE_NAME, sql);
		db=helper.getWritableDatabase();
		Log.i("sql", sql);
	}
	public void insert(User data){
		ContentValues values=new ContentValues();
		values.put(COLUMNS[0], data.getNameedit());
		values.put(COLUMNS[1], data.getPasswordedit());
		db.insert(TABLE_NAME,null,values);
	}
	public int select(String name){
		int password = 0;
		sql=" select * from "+TABLE_NAME+" where nameedit = ? ";
		Cursor cursor=db.rawQuery(sql,new String[]{name});
		while(true){
			if(cursor.moveToNext()==false)
				break;
			password =cursor.getInt(1);
		}
		return password;
	}
	public ArrayList<User> find(){
		ArrayList<User> list=new ArrayList<User>();
		User user=null;
		Cursor cursor=db.query(TABLE_NAME, COLUMNS, null, null, null, null, null);
		while(cursor.moveToNext()){
			user=new User();
			user.setNameedit(cursor.getString(0));
			user.setPasswordedit(cursor.getInt(1));
			list.add(user);
		}
		cursor.close();
		return list;
		
	}
}
