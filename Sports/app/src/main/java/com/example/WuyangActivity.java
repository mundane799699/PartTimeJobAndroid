package com.example;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import com.example.adapter.SceneAdapter;
import com.example.bean.WuyangYundong;
import com.example.dao.ActionMovieDAO;
import java.util.ArrayList;
import java.util.List;

public class WuyangActivity extends Activity implements OnItemClickListener {
    
    private ListView lv_strategy;
    private List<WuyangYundong> mDataList = new ArrayList<WuyangYundong>();
    private ActionMovieDAO mActionMovie;
    private SceneAdapter mAdapter;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scenic);
        
        lv_strategy = (ListView) findViewById(R.id.lv_strategy);
        mAdapter = new SceneAdapter(this, mDataList);
        lv_strategy.setAdapter(mAdapter);
        lv_strategy.setOnItemClickListener(this);
        initData();
    }
    
    private void initData() {
        mActionMovie = new ActionMovieDAO(this);
        List<WuyangYundong> list = mActionMovie.queryAllMovie();
        if (list == null || list.isEmpty()) {
            initDaoData();
        }
        list = mActionMovie.queryAllMovie();
        mDataList.clear();
        mDataList.addAll(list);
        mAdapter.notifyDataSetChanged();
    }
    
    private void initDaoData() {
        WuyangYundong yundong1 = new WuyangYundong();
        yundong1.drawableName = "juzhong";
        yundong1.name = getString(R.string.juzho);
        yundong1.describe =
                getString(R.string.juhzongd);
        mActionMovie.add(yundong1);
        
        WuyangYundong yundong2 = new WuyangYundong();
        yundong2.drawableName = "fuwocheng";
        yundong2.name = getString(R.string.fwc);
        yundong2.describe =
                getString(R.string.fwcd);
        mActionMovie.add(yundong2);
        
    }
    
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(this, DetailActivity.class);
        WuyangYundong scene = mDataList.get(position);
        intent.putExtra("image", scene.drawableName);
        intent.putExtra("title", scene.name);
        intent.putExtra("content", scene.describe);
        startActivity(intent);
    }
}
