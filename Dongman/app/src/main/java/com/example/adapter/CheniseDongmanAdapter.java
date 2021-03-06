package com.example.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.example.R;
import com.example.bean.ChineseDongman;
import java.util.List;

public class CheniseDongmanAdapter extends BaseAdapter {
    
    private List<ChineseDongman> list;
    
    public CheniseDongmanAdapter(List<ChineseDongman> list) {
        this.list = list;
    }
    
    @Override
    public int getCount() {
        return list.size();
    }
    
    @Override
    public Object getItem(int position) {
        return list.get(position);
    }
    
    @Override
    public long getItemId(int position) {
        return position;
    }
    
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_dongman, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        
        ChineseDongman dongman = list.get(position);
        holder.mTvTitle.setText(dongman.name);
        holder.mTvContent.setText(dongman.describe);
        holder.mTvDate.setText(dongman.date);
        return convertView;
    }
    
    static class ViewHolder {
        
        private TextView mTvTitle;
        private TextView mTvContent;
        private TextView mTvDate;
        
        public ViewHolder(View convertView) {
            mTvTitle = convertView.findViewById(R.id.tv_item_title);
            mTvContent = convertView.findViewById(R.id.tv_item_content);
            mTvDate = convertView.findViewById(R.id.tv_date);
        }
    }
}
