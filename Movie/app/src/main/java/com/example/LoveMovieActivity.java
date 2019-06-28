package com.example;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ListView;
import com.example.adapter.LoveMovieAdapter;
import com.example.bean.LoveMovie;
import com.example.dao.LoveMovieDAO;
import java.util.ArrayList;
import java.util.List;

public class LoveMovieActivity extends Activity {
    
    private ListView mLv;
    private LoveMovieAdapter mAdapter;
    private List<LoveMovie> mDataList = new ArrayList<LoveMovie>();
    private LoveMovieDAO mDao;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delicious);
        
        mLv = (ListView) findViewById(R.id.lv_delicious);
        mAdapter = new LoveMovieAdapter(this, mDataList);
        mLv.setAdapter(mAdapter);
        mDao = new LoveMovieDAO(this);
        findViewById(R.id.btn_add).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                toAddLoveMovie();
        
            }
        });
    }
    
    private void toAddLoveMovie() {
        startActivity(new Intent(this, AddLoveMovieActivity.class));
    }
    
    @Override
    protected void onResume() {
        super.onResume();
        queryData();
    }
    
    private void queryData() {
        List<LoveMovie> list = mDao.queryAllMovie();
        if (list == null || !list.isEmpty()) {
            mDataList.clear();
            mDataList.addAll(list);
        }
        mAdapter.notifyDataSetChanged();
    }
}
