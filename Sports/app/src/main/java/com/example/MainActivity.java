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
        
        findViewById(R.id.btn_youyang).setOnClickListener(this);
        findViewById(R.id.btn_wuyang).setOnClickListener(this);
        findViewById(R.id.btn_socket).setOnClickListener(this);
        findViewById(R.id.btn_contacts).setOnClickListener(this);
    }
    
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_youyang:
                Intent intent1 = new Intent(this, YouyangActivity.class);
                startActivity(intent1);
                break;
            case R.id.btn_wuyang:
                Intent intent2 = new Intent(this, WuyangActivity.class);
                startActivity(intent2);
                break;
            case R.id.btn_socket:
                startActivity(new Intent(this, SocketActivity.class));
                break;
            case R.id.btn_contacts:
                startActivity(new Intent(this, ContactListActivity.class));
                break;
            default:
                break;
        }
    }
}
