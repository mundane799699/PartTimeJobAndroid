package com.example.daliantear.shop;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ListView;
import com.example.daliantear.R;
import com.example.daliantear.bean.Shop;
import com.example.daliantear.dao.ShopDao;
import java.util.ArrayList;
import java.util.List;

/**
 * ShopListActivity
 *
 * @author fangyuan
 * @date 2019-06-30
 */
public class ShopListActivity extends Activity {
    
    private ListView mLv;
    private List<Shop> mList = new ArrayList<Shop>();
    private ShopDao mDao;
    private ShopAdapter mAdapter;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_list);
        findViewById(R.id.btn_add).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                addShop();
            }
        });
        mLv = (ListView) findViewById(R.id.lv);
        mDao = new ShopDao(this);
        mAdapter = new ShopAdapter(this, mList);
        mLv.setAdapter(mAdapter);
    }
    
    @Override
    protected void onResume() {
        super.onResume();
        List<Shop> list = mDao.queryAll();
        if (list != null && !list.isEmpty()) {
            mList.clear();
            mList.addAll(list);
        }
        mAdapter.notifyDataSetChanged();
    }
    
    private void addShop() {
        startActivity(new Intent(this, AddShopActivity.class));
    }
}
