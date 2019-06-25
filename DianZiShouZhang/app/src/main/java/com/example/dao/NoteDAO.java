package com.example.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.example.model.Note;
import java.util.ArrayList;
import java.util.List;

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
    public void add(Note note) {
        try {
            db.beginTransaction(); // 以事物的方式插入数据库，这样数据库只需要打开关闭一次
            ContentValues values = new ContentValues();
            values.put("_id", note._id);
            values.put("flag", note.flag);
            values.put("song_name", note.songName);
            values.put("song_singer", note.songSinger);
            values.put("song_path", note.songPath);
            db.insert("tb_flag", null, values);
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
    
    // 显示所有的信息
    public List<Note> getScrollData() {
        List<Note> noteList = new ArrayList<>();
        String sql = "select * from tb_flag";
        Cursor cursor = db.rawQuery(sql, null);
        while (cursor.moveToNext()) {
            Note note = new Note();
            note._id = cursor.getInt(cursor.getColumnIndex("_id"));
            note.flag = cursor.getString(cursor.getColumnIndex("flag"));
            note.songName = cursor.getString(cursor.getColumnIndex("song_name"));
            note.songSinger = cursor.getString(cursor.getColumnIndex("song_singer"));
            note.songPath = cursor.getString(cursor.getColumnIndex("song_path"));
            noteList.add(note);
        }
        cursor.close();
        return noteList;
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
        Cursor cursor = db.rawQuery("select _id,flag,song_name,song_singer,song_path from tb_flag where _id=?", new String[] { String.valueOf(id) });
        if (cursor.moveToNext()) {
            int noteId = cursor.getInt(cursor.getColumnIndex("_id"));
            String noteFlag = cursor.getString(cursor.getColumnIndex("flag"));
            String songName = cursor.getString(cursor.getColumnIndex("song_name"));
            String songSinger = cursor.getString(cursor.getColumnIndex("song_singer"));
            String songPath = cursor.getString(cursor.getColumnIndex("song_path"));
            return new Note(noteId, noteFlag, songName, songSinger, songPath);
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
