package com.example.dao;

import java.util.ArrayList;
import java.util.List;

import com.example.model.Note;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class NoteDAO {
    private DBOpenHelper helper;
    private SQLiteDatabase db;
    
    public NoteDAO(Context context) {
        helper = new DBOpenHelper(context);
        db = helper.getWritableDatabase();
    }
    
    //获取编号最大的数
    public int getMaxId() {
        Cursor cursor = db.rawQuery("select max(_id)from tb_flag", null);
        while (cursor.moveToLast()) {
            return cursor.getInt(0);
        }
        cursor.close();
        return 0;
    }
    
    //增
    public void add(Note tb_flag) {
        db.execSQL("insert into tb_flag (_id,flag)values" + "(?,?)", new Object[] { tb_flag._id, tb_flag.flag });
    }
    
    // 显示所有的信息
    public List<Note> getScrollData(int start, int count) {
        List<Note> tb_flag = new ArrayList<>();
        Cursor cursor = db.rawQuery("select * from tb_flag limit ?,?", new String[] { String.valueOf(start), String.valueOf(count) });
        while (cursor.moveToNext()) {
            tb_flag.add(new Note(cursor.getInt(cursor.getColumnIndex("_id")),
                    
                    cursor.getString(cursor.getColumnIndex("flag"))));
        }
        cursor.close();
        return tb_flag;
    }
    
    // 获取总记录数
    public long getCount() {
        Cursor cursor = db.rawQuery("select count(_id) from tb_flag", null);
        if (cursor.moveToNext()) {
            return cursor.getLong(0);
        }
        cursor.close();
        return 0;
    }
    
    public Note find(int id) {
        Cursor cursor = db.rawQuery("select _id,flag from tb_flag where _id=?", new String[] { String.valueOf(id) });
        if (cursor.moveToNext()) {
            return new Note(cursor.getInt(cursor.getColumnIndex("_id")),
                    
                    cursor.getString(cursor.getColumnIndex("flag")));
        }
        cursor.close();
        return null;
    }
    
    //改
    public void update(Note tb_flag) {
        db.execSQL("update tb_flag set flag=?" + "where _id=?", new Object[] { tb_flag.flag, tb_flag._id });
    }
    
    //删
    public void detele(int id) {
        
        db.execSQL("delete from tb_flag where _id=?", new String[] { String.valueOf(id) }); //？？？
    }
}
