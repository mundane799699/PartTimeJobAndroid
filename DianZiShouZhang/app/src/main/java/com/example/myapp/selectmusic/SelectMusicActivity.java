package com.example.myapp.selectmusic;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import com.example.adapter.SongAdapter;
import com.example.model.Song;
import com.example.myapp.R;
import com.example.utils.MusicUtils;
import java.util.ArrayList;
import java.util.List;

public class SelectMusicActivity extends AppCompatActivity {
    
    private ListView mLv;
    private List<Song> mSongList = new ArrayList<>();
    private SongAdapter mSongAdapter;
    public static final String KEY_SONG = "SONG";
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_music);
        mLv = findViewById(R.id.lv);
        mSongList.clear();
        List<Song> songList = MusicUtils.getmusic(this);
        mSongList.addAll(songList);
        mSongAdapter = new SongAdapter(this, mSongList);
        mLv.setAdapter(mSongAdapter);
        mLv.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Song song = mSongList.get(position);
                Intent intent = new Intent();
                intent.putExtra(KEY_SONG, song);
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }
}
