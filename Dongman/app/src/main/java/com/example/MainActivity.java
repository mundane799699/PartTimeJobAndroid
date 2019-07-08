package com.example;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import com.example.Utils.SPUtil;
import com.example.base.BaseActivity;
import com.example.manageuser.UserListActivity;

public class MainActivity extends BaseActivity implements OnClickListener {
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SPUtil.init(this);
        
        findViewById(R.id.btn_chinese).setOnClickListener(this);
        findViewById(R.id.btn_foreign).setOnClickListener(this);
        findViewById(R.id.btn_socket).setOnClickListener(this);
        findViewById(R.id.btn_bianjian).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                goToBianjianListActivity();
            
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
    
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_chinese:
                Intent intent1 = new Intent(this, ChineseDongmanActivity.class);
                startActivity(intent1);
                break;
            case R.id.btn_foreign:
                Intent intent2 = new Intent(this, ForeignDongmanActivity.class);
                startActivity(intent2);
                break;
            case R.id.btn_socket:
                startActivity(new Intent(this, SocketActivity.class));
                break;
            default:
                break;
        }
    }
}
