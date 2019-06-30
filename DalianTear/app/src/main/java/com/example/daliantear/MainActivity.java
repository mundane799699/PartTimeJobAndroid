package com.example.daliantear;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
    private EditText nameedit,passwordedit;
    private CheckBox checkbox1,checkbox2,checkbox3;
    private Button button1,button2,button3;
    private TextView textview1;
    String name,password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nameedit=(EditText) findViewById(R.id.nameedit);
        passwordedit=(EditText) findViewById(R.id.passwordedit);
        textview1=(TextView)findViewById(R.id.textview1);
        checkbox1=(CheckBox) findViewById(R.id.checkbox1);
        checkbox2=(CheckBox) findViewById(R.id.checkbox2);
        button2=(Button) findViewById(R.id.button2);
        
        final DBHepler helper=new DBHepler(MainActivity.this);
        button2.setOnClickListener(new OnClickListener() {
            
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                int ps=Integer.parseInt(passwordedit.getText().toString());
                Log.i("ps", ps+" ");
                if(helper.select(nameedit.getText().toString())==ps){
                    Intent intent=new Intent(MainActivity.this,dengru.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(MainActivity.this, "失败！", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    public void sumbit_dengru1(View v){
        Intent intent =new Intent(MainActivity.this,zhuce.class);
        
        startActivity(intent);
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
}
