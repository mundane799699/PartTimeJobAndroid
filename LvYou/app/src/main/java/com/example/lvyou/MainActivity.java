package com.example.lvyou;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import com.example.lvyou.Utils.SPUtil;

public class MainActivity extends Activity implements OnClickListener {
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_strategy);
        SPUtil.init(this);
        
        findViewById(R.id.iv_scenic).setOnClickListener(this);
        findViewById(R.id.iv_delicious).setOnClickListener(this);
        findViewById(R.id.iv_architecture).setOnClickListener(this);
        findViewById(R.id.iv_socket).setOnClickListener(this);
        findViewById(R.id.iv_contact).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, ContactListActivity.class));
            }
        });
    }
    
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_scenic:
                Intent intent1 = new Intent(this, ScenicActivity.class);
                startActivity(intent1);
                break;
            case R.id.iv_delicious:
                Intent intent2 = new Intent(this, DeliciousActivity.class);
                startActivity(intent2);
                break;
            case R.id.iv_architecture:
                Intent intent3 = new Intent(this, ArchitectureActivity.class);
                startActivity(intent3);
                break;
            case R.id.iv_socket:
                startActivity(new Intent(this, SocketActivity.class));
                break;
            default:
                break;
        }
    }
}
