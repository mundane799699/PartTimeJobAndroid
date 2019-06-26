package com.example.adapter;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.example.model.Habit;
import com.example.myapp.R;
import com.example.utils.DateUtil;
import java.util.List;

/**
 * NoteListAdapter
 *
 * @author fangyuan
 * @date 2019/6/22
 */
public class TodayAdapter extends BaseAdapter {
    private List<Habit> mDataList;
    
    public TodayAdapter(List<Habit> list) {
        mDataList = list;
    }
    
    @Override
    public int getCount() {
        return mDataList.size();
    }
    
    @Override
    public Habit getItem(int position) {
        return mDataList.get(position);
    }
    
    @Override
    public long getItemId(int position) {
        return position;
    }
    
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_note, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        Habit habit = mDataList.get(position);
        holder.mTvCardTimes.setText(String.format("完成%d天", habit.cardtimes));
        holder.mTvName.setText(habit.name);
        String lastCardDate = habit.lastCardDate;
        String today = DateUtil.getFormatDate();
        if (!TextUtils.equals(today, lastCardDate)) {
            holder.mTvTodayStatus.setText("今日未打卡");
        } else {
            holder.mTvTodayStatus.setText("今日已打卡");
        }
        return convertView;
    }
    
    static class ViewHolder {
        
        private TextView mTvName;
        private TextView mTvCardTimes;
        private TextView mTvTodayStatus;
        
        public ViewHolder(View convertView) {
            
            mTvName = convertView.findViewById(R.id.tv_name);
            mTvCardTimes = convertView.findViewById(R.id.tv_card_times);
            mTvTodayStatus = convertView.findViewById(R.id.tv_today_status);
        }
    }
}
