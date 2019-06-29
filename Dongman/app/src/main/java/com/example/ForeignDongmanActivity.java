package com.example;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ListView;
import com.example.adapter.ForeignAdapter;
import com.example.bean.GuowaiDongman;
import com.example.dao.ForeignDongmanDAO;
import java.util.ArrayList;
import java.util.List;

public class ForeignDongmanActivity extends Activity {
    
    private ListView lv_delicious;
    private ForeignAdapter mAdapter;
    private List<GuowaiDongman> mDataList = new ArrayList<GuowaiDongman>();
    private ForeignDongmanDAO mDao;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delicious);
        mDao = new ForeignDongmanDAO(this);
        lv_delicious = (ListView) findViewById(R.id.lv_delicious);
        mAdapter = new ForeignAdapter(this, mDataList);
        lv_delicious.setAdapter(mAdapter);
        findViewById(R.id.btn_add).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                addForeignMovie();
            }
        });
    }
    
    private void addForeignMovie() {
        startActivity(new Intent(this, AddForeignDongmanActivity.class));
    }
    
    @Override
    protected void onResume() {
        super.onResume();
        queryData();
    }
    
    private void queryData() {
        List<GuowaiDongman> list = mDao.queryAll();
        if (list != null && !list.isEmpty()) {
            mDataList.clear();
            mDataList.addAll(list);
        }
        mAdapter.notifyDataSetChanged();
    }
}