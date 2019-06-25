package com.example.lvyou;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import com.example.lvyou.base.BaseActivity;
import com.example.lvyou.bean.Contact;
import com.example.lvyou.dao.ContactDao;

/**
 * AddContactActivity
 *
 * @author fangyuan
 * @date 2019-06-25
 */
public class AddContactActivity extends BaseActivity {
    
    private EditText mEtName;
    private EditText mEtPhone;
    private EditText mEtRelation;
    private ContactDao mContactDao;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);
        mEtName = (EditText) findViewById(R.id.et_name);
        mEtPhone = (EditText) findViewById(R.id.et_phone);
        mEtRelation = (EditText) findViewById(R.id.et_relation);
        mContactDao = new ContactDao(this);
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
        Contact contact = new Contact();
        contact.name = name;
        contact.phone = phone;
        contact.relation = relation;
        mContactDao.add(contact);
        finish();
    }
}
