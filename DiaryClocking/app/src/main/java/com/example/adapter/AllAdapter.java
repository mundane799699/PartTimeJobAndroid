package com.example.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.example.model.Habit;
import com.example.myapp.R;
import java.util.List;

/**
 * NoteListAdapter
 *
 * @author fangyuan
 * @date 2019/6/22
 */
public class AllAdapter extends BaseAdapter {
    private List<Habit> mDataList;
    
    public AllAdapter(List<Habit> list) {
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
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_all, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        Habit habit = mDataList.get(position);
        holder.mTvCardTimes.setText(String.format("%d天", habit.cardtimes));
        holder.mTvName.setText(habit.name);
        holder.mTvWords.setText(habit.words);
        return convertView;
    }
    
    static class ViewHolder {
        
        private TextView mTvName;
        private TextView mTvCardTimes;
        private TextView mTvWords;
        
        public ViewHolder(View convertView) {
            
            mTvName = convertView.findViewById(R.id.tv_name);
            mTvCardTimes = convertView.findViewById(R.id.tv_card_times);
            mTvWords = convertView.findViewById(R.id.tv_words);
        }
    }
}
