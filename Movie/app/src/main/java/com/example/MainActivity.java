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
        
        findViewById(R.id.btn_action).setOnClickListener(this);
        findViewById(R.id.btn_love).setOnClickListener(this);
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
            case R.id.btn_action:
                Intent intent1 = new Intent(this, ActionMovieActivity.class);
                startActivity(intent1);
                break;
            case R.id.btn_love:
                Intent intent2 = new Intent(this, LoveMovieActivity.class);
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
