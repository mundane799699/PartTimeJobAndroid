package io.github.laucherish.purezhihud.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SqliteDBHelper extends SQLiteOpenHelper {

    public SqliteDBHelper(Context context) {
        /**
         * 第二个参数：数据库名字
         * 第三个参数：数据库版本
         */
        super(context, "zhihudaily.db", null, 1);
    }

    // 主要创建表用
    @Override
    public void onCreate(SQLiteDatabase db) {
        // create table if not exists users (_id integer primary key not null, name text, pwd text)
        String sql = "create table if not exists users (_id integer primary key not null, name text, pwd text)";
        db.execSQL(sql);
    }

    // 数据库升级用， 当数据库版本加1的时候onUpgrade()就会走一遍
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
