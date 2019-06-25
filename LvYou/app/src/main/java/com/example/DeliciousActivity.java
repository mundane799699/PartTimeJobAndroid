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
import com.example.lvyou.R;
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
        
        setTitle("��ʳ");
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
        food1.name = "��ˮѼ";
        food1.describe =
                "��ˮѼ���Ͼ��������ز���������ˣ��ǽ���˵Ĵ���֮һ���ֽй�Ѽ�����й������־��Ʒ�����Ͼ��С����ꡱ��ƣ���Ҳ�ơ�������ˮѼ�����ø�ʢ��������������ǧ��ٶ�����ʷ���Ͼ���ˮѼ������ʷ�ƾã������˷ḻ���������顣ѼƤ�����ۡ��ʶ����塢����ζ���������㡢�֡��۵��ص㡣��������ǰ�󣬹�ʢ�����������ĵ���ˮѼɫζ��ѣ���Ϊ��Ѽ����ˮѼ��������Ѽ�ӵı�ζ��������豹��棬�����塢����������������פ���ۣ�������ˮѼ���ܿ������׾�˥�ϣ���Ѫ�ܲ����������ˡ�Զ�ڴ���ս��ʱ�ڣ��Ͼ����С�������Ѽ���ļ��أ��ݡ���ؼǡ����أ����������ǣ�������Ѽ�����������˵�������ڴ���ս��ʱ�ڣ��Ͼ������ˡ�������Ѽ���Ĵ�ͳ���Ͼ�����ϲѼ����������Ʒ��֮�࣬����֮�󣬴���֮�㣬Ϊ�й�֮����Ͼ��С�����Ѽ�ȼ����¡�֮�ޡ�";
        mFoodDAO.add(food1);
        
        Food food2 = new Food();
        food2.drawableName = "meishi2";
        food2.name = "������";
        food2.describe =
                "�����������Ͼ���������ͳ��㣬������ˡ�����С�ԣ��͹���֭ź��÷���⡢�ඹ����СԲ��һͬ����Ϊ�����Ͼ��Ĵ���������ζ��ͷСʳ����������ڸ���ˬ�ڡ�����������֭�ʽ���ɫ�������ˣ�ɢ����Ũ���Ĺ��㣬�Ժ󴽳����㡣ѡ���������磬������Ƥ���������ƵĹ��ǽ������ڴ�����������ơ����ʱ��Ҫ��һ��ڼ����������Ż������ͮͮ�����˵���ɫ��ÿ�������ǰ���Ͼ��ǵ���Ʈ���ŵ������㣬��ʱ��Ҳ��������ͷ����ļ��ڡ��Ͼ�Ů�����ɣ�����ԭ�����е�ʫ�顰Ԯ���������ù𽬡��ܽ��������ժ�¹��������Ƴɡ��𽬡����Ͼ�������ϸ����������ʳ��Ҳ׷����������չ��������ͷһ���ﲻ���д���ܵ�����д��СС��С��ܵ���������˶�����������ԲԲ������������������������������Ȥ�̺���ƽ��֮�У����ǳ�������ͳ����Ͼ���ϰ�ס�";
        mFoodDAO.add(food2);
        
        Food scene3 = new Food();
        scene3.drawableName = "meishi3";
        scene3.name = "ѼѪ��˿";
        scene3.describe =
                "ѼѪ��˿�����Ͼ�����ɫ���ԣ�������ˡ�����С�ԣ��ǽ���˺ͽ���С������Ҫ�Ĵ����Ǿø�ʢ����ѼΪ��ɫ����ʳ֮һ��ѼѪ��˿����ѼѪ��Ѽ����Ѽ�εȼ���Ѽ���ͷ�˿�Ƴɡ������ζƽ�ͣ�����ˬ�����ص㣬�Լ��ϱ����˵Ŀ�ζ��ɫ��������ȫ�����ء��Ͼ��Թ�ϲʳѼ�ͣ�ʢ����Ѽ���ȣ��С�����Ѽ�ȼ����¡�֮������ѼѪ��˿��Ϊӭ�ϸ�����ʳ��ɫ���и�����������ʱ�����ؾ��в�ͬ������������Ѽ�������ƣ�����ѼѪ��Ѽ����Ѽ���������������õ��Ǵ�ͳ����������ˮѼ�ķ������ǽ��������Ҫ�Ĵ����Ͼ���Ѽ������1400�������ʷ��ѼѪ����ѼѪ��˿���ĳ��Σ��������ѼѪ��Ѽ����Ѽ�Ρ�Ѽ�ӵ�ʳƷ���峯ʱ�ڣ����˽���˿����ѼѪ���ڣ���֭�������磬���༫�ѣ��ɴ˲���ѼѪ��˿����ѼѪ��˿��������Ѽ�������ƣ�����ѼѪ��Ѽ����Ѽ���������������õ��Ǵ�ͳ����������ˮѼ�ķ������ǽ��������Ҫ�Ĵ���";
        mFoodDAO.add(scene3);
    }
    
    @Override
    public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
        Food food = mFoodList.get(arg2);
        Intent intent = new Intent(this, ContentActivity.class);
        intent.putExtra("image", food.drawableName);
        intent.putExtra("title", food.name);
        intent.putExtra("content", food.describe);
        intent.putExtra("befor", "��ʳ");
        startActivity(intent);
    }
}
