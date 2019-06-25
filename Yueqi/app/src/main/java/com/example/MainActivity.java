package com.example;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import com.example.Utils.SPUtil;
import com.example.xstrategy.R;

public class MainActivity extends Activity implements OnClickListener {
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SPUtil.init(this);
        
        findViewById(R.id.btn_tanzou).setOnClickListener(this);
        findViewById(R.id.btn_chuiguan).setOnClickListener(this);
        findViewById(R.id.btn_socket).setOnClickListener(this);
    }
    
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_tanzou:
                Intent intent1 = new Intent(this, TanZouYueQiActivity.class);
                startActivity(intent1);
                break;
            case R.id.btn_chuiguan:
                Intent intent2 = new Intent(this, ChuiGuanYueQiActivity.class);
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
