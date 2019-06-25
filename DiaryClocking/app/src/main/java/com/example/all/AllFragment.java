package com.example.all;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import com.example.adapter.AllAdapter;
import com.example.dao.HabitDao;
import com.example.edit.EditHabitActivity;
import com.example.model.Habit;
import com.example.myapp.R;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class AllFragment extends Fragment {
    
    private ListView mLvHabits;
    private List<Habit> mList = new ArrayList<>();
    private AllAdapter mAdapter;
    private HabitDao mHabitDao;
    
    public AllFragment() {
        // Required empty public constructor
    }
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_all, container, false);
    }
    
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mHabitDao = new HabitDao(getContext());
        mLvHabits = view.findViewById(R.id.lv_habit);
        mAdapter = new AllAdapter(mList);
        mLvHabits.setAdapter(mAdapter);
        mLvHabits.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Habit habit = mList.get(position);
                goToEditHabitActivity(habit);
            }
        });
    }
    
    private void goToEditHabitActivity(Habit habit) {
        Intent intent = new Intent(getContext(), EditHabitActivity.class);
        intent.putExtra(EditHabitActivity.KEY_HABIT, habit);
        startActivity(intent);
    }
    
    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        queryHabits();
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
}
