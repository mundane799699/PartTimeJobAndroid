package com.example;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import com.example.base.BaseActivity;
import com.example.bean.GuowaiDongman;
import com.example.dao.ForeignDongmanDAO;

/**
 * AddBianjianActivity
 *
 * @author fangyuan
 * @date 2019-06-25
 */
public class AddForeignDongmanActivity extends BaseActivity {
    
    private EditText mEtName;
    private EditText mEtPhone;
    private EditText mEtRelation;
    private ForeignDongmanDAO mDao;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_foreign);
        mEtName = (EditText) findViewById(R.id.et_name);
        mEtPhone = (EditText) findViewById(R.id.et_phone);
        mEtRelation = (EditText) findViewById(R.id.et_relation);
        mDao = new ForeignDongmanDAO(this);
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
        String date = mEtRelation.getText().toString();
        GuowaiDongman dongman = new GuowaiDongman();
        dongman.name = name;
        dongman.describe = describe;
        dongman.date = date;
        mDao.add(dongman);
        finish();
    }
}
