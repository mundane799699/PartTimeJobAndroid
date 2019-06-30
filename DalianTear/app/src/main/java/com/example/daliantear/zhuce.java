package com.example.daliantear;


import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class zhuce extends Activity{
	private EditText nameedit,passwordedit;
	private RadioGroup radiogroup;
	private String datestring;
	private TextView dateText;
	private CheckBox checkbox1,checkbox2;
	private AutoCompleteTextView hobbytext;
	private Button button1,button2,button3,button4;
	private TextView textview1;
	private String[] hobbys={
			"han 礼服",
			"han 日常",
			"han 汉元素",
			"han 配饰",
			"han 魏晋",
			"han 先秦",
			"han 唐宋"
	};
	private int year;
	private int month;
	private int day;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.zhuce);
		nameedit=(EditText) findViewById(R.id.nameedit);
		passwordedit=(EditText) findViewById(R.id.passwordedit);
		radiogroup=(RadioGroup) findViewById(R.id.sex);
		dateText=(TextView) findViewById(R.id.date);
		checkbox1=(CheckBox) findViewById(R.id.checkbox1);
		checkbox2=(CheckBox) findViewById(R.id.checkbox2);
		hobbytext=(AutoCompleteTextView) findViewById(R.id.hobbytext);
		ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1,
				hobbys);
		button4 = (Button) findViewById(R.id.button4);
		hobbytext.setAdapter(adapter);
		button4.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				User info=null;
				DBHepler helper=new DBHepler(zhuce.this);
				info=new User();
				info.setNameedit(nameedit.getText().toString());
				info.setPasswordedit(Integer.parseInt(passwordedit.getText().toString()));
				helper.insert(info);
				nameedit.setText("");
				passwordedit.setText("");
				
				Intent intent =new Intent(zhuce.this,MainActivity.class);
				startActivity(intent);
				Toast.makeText(zhuce.this, "保存成功", Toast.LENGTH_SHORT).show();
			}
		});
	}
	public void date_select(View v){
		int intyear=2000;
		int intmonth=0;
		int intday=1;
		new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
			
			@Override
			public void onDateSet(DatePicker arg0, int arg1, int arg2, int arg3) {
				// TODO Auto-generated method stub
				year=arg1;
				month=arg2;
				day=arg3;
				datestring=year+"-"+(month+1)+"-"+day;
				dateText.setText(datestring);
			}
		}, intyear, intmonth, intday).show();
	}
}
