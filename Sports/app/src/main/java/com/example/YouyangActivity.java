package com.example;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import com.example.adapter.FoodAdapter;
import com.example.bean.YouyangYundong;
import com.example.dao.LoveMovieDAO;
import java.util.ArrayList;
import java.util.List;

public class YouyangActivity extends Activity implements OnItemClickListener {
    
    private ListView lv_delicious;
    private FoodAdapter mAdapter;
    private List<YouyangYundong> mDataList = new ArrayList<YouyangYundong>();
    private LoveMovieDAO mDao;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delicious);
        
        lv_delicious = (ListView) findViewById(R.id.lv_delicious);
        mAdapter = new FoodAdapter(this, mDataList);
        lv_delicious.setAdapter(mAdapter);
        lv_delicious.setOnItemClickListener(this);
        initData();
    }
    
    private void initData() {
        mDao = new LoveMovieDAO(this);
        List<YouyangYundong> list = mDao.queryAllFood();
        if (list == null || list.isEmpty()) {
            initDaoData();
        }
        list = mDao.queryAllFood();
        mDataList.clear();
        mDataList.addAll(list);
        mAdapter.notifyDataSetChanged();
    }
    
    private void initDaoData() {
        YouyangYundong yundong1 = new YouyangYundong();
        yundong1.drawableName = "youyong";
        yundong1.name = getString(R.string.youyong);
        yundong1.describe =
                getString(R.string.yoyongd);
        mDao.add(yundong1);
        
        YouyangYundong yundong2 = new YouyangYundong();
        yundong2.drawableName = "manpao";
        yundong2.name = getString(R.string.manpao);
        yundong2.describe =
                getString(R.string.manpaod);
        mDao.add(yundong2);
    }
    
    @Override
    public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
        YouyangYundong food = mDataList.get(arg2);
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra("image", food.drawableName);
        intent.putExtra("title", food.name);
        intent.putExtra("content", food.describe);
        startActivity(intent);
    }
}
