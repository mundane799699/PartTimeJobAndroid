package com.example.testfinal;

import java.io.File;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;



public class register extends Activity {
	private EditText name_edit2,pass_edit2,number_edit2;
	private Button zc2_button;
	private int year,month,day;
	private TextView datetext;
	private String datestring;
	private Spinner sp2,sp1;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.register);
		name_edit2=(EditText) findViewById(R.id.name_edit2);
		pass_edit2=(EditText) findViewById(R.id.pass_edit2);
		number_edit2=(EditText) findViewById(R.id.number_edit2);
		zc2_button=(Button) findViewById(R.id.zc2_button);
		datetext=(TextView) findViewById(R.id.datetext);
		sp1=(Spinner) findViewById(R.id.sp1);
		sp2=(Spinner) findViewById(R.id.sp2);
		final SQLiteDatabase db=SQLiteDatabase.openOrCreateDatabase(this.getFilesDir().toString()+"/atu.db3", null);
		
		try{
			db.execSQL("create table user(name integer,pass varchar(50),tel intege)");
		}catch(Exception e){
			e.printStackTrace();
		}
		sp2.setOnItemSelectedListener(new OnItemSelectedListener() {
			
			
			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				// TODO Auto-generated method stub
				
			}
			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
		});
	}
	
	public void zc2(View v){
		final SQLiteDatabase db=SQLiteDatabase.openOrCreateDatabase(this.getFilesDir().toString()+"/atu.db3", null);
		if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
			File sdCardDir=Environment.getExternalStorageDirectory();
			String n=name_edit2.getText().toString();
			String p=pass_edit2.getText().toString();
			String t=number_edit2.getText().toString();
			if(n.equals("")){
				Toast.makeText(register.this, "您的昵称为空", Toast.LENGTH_SHORT).show();
			}else if(p.equals("")){
				Toast.makeText(register.this, "您的密码为空", Toast.LENGTH_SHORT).show();
			}else if(t.equals("")){
				Toast.makeText(register.this, "您的电话号为空", Toast.LENGTH_SHORT).show();
			}else{
				final AlertDialog.Builder adBuilder=new AlertDialog.Builder(this);
				adBuilder.setMessage("确认保存记录吗？").setPositiveButton("确认", new DialogInterface.OnClickListener(){
					public void onClick(DialogInterface dialog,int which){
						if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
							File sdCardDir=Environment.getExternalStorageDirectory();
							String n=name_edit2.getText().toString();
							String p=pass_edit2.getText().toString();
							String t=number_edit2.getText().toString();
							Bundle bundle=new Bundle();	//创建bundle对象
							bundle.putString("name", n);
							db.execSQL("insert into user values(?,?,?)",new String[]{n,p,t});
							
							Toast.makeText(register.this,"保存成功！", Toast.LENGTH_SHORT).show();
							Intent intent=getIntent();
							setResult(0x111,intent);
							register.this.finish();
							intent=new Intent(register.this,login.class);
							startActivity(intent);
						}
					}
					
					
				}).setNegativeButton("取消", new DialogInterface.OnClickListener() {
					
					public void onClick(DialogInterface dialog, int which) {}
				});
				AlertDialog alertDialog=adBuilder.create();
				alertDialog.show();
			}
		}
	}
	
	public void date_select(View v){
		int intyear=2000;
		int intmonth=0;
		int intday=1;
		new DatePickerDialog(this,day, new DatePickerDialog.OnDateSetListener() {
			
			@Override
			public void onDateSet(DatePicker arg0, int arg1, int arg2, int arg3) {
				// TODO Auto-generated method stub
				year=arg1;
				month=arg2;
				day=arg3;
				datestring=year+"-"+(month+1)+"-"+day;
				datetext.setText(datestring);
			}
		}, intyear, intmonth, intday).show();
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	
	
}
