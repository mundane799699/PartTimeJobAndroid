package com.example.testfinal;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ListView;
import com.example.testfinal.adapter.ShangpinAdapter;
import com.example.testfinal.bean.ShangPin;
import com.example.testfinal.dao.ShangPinDao;
import java.util.ArrayList;
import java.util.List;

/**
 * ShangpinListActivity
 *
 * @author fangyuan
 * @date 2019-06-28
 */
public class ShangpinListActivity extends Activity {
    
    private ListView mLv;
    private List<ShangPin> mList = new ArrayList<ShangPin>();
    private ShangPinDao mShangPinDao;
    private ShangpinAdapter mAdapter;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shangpin_list);
        mLv = (ListView) findViewById(R.id.lv);
        mShangPinDao = new ShangPinDao(this);
        mAdapter = new ShangpinAdapter(this, mList);
        mLv.setAdapter(mAdapter);
        findViewById(R.id.btn_add).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ShangpinListActivity.this, AddShangpinActivity.class));
            }
        });
    }
    
    @Override
    protected void onResume() {
        super.onResume();
        List<ShangPin> list = mShangPinDao.queryAllData();
        if (list != null && !list.isEmpty()) {
            mList.clear();
            mList.addAll(list);
        }
        mAdapter.notifyDataSetChanged();
    }
}
