package com.example.mine;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import com.example.dao.HabitDao;
import com.example.model.Habit;
import com.example.myapp.R;
import com.example.utils.DateUtil;
import com.example.utils.SPUtil;
import java.util.List;

public class CardFragment extends Fragment {
    
    private TextView mTvWeek;
    private TextView mTvDate;
    private String mCurrentDate;
    private TextView mTvGetCard;
    private HabitDao mHabitDao;
    private View mLlCard;
    
    public CardFragment() {
        // Required empty public constructor
    }
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_mine, container, false);
    }
    
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mHabitDao = new HabitDao(getContext());
        mLlCard = view.findViewById(R.id.ll_card);
        initViewStatus();
        mTvGetCard = view.findViewById(R.id.tv_get_card);
        mTvWeek = view.findViewById(R.id.tv_week);
        mTvWeek.setText(DateUtil.getWeek());
        mTvDate = view.findViewById(R.id.tv_date);
        mCurrentDate = DateUtil.getFormatDate();
        mTvDate.setText(mCurrentDate);
        initCardGet();
    }
    
    private void initCardGet() {
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
            mLlCard.setVisibility(View.VISIBLE);
        }
    }
    
    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        initViewStatus();
        initCardGet();
    }
    
    private void initViewStatus() {
        int todayAllCardTimes = 0;
        List<Habit> list = mHabitDao.queryAllHabits();
        if (list != null) {
            for (Habit habit : list) {
                String lastCardDate = habit.lastCardDate;
                String today = DateUtil.getFormatDate();
                if (TextUtils.equals(today, lastCardDate)) {
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
        Toast.makeText(getContext(), "今日卡片已领取", Toast.LENGTH_SHORT).show();
        mTvGetCard.setText("已领取");
        SPUtil.putBoolean(mCurrentDate, true);
    }
}
