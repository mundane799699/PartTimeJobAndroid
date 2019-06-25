package com.example;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import com.example.base.BaseActivity;
import com.example.bean.BianJian;
import com.example.dao.BianjianDao;

/**
 * AddBianjianActivity
 *
 * @author fangyuan
 * @date 2019-06-25
 */
public class AddBianjianActivity extends BaseActivity {
    
    private EditText mEtName;
    private EditText mEtPhone;
    private EditText mEtRelation;
    private BianjianDao mDao;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_bianjian);
        mEtName = (EditText) findViewById(R.id.et_name);
        mEtPhone = (EditText) findViewById(R.id.et_phone);
        mEtRelation = (EditText) findViewById(R.id.et_relation);
        mDao = new BianjianDao(this);
        findViewById(R.id.btn_save).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                save();
            }
        });
    }
    
    private void save() {
        String name = mEtName.getText().toString();
        String phone = mEtPhone.getText().toString();
        String relation = mEtRelation.getText().toString();
        if (TextUtils.isEmpty(name) || TextUtils.isEmpty(phone) || TextUtils.isEmpty(relation)) {
            showMessage(getString(R.string.parameter_empty));
            return;
        }
        BianJian bian = new BianJian();
        bian.title = name;
        bian.describe = phone;
        bian.date = relation;
        mDao.add(bian);
        finish();
    }
}
