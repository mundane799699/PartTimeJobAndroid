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
import com.example.xstrategy.R;

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
        food1.name = "加菲猫";
        food1.describe =
                "异国短毛猫是在六十年代的美国，以人工方式将波斯猫等长毛种的猫与美国短毛猫、缅甸猫等交配繁殖出来的品种。当初进行繁殖计划时，异国短毛猫的体形还很瘦弱，波斯猫的饲养者担心纯种波斯猫会被杂种化，有些繁殖者甚至强烈批评它“有损纯种猫”，因而在1968年禁止交配。当然，还是有人暗暗努力，最后发现用美国短毛猫配种，形体才渐渐成型。直到八十年代，异国短毛猫的品种正式确立，并获得猫协会的认可";
        mFoodDAO.add(food1);
        
        Food food2 = new Food();
        food2.drawableName = "yingduan";
        food2.name = "英短";
        food2.describe =
                "英国短毛猫，体形圆胖，四肢粗短发达，毛短而密，头大脸圆，温柔平静，对人友善，极易饲养。大而圆的眼睛根据被毛不同而呈现各种颜色。作为一个古老的猫品种，其历史可追溯至古罗马时期的家猫，由于拥有悠久的育种历史，可以称得上是猫家族中的典范。英国短毛猫除了拥有固定而聚，代表性的遗传特征之外，又具有丰富的变异性，如背毛色眼睛颜色等。更重要的是，有了广泛的配种历史后，这种猫拥有了更健康的身体和更温驯的性格";
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
