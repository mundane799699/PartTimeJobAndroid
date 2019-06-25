package com.example.lvyou;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import com.example.lvyou.adapter.FoodAdapter;
import com.example.lvyou.bean.Food;
import com.example.lvyou.dao.FoodDAO;

import java.util.ArrayList;
import java.util.List;

public class DeliciousActivity extends Activity implements OnItemClickListener {
    
    private ListView lv_delicious;
    private FoodAdapter mFoodAdapter;
    private List<Food> mFoodList = new ArrayList<Food>();
    private FoodDAO mFoodDAO;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delicious);

        setTitle(R.string.meishi);
        lv_delicious = (ListView) findViewById(R.id.lv_delicious);
        mFoodAdapter = new FoodAdapter(this, mFoodList);
        lv_delicious.setAdapter(mFoodAdapter);
        lv_delicious.setOnItemClickListener(this);
        initData();
    }
    
    private void initData() {
        mFoodDAO = new FoodDAO(this);
        List<Food> foodList = mFoodDAO.queryAllFood();
        if (foodList == null || foodList.isEmpty()) {
            initDaoData();
        }
        foodList = mFoodDAO.queryAllFood();
        mFoodList.clear();
        mFoodList.addAll(foodList);
        mFoodAdapter.notifyDataSetChanged();
    }
    
    private void initDaoData() {
        Food food1 = new Food();
        food1.drawableName = "meishi1";
        food1.name = getString(R.string.yanshuiya);
        food1.describe =
                getString(R.string.yanshuiyad);
        mFoodDAO.add(food1);

        Food food2 = new Food();
        food2.drawableName = "meishi2";
        food2.name = getString(R.string.tangyumiao);
        food2.describe =
                getString(R.string.tangyumiaod);
        mFoodDAO.add(food2);

        Food scene3 = new Food();
        scene3.drawableName = "meishi3";
        scene3.name = getString(R.string.yaxuef);
        scene3.describe =
                getString(R.string.yaxuefd);
        mFoodDAO.add(scene3);
    }
    
    @Override
    public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
        Food food = mFoodList.get(arg2);
        Intent intent = new Intent(this, ContentActivity.class);
        intent.putExtra("image", food.drawableName);
        intent.putExtra("title", food.name);
        intent.putExtra("content", food.describe);
        intent.putExtra("befor", R.string.meishi2);
        startActivity(intent);
    }
}
