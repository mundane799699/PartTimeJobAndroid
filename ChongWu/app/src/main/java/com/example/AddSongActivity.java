package com.example;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import com.example.base.BaseActivity;
import com.example.bean.Song;
import com.example.dao.SongDao;

/**
 * AddSongActivity
 *
 * @author fangyuan
 * @date 2019-06-25
 */
public class AddSongActivity extends BaseActivity {
    
    private EditText mEtName;
    private EditText mEtPhone;
    private EditText mEtRelation;
    private SongDao mDao;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_song);
        mEtName = (EditText) findViewById(R.id.et_name);
        mEtPhone = (EditText) findViewById(R.id.et_phone);
        mEtRelation = (EditText) findViewById(R.id.et_relation);
        mDao = new SongDao(this);
        findViewById(R.id.btn_save).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                save();
            
            }
        });
    }
    
    private void save() {
        String name = mEtName.getText().toString();
        String phone = mEtPhone.getText().toString();
        String relation = mEtRelation.getText().toString();
        if (TextUtils.isEmpty(name) || TextUtils.isEmpty(phone) || TextUtils.isEmpty(relation)) {
            showMessage(getString(R.string.parameter_empty));
            return;
        }
        Song song = new Song();
        song.name = name;
        song.singer = phone;
        song.album = relation;
        mDao.add(song);
        finish();
    }
}
