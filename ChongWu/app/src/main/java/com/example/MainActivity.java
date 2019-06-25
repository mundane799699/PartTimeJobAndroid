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
        setContentView(R.layout.activity_strategy);
        SPUtil.init(this);
        
        findViewById(R.id.btn_dog).setOnClickListener(this);
        findViewById(R.id.btn_cat).setOnClickListener(this);
        findViewById(R.id.btn_socket).setOnClickListener(this);
        findViewById(R.id.btn_collect).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                goToSongListActivity();
        
            }
        });
    }
    
    private void goToSongListActivity() {
        startActivity(new Intent(this, SongListActivity.class));
    }
    
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_dog:
                Intent intent1 = new Intent(this, DogActivity.class);
                startActivity(intent1);
                break;
            case R.id.btn_cat:
                Intent intent2 = new Intent(this, CatActivity.class);
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
