package com.example.edit;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AlertDialog.Builder;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import com.example.base.BaseActivity;
import com.example.dao.HabitDao;
import com.example.model.Habit;
import com.example.myapp.R;

public class EditHabitActivity extends BaseActivity {
    public static final String KEY_HABIT = "HABIT";
    
    private TextView mTvHabitName;
    private TextView mTvHabitWords;
    private HabitDao mHabitDao;
    private Habit mCurrentHabit;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_habit);
        mCurrentHabit = (Habit) getIntent().getSerializableExtra(KEY_HABIT);
        mTvHabitName = findViewById(R.id.et_habit_name);
        mTvHabitWords = findViewById(R.id.et_habit_words);
        mTvHabitName.setText(mCurrentHabit.name);
        mTvHabitWords.setText(mCurrentHabit.words);
        mHabitDao = new HabitDao(this);
        findViewById(R.id.iv_close).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        findViewById(R.id.btn_delete).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmDeleteHabit();
            }
        });
    }
    
    private void confirmDeleteHabit() {
        Builder builder = new Builder(this);
        builder.setMessage("确定要删除该习惯吗?").setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mHabitDao.detele(mCurrentHabit);
                finish();
            }
        }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
    
}
