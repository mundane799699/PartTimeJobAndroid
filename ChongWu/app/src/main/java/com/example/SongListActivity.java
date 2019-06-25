package com.example;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ListView;
import com.example.adapter.SongAdapter;
import com.example.bean.Song;
import com.example.dao.SongDao;
import com.example.xstrategy.R;
import java.util.ArrayList;
import java.util.List;

/**
 * SingerListActivity
 *
 * @author fangyuan
 * @date 2019-06-25
 */
public class SongListActivity extends Activity {
    
    private ListView mLv;
    private List<Song> mList = new ArrayList<Song>();
    private SongAdapter mAdapter;
    private SongDao mDao;
    private View mBtnAdd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song_list);
        mDao = new SongDao(this);
        mLv = (ListView) findViewById(R.id.lv);
        mBtnAdd = findViewById(R.id.btn_add);
        mBtnAdd.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                goToAddSongActivity();
            }
        });
        mAdapter = new SongAdapter(this, mList);
        mLv.setAdapter(mAdapter);
    }
    
    @Override
    protected void onResume() {
        super.onResume();
        queryAllContacts();
    }
    
    private void queryAllContacts() {
        List<Song> list = mDao.queryAllSongs();
        if (list != null && !list.isEmpty()) {
            mList.clear();
            mList.addAll(list);
        }
        mAdapter.notifyDataSetChanged();
    }
    
    private void goToAddSongActivity() {
        startActivity(new Intent(this, AddSongActivity.class));
    }
}
