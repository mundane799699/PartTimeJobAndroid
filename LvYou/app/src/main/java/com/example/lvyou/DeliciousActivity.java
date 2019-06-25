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

        setTitle("美食");
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
        food1.name = "盐水鸭";
        food1.describe =
                "盐水鸭是南京著名的特产，属金陵菜，是金陵菜的代表之一，又叫桂花鸭，是中国地理标志产品。因南京有“金陵”别称，故也称“金陵盐水鸭”，久负盛名，至今已有两千五百多年历史。南京盐水鸭制作历史悠久，积累了丰富的制作经验。鸭皮白肉嫩、肥而不腻、香鲜味美，具有香、酥、嫩的特点。而以中秋前后，桂花盛开季节制作的的盐水鸭色味最佳，名为桂花鸭。盐水鸭最能体现鸭子的本味，做法返璞归真，滤油腻、驱腥臊、留鲜美、驻肥嫩，常吃盐水鸭还能抗炎消肿拒衰老，心血管病患者尤适宜。远在春秋战国时期，南京即有“筑地养鸭”的记载，据《吴地记》记载：“吴王筑城，城以养鸭，周数百里。”说明，早在春秋战国时期，南京就有了“筑地养鸭”的传统。南京素以喜鸭而闻名，其品种之多，数量之大，传播之广，为中国之最，故南京有”金陵鸭肴甲天下”之赞。";
        mFoodDAO.add(food1);

        Food food2 = new Food();
        food2.drawableName = "meishi2";
        food2.name = "糖芋苗";
        food2.describe =
                "桂花糖芋苗是南京的著名传统甜点，属金陵菜、金陵小吃，和桂花蜜汁藕、梅花糕、赤豆酒酿小圆子一同被誉为金陵南京四大最有人情味街头小食。光洁的芋苗口感润滑爽口、香甜酥软，汤汁呈酱红色鲜亮诱人，散发着浓郁的桂花香，吃后唇齿留香。选用新鲜芋苗，蒸熟后剥皮；加上特制的桂花糖浆，放在大锅里慢慢熬制。煮的时候要放一点口碱，这样芋苗煮才会煮出红彤彤、诱人的颜色。每年中秋节前后，南京城到处飘浮着淡淡桂花香，这时候也正巧是芋头成熟的季节。南京女人手巧，把屈原楚辞中的诗情“援北方闭兮酌桂浆”溶进了生活，采摘下桂花用糖腌制成“桂浆”。南京男人心细，即便是饮食，也追求生命的舒展。看着芋头一窝里不但有大芋艿，还有大大小小的小芋艿，象征着人丁兴旺，团团圆圆。便期盼着生命的美满，把人生真趣蕴含于平淡之中，于是吃糖芋苗就成了南京的习俗。";
        mFoodDAO.add(food2);

        Food scene3 = new Food();
        scene3.drawableName = "meishi3";
        scene3.name = "鸭血粉丝";
        scene3.describe =
                "鸭血粉丝汤是南京的特色名吃，属金陵菜、金陵小吃，是金陵菜和金陵小吃中重要的代表，是久负盛名以鸭为特色的美食之一。鸭血粉丝汤由鸭血、鸭肠、鸭肝等加入鸭汤和粉丝制成。以其口味平和，鲜香爽滑的特点，以及南北皆宜的口味特色，风靡于全国各地。南京自古喜食鸭馔，盛行以鸭制肴，有“金陵鸭肴甲天下”之美誉。鸭血粉丝汤为迎合各地饮食特色进行改良，在制作时，各地均有不同，不过不论是鸭汤的烹制，还是鸭血、鸭肝与鸭肠的制作，都采用的是传统制作金陵盐水鸭的方法，是金陵菜中重要的代表。南京的鸭肴已有1400多年的历史，鸭血汤是鸭血粉丝汤的雏形，里面放有鸭血、鸭肠、鸭肝、鸭胗等食品，清朝时期，有人将粉丝放入鸭血汤内，汤汁芳香四溢，卖相极佳，由此产生鸭血粉丝汤。鸭血粉丝汤不论是鸭汤的烹制，还是鸭血、鸭肝与鸭肠的制作，都采用的是传统制作金陵盐水鸭的方法，是金陵菜中重要的代表。";
        mFoodDAO.add(scene3);
    }
    
    @Override
    public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
        Food food = mFoodList.get(arg2);
        Intent intent = new Intent(this, ContentActivity.class);
        intent.putExtra("image", food.drawableName);
        intent.putExtra("title", food.name);
        intent.putExtra("content", food.describe);
        intent.putExtra("befor", "美食");
        startActivity(intent);
    }
}
