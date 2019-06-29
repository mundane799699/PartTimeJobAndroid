package com.example;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ListView;
import com.example.adapter.WuYangAdapter;
import com.example.bean.WuyangYundong;
import com.example.dao.WuyangDAO;
import java.util.ArrayList;
import java.util.List;

public class WuyangActivity extends Activity {
    
    private ListView lv_strategy;
    private List<WuyangYundong> mDataList = new ArrayList<WuyangYundong>();
    private WuyangDAO mDao;
    private WuYangAdapter mAdapter;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scenic);
        
        lv_strategy = (ListView) findViewById(R.id.lv_strategy);
        mAdapter = new WuYangAdapter(this, mDataList);
        lv_strategy.setAdapter(mAdapter);
        mDao = new WuyangDAO(this);
        
        findViewById(R.id.btn_add).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                addWuyang();
            }
        });
    }
    
    private void addWuyang() {
        startActivity(new Intent(this, AddWuyangActivity.class));
    }
    
    @Override
    protected void onResume() {
        super.onResume();
        queryData();
    }
    
    private void queryData() {
        List<WuyangYundong> list = mDao.queryAll();
        if (list != null && !list.isEmpty()) {
            mDataList.clear();
            mDataList.addAll(list);
        }
        list = mDao.queryAll();
        mDataList.clear();
        mDataList.addAll(list);
        mAdapter.notifyDataSetChanged();
    }
}
