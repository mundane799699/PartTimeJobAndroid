package com.example.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.example.R;
import com.example.bean.WuyangYundong;
import java.util.List;

public class WuYangAdapter extends BaseAdapter {
    
    private Context context;
    private List<WuyangYundong> list;
    
    public WuYangAdapter(Context context, List<WuyangYundong> list) {
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
        // TODO Auto-generated method stub
        return arg0;
    }
    
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_information, parent, false);
        }
        
        WuyangYundong yundong = list.get(position);
        TextView tv_title = (TextView) convertView.findViewById(R.id.tv_item_title);
        TextView tv_content = (TextView) convertView.findViewById(R.id.tv_item_content);
        TextView tvEffect = (TextView) convertView.findViewById(R.id.tv_effect);
        tv_title.setText(yundong.name);
        tv_content.setText(yundong.describe);
        tvEffect.setText(yundong.effect);
        
        return convertView;
    }
}
