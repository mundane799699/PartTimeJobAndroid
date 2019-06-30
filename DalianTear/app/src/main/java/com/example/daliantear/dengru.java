package com.example.daliantear;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import com.example.daliantear.fuzhuang.FuzhuangListActivity;
import com.example.daliantear.shop.ShopListActivity;

public class dengru extends Activity {
    private Button li;
    private ImageButton xingzhia;
    
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dengru);
        findViewById(R.id.btn_collect_clothes).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                collectClothes();
            }
        });
        findViewById(R.id.btn_collect_shop).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                collectShop();
            }
        });
    }
    
    private void collectShop() {
        startActivity(new Intent(this, ShopListActivity.class));
    }
    
    private void collectClothes() {
        startActivity(new Intent(this, FuzhuangListActivity.class));
    }
    
    public void sumbit_lifu(View v) {
        Intent intent = new Intent(dengru.this, lifu.class);
        startActivity(intent);
    }
    
    public void submit_xing(View v) {
        Intent intent = new Intent(dengru.this, xingzhi.class);
        startActivity(intent);
    }
    
    public void sumbit_rc(View v) {
        Intent intent = new Intent(dengru.this, Richang.class);
        startActivity(intent);
    }
    
    public void sumbit_kh(View v) {
        Intent intent = new Intent(dengru.this, Zixun.class);
        
        startActivity(intent);
    }
}
