package com.example;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.Utils.MResource;

public class DetailActivity extends Activity {
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content);
        
        Intent intent = getIntent();
        String image = intent.getStringExtra("image");
        String title = intent.getStringExtra("title");
        String content = intent.getStringExtra("content");
        
        ((ImageView) findViewById(R.id.content_iv)).setImageResource(MResource.getIdByDrawableName(this, image));
        ((TextView) findViewById(R.id.content_tv_c)).setText(content);
    }
}
