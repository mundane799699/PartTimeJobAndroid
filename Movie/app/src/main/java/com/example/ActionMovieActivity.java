package com.example;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ListView;
import com.example.adapter.ActionMovieAdapter;
import com.example.bean.ActionMovie;
import com.example.dao.ActionMovieDAO;
import java.util.ArrayList;
import java.util.List;

public class ActionMovieActivity extends Activity {
    
    private ListView mLv;
    private List<ActionMovie> mDataList = new ArrayList<ActionMovie>();
    private ActionMovieDAO mActionMovie;
    private ActionMovieAdapter mAdapter;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scenic);
        mLv = (ListView) findViewById(R.id.lv_strategy);
        mAdapter = new ActionMovieAdapter(this, mDataList);
        mLv.setAdapter(mAdapter);
        mActionMovie = new ActionMovieDAO(this);
        findViewById(R.id.btn_add).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoAddActionMovie();
            }
        });
    }
    
    private void gotoAddActionMovie() {
        startActivity(new Intent(this, AddActionMovieActivity.class));
    }
    
    @Override
    protected void onResume() {
        super.onResume();
        queryData();
    }
    
    private void queryData() {
        List<com.example.bean.ActionMovie> list = mActionMovie.queryAllMovie();
        if (list != null && !list.isEmpty()) {
            mDataList.clear();
            mDataList.addAll(list);
        }
        mAdapter.notifyDataSetChanged();
    }
}
