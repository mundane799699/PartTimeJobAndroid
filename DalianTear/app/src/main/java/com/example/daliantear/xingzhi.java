package com.example.daliantear;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class xingzhi extends Activity{
	private Button button;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.xingzhi);
		button =(Button) findViewById(R.id.fanhui);
	}
	public void submit_fan(View v){
	   	 Intent intent =new Intent(xingzhi.this,dengru.class);
	 	   
	 	     startActivity(intent);
	   }

}
