package com.example;

import com.example.adapter.SceneAdapter;
import com.example.bean.Dog;
import com.example.dao.DaoDAO;
import com.example.xstrategy.R;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class DogActivity extends Activity implements OnItemClickListener {
    
    private ListView lv_strategy;
    private List<Dog> mSceneList = new ArrayList<Dog>();
    private DaoDAO mDogDAO;
    private SceneAdapter mAdapter;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scenic);
        
        lv_strategy = (ListView) findViewById(R.id.lv_strategy);
        mAdapter = new SceneAdapter(this, mSceneList);
        lv_strategy.setAdapter(mAdapter);
        lv_strategy.setOnItemClickListener(this);
        initData();
    }
    
    private void initData() {
        mDogDAO = new DaoDAO(this);
        List<Dog> sceneList = mDogDAO.queryAllScene();
        if (sceneList == null || sceneList.isEmpty()) {
            initDaoData();
        }
        sceneList = mDogDAO.queryAllScene();
        mSceneList.clear();
        mSceneList.addAll(sceneList);
        mAdapter.notifyDataSetChanged();
    }
    
    private void initDaoData() {
        Dog dog1 = new Dog();
        dog1.drawableName = "jinmao";
        dog1.name = getString(R.string.jinmao);
        dog1.describe = getString(R.string.jinmaodescribe);
        mDogDAO.add(dog1);
        
        Dog dog2 = new Dog();
        dog2.drawableName = "hashiqi";
        dog2.name = getString(R.string.hashiqi);
        dog2.describe = getString(R.string.hashiqidescribe);
        mDogDAO.add(dog2);
    }
    
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(this, DetailActivity.class);
        Dog scene = mSceneList.get(position);
        intent.putExtra("image", scene.drawableName);
        intent.putExtra("title", scene.name);
        intent.putExtra("content", scene.describe);
        startActivity(intent);
    }
}
