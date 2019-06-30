package com.example.daliantear.fuzhuang;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ListView;
import com.example.daliantear.R;
import com.example.daliantear.bean.FuZhuang;
import com.example.daliantear.dao.FuzhuangDao;
import java.util.ArrayList;
import java.util.List;

/**
 * FuzhuangListActivity
 *
 * @author fangyuan
 * @date 2019-06-30
 */
public class FuzhuangListActivity extends Activity {
    
    private ListView mLv;
    private List<FuZhuang> mList = new ArrayList<FuZhuang>();
    private FuzhuangDao mDao;
    private FuzhuangAdapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fuzhuang_list);
        findViewById(R.id.btn_add).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                addHanfu();
            }
        });
        mLv = (ListView) findViewById(R.id.lv);
        mDao = new FuzhuangDao(this);
        mAdapter = new FuzhuangAdapter(this, mList);
        mLv.setAdapter(mAdapter);
    }
    
    @Override
    protected void onResume() {
        super.onResume();
        List<FuZhuang> list = mDao.queryAll();
        if (list != null && !list.isEmpty()) {
            mList.clear();
            mList.addAll(list);
        }
        mAdapter.notifyDataSetChanged();
    }
    
    private void addHanfu() {
        startActivity(new Intent(this, AddFuzhuangActivity.class));
    }
}
