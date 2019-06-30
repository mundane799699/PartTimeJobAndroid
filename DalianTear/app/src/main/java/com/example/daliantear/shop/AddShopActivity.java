package com.example.daliantear.shop;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import com.example.daliantear.R;
import com.example.daliantear.bean.Shop;
import com.example.daliantear.dao.ShopDao;

/**
 * ShopListActivity
 *
 * @author fangyuan
 * @date 2019-06-30
 */
public class AddShopActivity extends Activity {
    
    private EditText mEtName;
    private EditText mEtAddress;
    private EditText mEtPhone;
    private ShopDao mDao;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_shop);
        mEtName = (EditText) findViewById(R.id.et_name);
        mEtAddress = (EditText) findViewById(R.id.et_phone);
        mEtPhone = (EditText) findViewById(R.id.et_relation);
        mDao = new ShopDao(this);
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
        Shop data = new Shop();
        data.name = name;
        data.address = address;
        data.phone = phone;
        mDao.add(data);
        finish();
    }
}
