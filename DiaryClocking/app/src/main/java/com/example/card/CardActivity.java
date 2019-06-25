package com.example.card;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.widget.Toast;
import com.example.dao.HabitDao;
import com.example.model.Habit;
import com.example.myapp.R;
import com.example.utils.DateUtil;
import com.example.utils.SPUtil;
import java.util.List;

public class CardActivity extends AppCompatActivity {
    
    private TextView mTvWeek;
    private TextView mTvDate;
    private String mCurrentDate;
    private TextView mTvGetCard;
    private HabitDao mHabitDao;
    private View mLlCard;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card);
        mHabitDao = new HabitDao(this);
        findViewById(R.id.iv_close).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mLlCard = findViewById(R.id.ll_card);
        initViewStatus();
        mTvGetCard = findViewById(R.id.tv_get_card);
        mTvWeek = findViewById(R.id.tv_week);
        mTvWeek.setText(DateUtil.getWeek());
        mTvDate = findViewById(R.id.tv_date);
        mCurrentDate = DateUtil.getFormatDate("MM月dd日,yyyy");
        mTvDate.setText(mCurrentDate);
        boolean hasGetCard = SPUtil.getBoolean(mCurrentDate, false);
        if (!hasGetCard) {
            mTvGetCard.setText("领取今日卡片");
            mTvGetCard.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    getCard();
                }
            });
        } else {
            mTvGetCard.setText("已领取");
        }
    }
    
    private void initViewStatus() {
        int todayAllCardTimes = 0;
        List<Habit> list = mHabitDao.queryAllHabits();
        if (list != null) {
            for (Habit habit : list) {
                if (habit.todayStatus != 0) {
                    todayAllCardTimes++;
                }
            }
        }
        if (todayAllCardTimes > 0) {
            mLlCard.setVisibility(View.VISIBLE);
        } else {
            mLlCard.setVisibility(View.GONE);
        }
    }
    
    private void getCard() {
        Toast.makeText(this, "今日卡片已领取", Toast.LENGTH_SHORT).show();
        mTvGetCard.setText("已领取");
        SPUtil.putBoolean(mCurrentDate, true);
    }
}
