package com.example.tuijian;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import com.example.R;
import com.example.adapter.TuijianListAdapter;
import com.example.bean.Tuijian;
import java.util.ArrayList;
import java.util.List;

public class DongmanTuijianActivity extends AppCompatActivity {
    
    private ListView mLv;
    private List<Tuijian> mList;
    private TuijianListAdapter mAdapter;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dongman_tuijian);
        mLv = findViewById(R.id.lv);
        initData();
        mLv.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Tuijian tuijian = mList.get(position);
                if (!TextUtils.isEmpty(tuijian.describe)) {
                    TuijianDetailActivity.openTuijianDetail(DongmanTuijianActivity.this, tuijian);
                }
            }
        });
    }
    
    private void initData() {
        mList = new ArrayList<>();
        Tuijian tuijian = new Tuijian();
        tuijian.name = "大鱼海棠";
        tuijian.drawable = "dayu";
        tuijian.describe =
                "《大鱼海棠》是彼岸天文化有限公司、北京光线影业有限公司、霍尔果斯彩条屋影业有限公司联合出品，由梁旋、张春执导，梁旋编剧，季冠霖、苏尚卿、许魏洲、金士杰等配音的奇幻动画电影。\n" + "该片讲述了掌管海棠花生长的少女椿为报恩而努力复活人类男孩“鲲”的灵魂，在本是天神的湫帮助下与彼此纠缠的命运斗争的故事。影片于2016年7月8日在中国大陆上映。";
        mList.add(tuijian);
        
        tuijian = new Tuijian();
        tuijian.name = "昨日青空";
        tuijian.drawable = "zuori";
        mList.add(tuijian);
        
        tuijian = new Tuijian();
        tuijian.name = "你的名字";
        tuijian.drawable = "nidemingzi";
        mList.add(tuijian);
        
        tuijian = new Tuijian();
        tuijian.name = "天空之城";
        tuijian.drawable = "tiankong";
        mList.add(tuijian);
        
        mAdapter = new TuijianListAdapter(mList);
        mLv.setAdapter(mAdapter);
    }
}
