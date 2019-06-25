package com.example;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import com.example.adapter.SceneAdapter;
import com.example.base.BaseActivity;
import com.example.bean.TanZouYueQi;
import com.example.dao.ActionMovieDAO;
import com.example.xstrategy.R;
import java.util.ArrayList;
import java.util.List;

public class TanZouYueQiActivity extends BaseActivity implements OnItemClickListener {
    
    private ListView lv_strategy;
    private List<TanZouYueQi> mDataList = new ArrayList<TanZouYueQi>();
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
        List<TanZouYueQi> list = mActionMovie.queryAllMovie();
        if (list == null || list.isEmpty()) {
            initDaoData();
        }
        list = mActionMovie.queryAllMovie();
        mDataList.clear();
        mDataList.addAll(list);
        mAdapter.notifyDataSetChanged();
    }
    
    private void initDaoData() {
        TanZouYueQi yueqi1 = new TanZouYueQi();
        yueqi1.drawableName = "jita";
        yueqi1.name = "吉他";
        yueqi1.describe =
                "吉他（意大利语：Chitarra），又译为结他或六弦琴。是一种弹拨乐器，通常有六条弦，形状与提琴相似。\n" +
                        "吉他在流行音乐、摇滚音乐、蓝调、民歌、佛朗明哥中，常被视为主要乐器。而在古典音乐的领域里，吉他常以独奏或二重奏的型式演出；当然，在室内乐和管弦乐中，吉他亦扮演着相当程度的陪衬角色。\n" +
                        "古典吉他与小提琴、钢琴并列为世界著名三大乐器";
        mActionMovie.add(yueqi1);
        
        TanZouYueQi yueqi2 = new TanZouYueQi();
        yueqi2.drawableName = "youkelili";
        yueqi2.name = "尤克里里";
        yueqi2.describe =
                "Ukulele即夏威夷小吉他，在港台等地一般译作乌克丽丽，在大陆一般习惯称为尤克里里，是一种四弦夏威夷的拨弦乐器，发明于葡萄牙盛行于夏威夷，归属在吉他乐器一族。\n";
        mActionMovie.add(yueqi2);

    }
    
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(this, DetailActivity.class);
        TanZouYueQi scene = mDataList.get(position);
        intent.putExtra("image", scene.drawableName);
        intent.putExtra("title", scene.name);
        intent.putExtra("content", scene.describe);
        startActivity(intent);
    }
}
