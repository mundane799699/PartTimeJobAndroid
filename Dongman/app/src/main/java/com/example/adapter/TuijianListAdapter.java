package com.example.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.R;
import com.example.Utils.MResource;
import com.example.bean.Tuijian;
import java.util.List;

public class TuijianListAdapter extends BaseAdapter {
    
    private List<Tuijian> list;
    
    public TuijianListAdapter(List<Tuijian> list) {
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
        Context context = parent.getContext();
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_tuijian, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        Tuijian tuijian = list.get(position);
        holder.mTv.setText(tuijian.name);
        holder.mIv.setImageResource(MResource.getIdByDrawableName(context, tuijian.drawable));
        return convertView;
    }
    
    static class ViewHolder {
        
        private TextView mTv;
        private ImageView mIv;
        
        public ViewHolder(View convertView) {
            mTv = convertView.findViewById(R.id.tv);
            mIv = convertView.findViewById(R.id.iv);
        }
    }
}
