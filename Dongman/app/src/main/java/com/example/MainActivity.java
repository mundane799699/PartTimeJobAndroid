package com.example;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import com.example.Utils.SPUtil;

public class MainActivity extends Activity implements OnClickListener {
    
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
