package com.example.daliantear;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

public class Richang extends Activity {
    private TextView urltext1;
    private Button fanhui;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.richang);
        Button button = (Button) findViewById(R.id.fanhui);
        TextView urltext1 = (TextView) findViewById(R.id.urltext1);
        //requestData(urltext1);
    }
    
    private void requestData(TextView urltext1) {
        try {
            HttpGet request = new HttpGet("http://10.0.2.2:8080/WebText/Hanfu");
            HttpResponse response = new DefaultHttpClient().execute(request);
            if (response.getStatusLine().getStatusCode() == 200) {
                String msg = EntityUtils.toString(response.getEntity(), "gb2312");
                JSONArray array = new JSONArray(msg);
                Log.i("log", msg);
                String s = "  ";
                for (int i = 0; i < array.length(); i++) {
                    JSONObject o = (JSONObject) array.get(i);
                    s = s + o.getString("name") + ": " + o.getString("xinxi") + " ";
                }
                urltext1.setText(s);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void submit_f(View v) {
        Intent intent = new Intent(Richang.this, dengru.class);
        
        startActivity(intent);
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
}
