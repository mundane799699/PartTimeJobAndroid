package com.example;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import com.example.Utils.SPUtil;
import com.example.base.BaseActivity;
import com.example.manageuser.UserListActivity;

public class MainActivity extends BaseActivity {
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SPUtil.init(this);
        
        findViewById(R.id.btn_manage_dongman).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ChineseDongmanActivity.class);
                startActivity(intent);
            }
        });
        findViewById(R.id.btn_dongmang_daquan).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
            
            }
        });
        findViewById(R.id.btn_manage_user).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                manageUser();
            }
        });
    }
    
    private void manageUser() {
        startActivity(new Intent(this, UserListActivity.class));
    }
    
    private void goToBianjianListActivity() {
        startActivity(new Intent(this, BianJianListActivity.class));
    }
}
