package com.example.testfinal;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import com.example.testfinal.bean.ShangPin;
import com.example.testfinal.dao.ShangPinDao;

/**
 * ShangpinListActivity
 *
 * @author fangyuan
 * @date 2019-06-28
 */
public class AddShangpinActivity extends Activity {
    
    private EditText mEtName;
    private EditText mEtPrice;
    private EditText mEtCount;
    private ShangPinDao mDao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_shangpin);
        mEtName = (EditText) findViewById(R.id.et_name);
        mEtPrice = (EditText) findViewById(R.id.et_phone);
        mEtCount = (EditText) findViewById(R.id.et_relation);
        mDao = new ShangPinDao(this);
        findViewById(R.id.btn_save).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                save();
            
            }
        });
    }
    
    private void save() {
        String name = mEtName.getText().toString();
        String price = mEtPrice.getText().toString();
        String count = mEtCount.getText().toString();
        ShangPin shangPin = new ShangPin();
        shangPin.name = name;
        shangPin.price = price;
        shangPin.count = count;
        mDao.add(shangPin);
        finish();
    }
}
