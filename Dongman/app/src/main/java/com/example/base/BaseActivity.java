package com.example.base;

import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

/**
 * BaseActivity
 *
 * @author fangyuan
 * @date 2019-06-23
 */
public class BaseActivity extends AppCompatActivity {
    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
    
}
