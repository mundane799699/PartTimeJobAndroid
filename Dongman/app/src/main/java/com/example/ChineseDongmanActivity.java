package com.example;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import com.example.adapter.SceneAdapter;
import com.example.bean.ChineseDongman;
import com.example.dao.ActionMovieDAO;
import java.util.ArrayList;
import java.util.List;

public class ChineseDongmanActivity extends Activity implements OnItemClickListener {
    
    private ListView lv_strategy;
    private List<ChineseDongman> mDataList = new ArrayList<ChineseDongman>();
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
        List<ChineseDongman> list = mActionMovie.queryAllMovie();
        if (list == null || list.isEmpty()) {
            initDaoData();
        }
        list = mActionMovie.queryAllMovie();
        mDataList.clear();
        mDataList.addAll(list);
        mAdapter.notifyDataSetChanged();
    }
    
    private void initDaoData() {
        ChineseDongman dongman1 = new ChineseDongman();
        dongman1.drawableName = "baishe";
        dongman1.name = "����Ե��";
        dongman1.describe =
                "���ߣ�Ե������׷�⶯���ͻ����ֵܹ�ͬ�����Ķ�����Ӱ���ɻƼҿ�������ִ�����ņ��������衢��Сϲ����������2019��1��11�����й��ڵ���ӳ��ӰƬ���й���䴫˵�����ߴ����������������£������������������ǰ�����ɵ�ǰ����֮��һ�ο̹����ĵİ������";
        mActionMovie.add(dongman1);
        
        ChineseDongman dongman2 = new ChineseDongman();
        dongman2.drawableName = "qinshimingyue";
        dongman2.name = "��ʱ����";
        dongman2.describe =
                "����ʱ���¡�ϵ�������й����������Ƽ���Ϣ�������޹�˾������3D��������ϵ�У���2007�괺���ڼ������й�ȫ��������ʽ��ӳ��������������Ȩ�����������������Ľ������硢���ڵ��Ӳ����Ľ���ƪ�����оŸ衷�ȡ�Ŀǰ���������ǰ5������175������6������ʱ����֮�׺�����������ӳ���¡�";
        mActionMovie.add(dongman2);

    }
    
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(this, DetailActivity.class);
        ChineseDongman scene = mDataList.get(position);
        intent.putExtra("image", scene.drawableName);
        intent.putExtra("title", scene.name);
        intent.putExtra("content", scene.describe);
        startActivity(intent);
    }
}
