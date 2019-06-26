package com.example.today;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AlertDialog.Builder;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import com.example.adapter.TodayAdapter;
import com.example.addhabit.AddHabitActivity;
import com.example.dao.HabitDao;
import com.example.model.Habit;
import com.example.myapp.R;
import com.example.utils.DateUtil;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class TodayFragment extends Fragment {
    
    private View mTvAdd;
    private ListView mLvHabits;
    private List<Habit> mList = new ArrayList<>();
    private TodayAdapter mAdapter;
    private HabitDao mHabitDao;
    
    public TodayFragment() {
        // Required empty public constructor
    }
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_today, container, false);
    }
    
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mHabitDao = new HabitDao(getContext());
        mTvAdd = view.findViewById(R.id.tv_add);
        mTvAdd.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                goToAddHabitActivity();
            }
        });
        mLvHabits = view.findViewById(R.id.lv_habit);
        mAdapter = new TodayAdapter(mList);
        mLvHabits.setAdapter(mAdapter);
        mLvHabits.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                changeCardStatus(position);
            }
        });
    }
    
    private void changeCardStatus(int position) {
        Habit habit = mList.get(position);
        String lastCardDate = habit.lastCardDate;
        String today = DateUtil.getFormatDate();
        if (!TextUtils.equals(today, lastCardDate)) {
            // 打卡
            habit.lastCardDate = today;
            habit.cardtimes++;
            mHabitDao.updateHabit(habit);
            queryHabits();
        } else {
            // 取消打开
            confirmCancel(habit);
        }
    }
    
    private void confirmCancel(final Habit habit) {
        Builder builder = new Builder(getContext());
        builder.setMessage("确定要取消打卡吗?").setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                habit.lastCardDate = "";
                habit.cardtimes--;
                mHabitDao.updateHabit(habit);
                queryHabits();
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
    
    @Override
    public void onResume() {
        super.onResume();
        queryHabits();
    }
    
    private void queryHabits() {
        List<Habit> list = mHabitDao.queryAllHabits();
        if (list != null) {
            mList.clear();
            mList.addAll(list);
            mAdapter.notifyDataSetChanged();
        }
    }
    
    private void goToAddHabitActivity() {
        startActivity(new Intent(getContext(), AddHabitActivity.class));
    }
    
}
