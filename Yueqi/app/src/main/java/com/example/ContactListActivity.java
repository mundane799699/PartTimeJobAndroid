package com.example;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ListView;
import com.example.adapter.ContactAdapter;
import com.example.base.BaseActivity;
import com.example.bean.Contact;
import com.example.dao.ContactDao;
import com.example.xstrategy.R;
import java.util.ArrayList;
import java.util.List;

public class ContactListActivity extends BaseActivity {
    
    private ListView mLv;
    private List<Contact> mList = new ArrayList<Contact>();
    private ContactAdapter mContactAdapter;
    private ContactDao mContactDao;
    private View mBtnAdd;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_list);
        mContactDao = new ContactDao(this);
        mLv = (ListView) findViewById(R.id.lv);
        mBtnAdd = findViewById(R.id.btn_add);
        mBtnAdd.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                goToAddContactActivity();
            }
        });
        mContactAdapter = new ContactAdapter(this, mList);
        mLv.setAdapter(mContactAdapter);
    }
    
    @Override
    protected void onResume() {
        super.onResume();
        queryAllContacts();
    }
    
    private void queryAllContacts() {
        List<Contact> list = mContactDao.queryAllUsers();
        if (list != null && !list.isEmpty()) {
            mList.clear();
            mList.addAll(list);
        }
        mContactAdapter.notifyDataSetChanged();
    }
    
    private void goToAddContactActivity() {
        startActivity(new Intent(this, AddContactActivity.class));
    }
}
