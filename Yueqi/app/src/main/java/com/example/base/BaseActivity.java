package com.example.base;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Toast;
import com.example.xstrategy.R;

/**
 * BaseActivity
 *
 * @author fangyuan
 * @date 2019-06-23
 */
public class BaseActivity extends Activity {
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
    }
    
    @Override
    protected void onResume() {
        super.onResume();
        View ivClose = findViewById(R.id.iv_close);
        if (ivClose != null) {
            ivClose.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    finish();
                }
            });
        }
    }
    
    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
