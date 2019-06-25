package com.example;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import com.example.adapter.SceneAdapter;
import com.example.bean.ActionMovie;
import com.example.dao.ActionMovieDAO;
import java.util.ArrayList;
import java.util.List;

public class ActionMovieActivity extends Activity implements OnItemClickListener {
    
    private ListView lv_strategy;
    private List<ActionMovie> mDataList = new ArrayList<ActionMovie>();
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
        List<ActionMovie> list = mActionMovie.queryAllMovie();
        if (list == null || list.isEmpty()) {
            initDaoData();
        }
        list = mActionMovie.queryAllMovie();
        mDataList.clear();
        mDataList.addAll(list);
        mAdapter.notifyDataSetChanged();
    }
    
    private void initDaoData() {
        ActionMovie movie1 = new ActionMovie();
        movie1.drawableName = "gongfu";
        movie1.name = getString(R.string.gongfu);
        movie1.describe =
                getString(R.string.gonfude);
        mActionMovie.add(movie1);
        
        ActionMovie movie2 = new ActionMovie();
        movie2.drawableName = "daohuoxian";
        movie2.name = getString(R.string.daohuoxian);
        movie2.describe =
                getString(R.string.daohuoxiande);
        mActionMovie.add(movie2);
        
    }
    
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(this, DetailActivity.class);
        ActionMovie scene = mDataList.get(position);
        intent.putExtra("image", scene.drawableName);
        intent.putExtra("title", scene.name);
        intent.putExtra("content", scene.describe);
        startActivity(intent);
    }
}
