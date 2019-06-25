package com.example.lvyou;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import com.example.lvyou.adapter.ArchitectureAdapter;
import com.example.lvyou.bean.Architecture;
import com.example.lvyou.dao.ArchitectureDAO;

import java.util.ArrayList;
import java.util.List;

public class ArchitectureActivity extends Activity implements OnItemClickListener{

    private ListView lv_architecture;
    private ArchitectureAdapter mAdapter;
    private List<Architecture> mList = new ArrayList<Architecture>();
    private ArchitectureDAO mArchitectureDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_architecture);

        setTitle(R.string.jianzhu);
        lv_architecture = (ListView) findViewById(R.id.lv_architecture);
        mAdapter = new ArchitectureAdapter(this, mList);
        lv_architecture.setAdapter(mAdapter);
        lv_architecture.setOnItemClickListener(this);
        initData();
    }

    private void initData() {
        mArchitectureDAO = new ArchitectureDAO(this);
        List<Architecture> list = mArchitectureDAO.queryAllArchitecture();
        if (list == null || list.isEmpty()) {
            initDaoData();
        }
        list = mArchitectureDAO.queryAllArchitecture();
        mList.clear();
        mList.addAll(list);
        mAdapter.notifyDataSetChanged();
    }

    private void initDaoData() {
        Architecture arc1 = new Architecture();
        arc1.drawableName = "jianzhu1";
        arc1.name = getString(R.string.zifeng);
        arc1.describe =
                getString(R.string.zifengd);
        mArchitectureDAO.add(arc1);

        Architecture arc2 = new Architecture();
        arc2.drawableName = "jianzhu2";
        arc2.name = getString(R.string.nanjinyan);
        arc2.describe =
                getString(R.string.nanjinyand);
        mArchitectureDAO.add(arc2);

        Architecture arc3 = new Architecture();
        arc3.drawableName = "jianzhu3";
        arc3.name = getString(R.string.minc);
        arc3.describe =
                getString(R.string.mingcd);
        mArchitectureDAO.add(arc3);
    }


    @Override
    public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
        // TODO Auto-generated method stub
        Architecture arc = mList.get(arg2);
        Intent intent = new Intent(this,ContentActivity.class);
        intent.putExtra("image", arc.drawableName);
        intent.putExtra("title", arc.name);
        intent.putExtra("content", arc.describe);
        intent.putExtra("befor", R.string.jianchu2);
        startActivity(intent);
    }
}
