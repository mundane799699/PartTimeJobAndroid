package com.example;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ListView;
import com.example.adapter.ShangJiaAdapter;
import com.example.bean.ShangJia;
import com.example.dao.ShangJiaDao;
import java.util.ArrayList;
import java.util.List;

/**
 * ShangpinListActivity
 *
 * @author fangyuan
 * @date 2019-06-28
 */
public class ShangjiaListActivity extends Activity {
    
    private ListView mLv;
    private List<ShangJia> mList = new ArrayList<ShangJia>();
    private ShangJiaDao mDao;
    private ShangJiaAdapter mAdapter;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shangjia_list);
        mLv = (ListView) findViewById(R.id.lv);
        mDao = new ShangJiaDao(this);
        mAdapter = new ShangJiaAdapter(this, mList);
        mLv.setAdapter(mAdapter);
        findViewById(R.id.btn_add).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ShangjiaListActivity.this, AddShangjiaActivity.class));
            }
        });
    }
    
    @Override
    protected void onResume() {
        super.onResume();
        List<ShangJia> list = mDao.queryAllData();
        if (list != null && !list.isEmpty()) {
            mList.clear();
            mList.addAll(list);
        }
        mAdapter.notifyDataSetChanged();
    }
}
