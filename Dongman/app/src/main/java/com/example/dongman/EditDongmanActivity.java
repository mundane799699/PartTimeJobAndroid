package com.example.dongman;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import com.example.R;
import com.example.bean.ChineseDongman;
import com.example.dao.ChineseDongmanDAO;

public class EditDongmanActivity extends AppCompatActivity {
    private static final String KEY_DONGMAN = "KEY_DONGMAN";
    private EditText mEtName;
    private EditText mEtDescribe;
    private EditText mEtDate;
    private ChineseDongman mDongman;
    private ChineseDongmanDAO mDAO;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_dongman);
        mDAO = new ChineseDongmanDAO(this);
        mEtName = findViewById(R.id.et_name);
        mEtDescribe = findViewById(R.id.et_describe);
        mEtDate = findViewById(R.id.et_date);
        Intent intent = getIntent();
        mDongman = (ChineseDongman) intent.getSerializableExtra(KEY_DONGMAN);
        if (mDongman != null) {
            mEtName.setText(mDongman.name);
            mEtDescribe.setText(mDongman.describe);
            mEtDate.setText(mDongman.date);
        }
        findViewById(R.id.btn_update).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                updateDongman();
            }
        });
    }
    
    private void updateDongman() {
        mDongman.name = mEtName.getText().toString();
        mDongman.describe = mEtDescribe.getText().toString();
        mDongman.date = mEtDate.getText().toString();
        mDAO.updateDongman(mDongman);
        finish();
    }
    
    public static void editDongman(Context context, ChineseDongman dongman) {
        Intent intent = new Intent(context, EditDongmanActivity.class);
        intent.putExtra(KEY_DONGMAN, dongman);
        context.startActivity(intent);
    }
}
