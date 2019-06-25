package com.example;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import com.example.adapter.FoodAdapter;
import com.example.bean.LoveMovie;
import com.example.dao.LoveMovieDAO;
import java.util.ArrayList;
import java.util.List;

public class LoveMovieActivity extends Activity implements OnItemClickListener {
    
    private ListView lv_delicious;
    private FoodAdapter mAdapter;
    private List<LoveMovie> mDataList = new ArrayList<LoveMovie>();
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
        List<LoveMovie> list = mDao.queryAllFood();
        if (list == null || list.isEmpty()) {
            initDaoData();
        }
        list = mDao.queryAllFood();
        mDataList.clear();
        mDataList.addAll(list);
        mAdapter.notifyDataSetChanged();
    }
    
    private void initDaoData() {
        LoveMovie movie1 = new LoveMovie();
        movie1.drawableName = "taitannike";
        movie1.name = getString(R.string.taitannike);
        movie1.describe =
                getString(R.string.taitannikedescribe);
        mDao.add(movie1);
        
        LoveMovie movie2 = new LoveMovie();
        movie2.drawableName = "luomajiari";
        movie2.name = getString(R.string.luomajiari);
        movie2.describe =
                getString(R.string.luomajiaridescribe);
        mDao.add(movie2);
    }
    
    @Override
    public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
        LoveMovie food = mDataList.get(arg2);
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra("image", food.drawableName);
        intent.putExtra("title", food.name);
        intent.putExtra("content", food.describe);
        startActivity(intent);
    }
}
