package com.example;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import com.example.adapter.FoodAdapter;
import com.example.bean.GuowaiDongman;
import com.example.dao.LoveMovieDAO;
import java.util.ArrayList;
import java.util.List;

public class ForeignDongmanActivity extends Activity implements OnItemClickListener {
    
    private ListView lv_delicious;
    private FoodAdapter mAdapter;
    private List<GuowaiDongman> mDataList = new ArrayList<GuowaiDongman>();
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
        List<GuowaiDongman> list = mDao.queryAllFood();
        if (list == null || list.isEmpty()) {
            initDaoData();
        }
        list = mDao.queryAllFood();
        mDataList.clear();
        mDataList.addAll(list);
        mAdapter.notifyDataSetChanged();
    }
    
    private void initDaoData() {
        GuowaiDongman dongman1 = new GuowaiDongman();
        dongman1.drawableName = "yiquanchaoren";
        dongman1.name = getString(R.string.yiquan);
        dongman1.describe =
                getString(R.string.yiquandesc);
        mDao.add(dongman1);
        
        GuowaiDongman dongman2 = new GuowaiDongman();
        dongman2.drawableName = "haizeiwang";
        dongman2.name = getString(R.string.haizei);
        dongman2.describe =
                getString(R.string.haizeidesc);
        mDao.add(dongman2);
    }
    
    @Override
    public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
        GuowaiDongman food = mDataList.get(arg2);
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra("image", food.drawableName);
        intent.putExtra("title", food.name);
        intent.putExtra("content", food.describe);
        startActivity(intent);
    }
}