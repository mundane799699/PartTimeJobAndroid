package com.example.testfinal;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class login extends Activity {
    private SQLiteDatabase db;
    private Button dl_button, zc_button;
    private EditText name_edit1, pass_edit1;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        dl_button = (Button) findViewById(R.id.dl_button);
        zc_button = (Button) findViewById(R.id.zc_button);
        name_edit1 = (EditText) findViewById(R.id.name_edit1);
        pass_edit1 = (EditText) findViewById(R.id.pass_edit1);
        final SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase(this.getFilesDir().toString() + "/atu.db", null);
    }
    
    public void zc(View v) {
        Intent intent = new Intent(login.this, register.class);
        startActivity(intent);
    }
    
    public void dl(View v) {
        String name1 = name_edit1.getText().toString();
        String pass1 = pass_edit1.getText().toString();
        final SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase(this.getFilesDir().toString() + "/atu.db3", null);
        Cursor cursor = db.rawQuery("select * from user where name='" + name1 + "'", null);
        String n = "", p = "", t = "";
        while (true) {
            if (cursor.moveToNext() == false) break;
            n = cursor.getString(0);
            p = cursor.getString(1);
            t = cursor.getString(2);
            Log.i("n", n);
        }
        Log.i("n2", name1);
        if (name1.equals("")) {
            Toast.makeText(login.this, "用户名为空", Toast.LENGTH_SHORT).show();
        } else if (pass1.equals("")) {
            Toast.makeText(login.this, "密码为空", Toast.LENGTH_SHORT).show();
        } else {
        
        }
        if (name1.equals(n) && pass1.equals(p)) {
            Intent intent = new Intent(login.this, shop.class);
            startActivity(intent);
        } else {
            Toast.makeText(login.this, "您的用户名或密码错误，请重试", Toast.LENGTH_SHORT).show();
        }
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
}
