package com.example;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ListView;
import com.example.adapter.YouyangAdapter;
import com.example.bean.YouyangYundong;
import com.example.dao.YouYangDAO;
import java.util.ArrayList;
import java.util.List;

public class YouyangActivity extends Activity {
    
    private ListView mLv;
    private YouyangAdapter mAdapter;
    private List<YouyangYundong> mDataList = new ArrayList<YouyangYundong>();
    private YouYangDAO mDao;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delicious);
        mDao = new YouYangDAO(this);
        mLv = (ListView) findViewById(R.id.lv_delicious);
        mAdapter = new YouyangAdapter(this, mDataList);
        mLv.setAdapter(mAdapter);
        findViewById(R.id.btn_add).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                addYouYang();
        
            }
        });
    }
    
    private void addYouYang() {
        startActivity(new Intent(this, AddYouyangActivity.class));
    }
    
    @Override
    protected void onResume() {
        super.onResume();
        queryData();
    }
    
    private void queryData() {
        List<YouyangYundong> list = mDao.queryAllFood();
        if (list != null && !list.isEmpty()) {
            mDataList.clear();
            mDataList.addAll(list);
        }
        list = mDao.queryAllFood();
        mDataList.clear();
        mDataList.addAll(list);
        mAdapter.notifyDataSetChanged();
    }
}
