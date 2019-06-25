package com.example;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import com.example.adapter.FoodAdapter;
import com.example.base.BaseActivity;
import com.example.bean.ChuiGuanYueQi;
import com.example.dao.LoveMovieDAO;
import com.example.xstrategy.R;
import java.util.ArrayList;
import java.util.List;

public class ChuiGuanYueQiActivity extends BaseActivity implements OnItemClickListener {
    
    private ListView lv_delicious;
    private FoodAdapter mAdapter;
    private List<ChuiGuanYueQi> mDataList = new ArrayList<ChuiGuanYueQi>();
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
        List<ChuiGuanYueQi> list = mDao.queryAllFood();
        if (list == null || list.isEmpty()) {
            initDaoData();
        }
        list = mDao.queryAllFood();
        mDataList.clear();
        mDataList.addAll(list);
        mAdapter.notifyDataSetChanged();
    }
    
    private void initDaoData() {
        ChuiGuanYueQi movie1 = new ChuiGuanYueQi();
        movie1.drawableName = "zhudi";
        movie1.name = "̩̹竹笛";
        movie1.describe =
                "竹笛，汉族乐器名。笛子是中国广为流传的吹奏乐器，因为是用天然竹材制成，所以也称为“竹笛”。竹笛流传地域广大，品种繁多。使用最普遍的有曲笛、梆笛和定调笛。还有玉屏笛、七孔笛、短笛和顺笛等。\n" +
                        "中国笛子具有强烈的华夏民族特色，发音动情、婉转。龙吟，古人谓“荡涤之声”， 故笛子原名为“涤”，日本至今还保留有“涤笛”，后演变为如今的笛。笛子是中国民族乐队中重要的旋律乐器，多用于独奏，也可参与合奏";
        mDao.add(movie1);
        
        ChuiGuanYueQi movie2 = new ChuiGuanYueQi();
        movie2.drawableName = "danhuangguan";
        movie2.name = "单簧管";
        movie2.describe =
                "单簧管，又称黑管或克拉管，在台湾又称为竖笛(英语称Clarinet，意大利语为Clarinetto，西班牙语为Clarinete，法语为Clarinette，德语为Klarinette)，有管弦乐队中的“演说家”和木管乐器中的戏剧女高音之称。高音区嘹亮明朗；中音区富于表情，音色纯净，清澈优美；低音区低沉，浑厚而丰满，是木管乐曲家族中应用最广泛的乐器之一。\n" +
                        "单簧管是木管乐器的一种，通常用非洲黑木制造，由木料、硬橡胶或金属制成，有一个鸟嘴形的吹口和圆形的空心，管身由五节可装拆的管体组成，管体成圆筒形，下端为开放的喇叭口。在吹口处固定一个簧片，吹奏者通过簧片和吹口的空间吹气时，并配合下唇适当的压力，薄薄的簧片尖产生振动，使乐器管内的空气柱开始振动，因而发出柔美的音色。其根源可以追溯到号角和风笛，一般认为是从一种类似竖笛的单簧片乐器芦笛(chalumeau)演变而来。";
        mDao.add(movie2);
    }
    
    @Override
    public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
        ChuiGuanYueQi food = mDataList.get(arg2);
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra("image", food.drawableName);
        intent.putExtra("title", food.name);
        intent.putExtra("content", food.describe);
        startActivity(intent);
    }
}
