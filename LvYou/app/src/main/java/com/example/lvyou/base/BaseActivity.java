package com.example.lvyou.base;

import android.app.Activity;
import android.widget.Toast;

/**
 * BaseActivity
 *
 * @author fangyuan
 * @date 2019-06-23
 */
public class BaseActivity extends Activity {
    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
    
}
