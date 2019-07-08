package com.example;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ListView;
import com.example.adapter.CheniseDongmanAdapter;
import com.example.base.BaseActivity;
import com.example.bean.ChineseDongman;
import com.example.dao.ChineseDongmanDAO;
import java.util.ArrayList;
import java.util.List;

public class ChineseDongmanActivity extends BaseActivity {
    
    private ListView mLv;
    private List<ChineseDongman> mDataList = new ArrayList<>();
    private ChineseDongmanDAO mDao;
    private CheniseDongmanAdapter mAdapter;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scenic);
        mDao = new ChineseDongmanDAO(this);
        mLv = findViewById(R.id.lv_strategy);
        mAdapter = new CheniseDongmanAdapter(this, mDataList);
        mLv.setAdapter(mAdapter);
        findViewById(R.id.btn_add).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                addChineseDongman();
            }
        });
    }
    
    @Override
    protected void onResume() {
        super.onResume();
        queryData();
    }
    
    private void addChineseDongman() {
        
        startActivity(new Intent(this, AddChineseDongmanActivity.class));
    }
    
    private void queryData() {
        List<ChineseDongman> list = mDao.queryAll();
        if (list != null && !list.isEmpty()) {
            mDataList.clear();
            mDataList.addAll(list);
        }
        mAdapter.notifyDataSetChanged();
    }
}