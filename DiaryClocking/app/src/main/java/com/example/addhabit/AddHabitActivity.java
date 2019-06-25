package com.example.addhabit;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import com.example.base.BaseActivity;
import com.example.dao.HabitDao;
import com.example.model.Habit;
import com.example.myapp.R;
import java.util.List;

public class AddHabitActivity extends BaseActivity {
    
    private EditText mEtHabitName;
    private EditText mEtHabitWords;
    private HabitDao mHabitDao;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_habit);
        mEtHabitName = findViewById(R.id.et_habit_name);
        mEtHabitWords = findViewById(R.id.et_habit_words);
        mHabitDao = new HabitDao(this);
        findViewById(R.id.iv_close).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        findViewById(R.id.btn_add).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                addHabit();
            }
        });
    }
    
    private void addHabit() {
        String habitName = mEtHabitName.getText().toString();
        String habitWords = mEtHabitWords.getText().toString();
        if (TextUtils.isEmpty(habitName)) {
            showMessage("习惯名字不能为空");
            return;
        }
        boolean hasExist = isExist(habitName);
        if (hasExist) {
            showMessage("该习惯名称已存在");
            return;
        }
        Habit habit = new Habit();
        habit.name = habitName;
        habit.words = habitWords;
        mHabitDao.add(habit);
        finish();
    }
    
    private boolean isExist(String habitName) {
        List<Habit> list = mHabitDao.queryAllHabits();
        if (list == null || list.isEmpty()) {
            return false;
        }
        for (Habit habit : list) {
            if (TextUtils.equals(habitName, habit.name)) {
                return true;
            }
        }
        return false;
    }
}
