package com.example.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.example.R;
import com.example.bean.LoveMovie;
import java.util.List;

public class LoveMovieAdapter extends BaseAdapter {
    
    private Context context;
    private List<LoveMovie> list;
    
    public LoveMovieAdapter(Context context, List<LoveMovie> list) {
        this.context = context;
        this.list = list;
    }
    
    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return list.size();
    }
    
    @Override
    public Object getItem(int arg0) {
        // TODO Auto-generated method stub
        return list.get(arg0);
    }
    
    @Override
    public long getItemId(int arg0) {
        return arg0;
    }
    
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_information, parent, false);
        }
        
        LoveMovie movie = list.get(position);
        TextView tv_title = (TextView) convertView.findViewById(R.id.tv_item_title);
        TextView tv_content = (TextView) convertView.findViewById(R.id.tv_item_content);
        TextView tvDate = (TextView) convertView.findViewById(R.id.tv_date);
        tv_title.setText(movie.name);
        tv_content.setText(movie.describe);
        tvDate.setText(movie.date);
        
        return convertView;
    }
}
