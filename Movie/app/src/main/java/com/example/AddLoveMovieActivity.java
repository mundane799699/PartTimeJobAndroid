package com.example;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import com.example.bean.LoveMovie;
import com.example.dao.LoveMovieDAO;

/**
 * AddActionMovieActivity
 *
 * @author fangyuan
 * @date 2019-06-28
 */
public class AddLoveMovieActivity extends Activity {
    private EditText mEtName;
    private EditText mEtPhone;
    private EditText mEtRelation;
    private LoveMovieDAO mDao;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_love_movie);
        mEtName = (EditText) findViewById(R.id.et_name);
        mEtPhone = (EditText) findViewById(R.id.et_phone);
        mEtRelation = (EditText) findViewById(R.id.et_relation);
        mDao = new LoveMovieDAO(this);
        findViewById(R.id.btn_save).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                save();
            }
        });
    }
    
    private void save() {
        String name = mEtName.getText().toString();
        String describe = mEtPhone.getText().toString();
        String date = mEtRelation.getText().toString();
        LoveMovie movie = new LoveMovie();
        movie.name = name;
        movie.describe = describe;
        movie.date = date;
        mDao.add(movie);
        finish();
    }
}
