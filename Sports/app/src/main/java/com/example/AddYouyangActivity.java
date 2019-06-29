package com.example;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import com.example.base.BaseActivity;
import com.example.bean.YouyangYundong;
import com.example.dao.YouYangDAO;

/**
 * AddContactActivity
 *
 * @author fangyuan
 * @date 2019-06-25
 */
public class AddYouyangActivity extends BaseActivity {
    
    private EditText mEtName;
    private EditText mEtPhone;
    private EditText mEtRelation;
    private YouYangDAO mDao;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_youyang);
        mEtName = (EditText) findViewById(R.id.et_name);
        mEtPhone = (EditText) findViewById(R.id.et_phone);
        mEtRelation = (EditText) findViewById(R.id.et_relation);
        mDao = new YouYangDAO(this);
        findViewById(R.id.btn_save).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                save();
            }
        });
    }
    
    private void save() {
        String name = mEtName.getText().toString();
        String describe = mEtPhone.getText().toString();
        String effect = mEtRelation.getText().toString();
        YouyangYundong yundong = new YouyangYundong();
        yundong.name = name;
        yundong.describe = describe;
        yundong.effect = effect;
        mDao.add(yundong);
        finish();
    }
}
