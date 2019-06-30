package com.example.daliantear.fuzhuang;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import com.example.daliantear.R;
import com.example.daliantear.bean.FuZhuang;
import com.example.daliantear.dao.FuzhuangDao;

/**
 * FuzhuangListActivity
 *
 * @author fangyuan
 * @date 2019-06-30
 */
public class AddFuzhuangActivity extends Activity {
    
    private EditText mEtName;
    private EditText mEtAddress;
    private EditText mEtPhone;
    private FuzhuangDao mDao;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_fuzhuang);
        mEtName = (EditText) findViewById(R.id.et_name);
        mEtAddress = (EditText) findViewById(R.id.et_phone);
        mEtPhone = (EditText) findViewById(R.id.et_relation);
        mDao = new FuzhuangDao(this);
        findViewById(R.id.btn_save).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                save();
            }
        });
    }
    
    private void save() {
        String name = mEtName.getText().toString();
        String caizhi = mEtAddress.getText().toString();
        String color = mEtPhone.getText().toString();
        FuZhuang data = new FuZhuang();
        data.name = name;
        data.caizhi = caizhi;
        data.color = color;
        mDao.add(data);
        finish();
    }
}
