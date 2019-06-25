package com.example.lvyou;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import com.example.lvyou.adapter.SceneAdapter;
import com.example.lvyou.bean.Scene;
import com.example.lvyou.dao.SceneDAO;

import java.util.ArrayList;
import java.util.List;

public class ScenicActivity extends Activity implements OnItemClickListener {
    
    private ListView lv_strategy;
    private List<Scene> mSceneList = new ArrayList<Scene>();
    private SceneDAO mSceneDAO;
    private SceneAdapter mAdapter;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scenic);

        setTitle(R.string.ing);
        lv_strategy = (ListView) findViewById(R.id.lv_strategy);
        mAdapter = new SceneAdapter(this, mSceneList);
        lv_strategy.setAdapter(mAdapter);
        lv_strategy.setOnItemClickListener(this);
        initData();
    }
    
    private void initData() {
        mSceneDAO = new SceneDAO(this);
        List<Scene> sceneList = mSceneDAO.queryAllScene();
        if (sceneList == null || sceneList.isEmpty()) {
            initDaoData();
        }
        sceneList = mSceneDAO.queryAllScene();
        mSceneList.clear();
        mSceneList.addAll(sceneList);
        mAdapter.notifyDataSetChanged();
    }
    
    private void initDaoData() {
        Scene scene1 = new Scene();
        scene1.drawableName = "jingdian1";
        scene1.name = getString(R.string.fuzimiao);
        scene1.describe =
                getString(R.string.fuzimiaod);
        mSceneDAO.add(scene1);

        Scene scene2 = new Scene();
        scene2.drawableName = "jingdian2";
        scene2.name = getString(R.string.mingixo);
        scene2.describe =
                getString(R.string.mingxiad);
        mSceneDAO.add(scene2);

        Scene scene3 = new Scene();
        scene3.drawableName = "jingdian3";
        scene3.name = getString(R.string.xanfu);
        scene3.describe =
                getString(R.string.xuanfud);
        mSceneDAO.add(scene3);
    }
    
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        // TODO Auto-generated method stub
        Intent intent = new Intent(this, ContentActivity.class);
        Scene scene = mSceneList.get(position);
        intent.putExtra("image", scene.drawableName);
        intent.putExtra("title", scene.name);
        intent.putExtra("content", scene.describe);
        intent.putExtra("befor", R.string.jingd3);
        startActivity(intent);
    }
}
