package com.example;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import com.example.bean.ShangJia;
import com.example.dao.ShangJiaDao;

/**
 * ShangpinListActivity
 *
 * @author fangyuan
 * @date 2019-06-28
 */
public class AddShangjiaActivity extends Activity {
    
    private EditText mEtName;
    private EditText mEtAddress;
    private EditText mEtPhone;
    private ShangJiaDao mDao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_shangjia);
        mEtName = (EditText) findViewById(R.id.et_name);
        mEtAddress = (EditText) findViewById(R.id.et_phone);
        mEtPhone = (EditText) findViewById(R.id.et_relation);
        mDao = new ShangJiaDao(this);
        findViewById(R.id.btn_save).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                save();
            
            }
        });
    }
    
    private void save() {
        String name = mEtName.getText().toString();
        String address = mEtAddress.getText().toString();
        String phone = mEtPhone.getText().toString();
        ShangJia shangJia = new ShangJia();
        shangJia.name = name;
        shangJia.address = address;
        shangJia.phone = phone;
        mDao.add(shangJia);
        finish();
    }
}
