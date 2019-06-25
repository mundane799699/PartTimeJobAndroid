package com.example;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ListView;
import com.example.adapter.BianjianAdapter;
import com.example.bean.BianJian;
import com.example.dao.BianjianDao;
import java.util.ArrayList;
import java.util.List;

public class BianJianListActivity extends Activity {
    
    private ListView mLv;
    private List<BianJian> mList = new ArrayList<BianJian>();
    private BianjianAdapter mAdapter;
    private BianjianDao mDao;
    private View mBtnAdd;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bian_jian_list);
        mDao = new BianjianDao(this);
        mLv = (ListView) findViewById(R.id.lv);
        mBtnAdd = findViewById(R.id.btn_add);
        mBtnAdd.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                goToAddSongActivity();
            }
        });
        mAdapter = new BianjianAdapter(this, mList);
        mLv.setAdapter(mAdapter);
    }
    
    @Override
    protected void onResume() {
        super.onResume();
        queryAllContacts();
    }
    
    private void queryAllContacts() {
        List<BianJian> list = mDao.queryAllData();
        if (list != null && !list.isEmpty()) {
            mList.clear();
            mList.addAll(list);
        }
        mAdapter.notifyDataSetChanged();
    }
    
    private void goToAddSongActivity() {
        startActivity(new Intent(this, AddBianjianActivity.class));
    }
}
