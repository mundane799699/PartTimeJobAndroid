package com.example;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import com.example.adapter.FoodAdapter;
import com.example.bean.Food;
import com.example.dao.FoodDAO;
import com.example.R;

import java.util.ArrayList;
import java.util.List;

public class CatActivity extends Activity implements OnItemClickListener {
    
    private ListView lv_delicious;
    private FoodAdapter mFoodAdapter;
    private List<Food> mFoodList = new ArrayList<Food>();
    private FoodDAO mFoodDAO;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delicious);
        
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
        food1.drawableName = "jiafei";
        food1.name = "�ӷ�è";
        food1.describe =
                "�����ëè������ʮ��������������˹���ʽ����˹è�ȳ�ë�ֵ�è��������ëè�����è�Ƚ��䷱ֳ������Ʒ�֡��������з�ֳ�ƻ�ʱ�������ëè�����λ�����������˹è�������ߵ��Ĵ��ֲ�˹è�ᱻ���ֻ�����Щ��ֳ������ǿ����������������è���������1968���ֹ���䡣��Ȼ���������˰���Ŭ�����������������ëè���֣�����Ž������͡�ֱ����ʮ����������ëè��Ʒ����ʽȷ���������èЭ����Ͽ�";
        mFoodDAO.add(food1);
        
        Food food2 = new Food();
        food2.drawableName = "yingduan";
        food2.name = "Ӣ��";
        food2.describe =
                "Ӣ����ëè������Բ�֣���֫�̷ֶ��ë�̶��ܣ�ͷ����Բ������ƽ�����������ƣ��������������Բ���۾����ݱ�ë��ͬ�����ָ�����ɫ����Ϊһ�����ϵ�èƷ�֣�����ʷ��׷����������ʱ�ڵļ�è������ӵ���ƾõ�������ʷ�����ԳƵ�����è�����еĵ䷶��Ӣ����ëè����ӵ�й̶����ۣ������Ե��Ŵ�����֮�⣬�־��зḻ�ı����ԣ��米ëɫ�۾���ɫ�ȡ�����Ҫ���ǣ����˹㷺��������ʷ������èӵ���˸�����������͸���ѱ���Ը�";
        mFoodDAO.add(food2);
    }
    
    @Override
    public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
        Food food = mFoodList.get(arg2);
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra("image", food.drawableName);
        intent.putExtra("title", food.name);
        intent.putExtra("content", food.describe);
        startActivity(intent);
    }
}
