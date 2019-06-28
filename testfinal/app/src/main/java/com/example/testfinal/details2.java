package com.example.testfinal;

import android.view.View.OnClickListener;
import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

public class details2 extends Activity {
    private TextView tetle, content, t1;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.details2);
    
        //requestData();
    
        findViewById(R.id.btn_buy).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                ToShangpinListActivity();
        
            }
        });
        
        findViewById(R.id.btn_add_shangjia).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
    
                ToShangjiaListActivity();
            }
        });
    }
    
    private void ToShangjiaListActivity() {
        startActivity(new Intent(this, ShangjiaListActivity.class));
    }
    
    private void requestData() {
        HttpGet request = new HttpGet("http://10.0.2.2:8080/android/json");
        try {
            HttpResponse response = new DefaultHttpClient().execute(request);
            if (response.getStatusLine().getStatusCode() == 200) {
                String msg = EntityUtils.toString(response.getEntity(), "gb2312");
                Log.i("json", msg);
                JSONArray array;
                String t = "", c = "";
                tetle = (TextView) findViewById(R.id.tetle);
                content = (TextView) findViewById(R.id.content);
                try {
                    array = new JSONArray(msg);
                    for (int i = 0; i < array.length(); i++) {
                        JSONObject o = (JSONObject) array.get(i);
                        t = t + o.getString("title") + "";
                        c = t + o.getString("content") + "";
                        tetle.setText(t);
                        content.setText(c);
                    }
                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        } catch (ClientProtocolException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    private void ToShangpinListActivity() {
        startActivity(new Intent(this, ShangpinListActivity.class));
    }
    
    public void ask(View v) {
        Intent intent = new Intent(details2.this, talk.class);
        startActivity(intent);
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
}
