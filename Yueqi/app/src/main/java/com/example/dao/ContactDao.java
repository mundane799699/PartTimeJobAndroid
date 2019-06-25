package com.example.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.example.bean.Contact;
import java.util.ArrayList;
import java.util.List;

/**
 * ContactDao
 *
 * @author fangyuan
 * @date 2019-06-25
 */
public class ContactDao {
    
    private DBOpenHelper helper;
    private SQLiteDatabase db;
    
    public ContactDao(Context context) {
        helper = new DBOpenHelper(context);
        db = helper.getWritableDatabase();
    }
    
    // 增
    public void add(Contact contact) {
        try {
            db.beginTransaction(); // 以事物的方式插入数据库，这样数据库只需要打开关闭一次
            ContentValues values = new ContentValues();
            values.put("name", contact.name);
            values.put("phone", contact.phone);
            values.put("relation", contact.relation);
            db.insert("tb_contact", null, values);
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
    
    public List<Contact> queryAllUsers() {
        String sql = "select * from tb_contact";
        Cursor cursor = null;
        List<Contact> contactList = new ArrayList<Contact>();
        try {
            cursor = db.rawQuery(sql, null);
            while (cursor.moveToNext()) {
                Contact contact = new Contact();
                contact.name = cursor.getString(cursor.getColumnIndex("name"));
                contact.phone = cursor.getString(cursor.getColumnIndex("phone"));
                contact.relation = cursor.getString(cursor.getColumnIndex("relation"));
                contactList.add(contact);
            }
            return contactList;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                cursor.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
