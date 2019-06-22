package com.example.myapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.example.base.BaseActivity;
import com.example.service.MyService;

public class MusicActivity extends BaseActivity {
    
    private Button mBtnPlay;
    // 表示音乐是否正在播放
    private boolean status;
    Intent intent;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music);
        
        intent = new Intent(this, MyService.class);
        mBtnPlay = findViewById(R.id.btn_open);
        mBtnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!status) {
                    mBtnPlay.setText("停止");
                    startService(intent);//调用onCreate的方法
                    status = true;
                } else {
                    mBtnPlay.setText("播放");
                    stopService(intent);//调用onDestory方法
                    status = false;
                }
            }
        });
    }
}
