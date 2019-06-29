package com.example;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ListView;
import com.example.adapter.CheniseDongmanAdapter;
import com.example.bean.ChineseDongman;
import com.example.dao.ChineseDongmanDAO;
import java.util.ArrayList;
import java.util.List;

public class ChineseDongmanActivity extends Activity {
    
    private ListView lv_strategy;
    private List<ChineseDongman> mDataList = new ArrayList<ChineseDongman>();
    private ChineseDongmanDAO mActionMovie;
    private CheniseDongmanAdapter mAdapter;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scenic);
        mActionMovie = new ChineseDongmanDAO(this);
        lv_strategy = (ListView) findViewById(R.id.lv_strategy);
        mAdapter = new CheniseDongmanAdapter(this, mDataList);
        lv_strategy.setAdapter(mAdapter);
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
        List<ChineseDongman> list = mActionMovie.queryAllMovie();
        if (list != null && !list.isEmpty()) {
            mDataList.clear();
            mDataList.addAll(list);
        }
        mAdapter.notifyDataSetChanged();
    }
    
}