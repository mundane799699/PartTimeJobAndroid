package com.example.dongman;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import com.example.R;
import com.example.adapter.CheniseDongmanAdapter;
import com.example.base.BaseActivity;
import com.example.bean.ChineseDongman;
import com.example.dao.ChineseDongmanDAO;
import com.example.dialog.UpdateDeleteDialog;
import com.example.dialog.UpdateDeleteDialog.OnDialogClickListener;
import java.util.ArrayList;
import java.util.List;

public class ChineseDongmanActivity extends BaseActivity {
    
    private ListView mLv;
    private List<ChineseDongman> mDataList = new ArrayList<>();
    private ChineseDongmanDAO mDao;
    private CheniseDongmanAdapter mAdapter;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scenic);
        mDao = new ChineseDongmanDAO(this);
        mLv = findViewById(R.id.lv_strategy);
        mAdapter = new CheniseDongmanAdapter(mDataList);
        mLv.setAdapter(mAdapter);
        mLv.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                showUpdateOrDeleteDialog(position);
            }
        });
        findViewById(R.id.btn_add).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                addChineseDongman();
            }
        });
    }
    
    private void showUpdateOrDeleteDialog(final int position) {
        UpdateDeleteDialog dialog = new UpdateDeleteDialog(this);
        dialog.setOnDialogClickListener(new OnDialogClickListener() {
            @Override
            public void onUpdate() {
                updateDongman(position);
            }
            
            @Override
            public void onDelete() {
                deleteDongman(position);
            }
        });
        dialog.show();
    }
    
    private void updateDongman(int position) {
        ChineseDongman dongman = mDataList.get(position);
        EditDongmanActivity.editDongman(this, dongman);
    }
    
    private void deleteDongman(int position) {
        ChineseDongman dongman = mDataList.get(position);
        mDao.deleteDongman(dongman);
        queryData();
    }
    
    @Override
    protected void onResume() {
        super.onResume();
        queryData();
    }
    
    private void addChineseDongman() {
        
        startActivity(new Intent(this, AddChineseDongmanActivity.class));
    }
    
    private void queryData() {
        List<ChineseDongman> list = mDao.queryAll();
        mDataList.clear();
        if (list != null && !list.isEmpty()) {
            mDataList.addAll(list);
        }
        mAdapter.notifyDataSetChanged();
    }
}