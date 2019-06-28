package com.example.testfinal.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.example.testfinal.R;
import com.example.testfinal.bean.ShangPin;
import java.util.List;

/**
 * SongAdapter
 *
 * @author fangyuan
 * @date 2019-06-23
 */
public class ShangpinAdapter extends BaseAdapter {
    Context context;
    List<ShangPin> list;
    
    public ShangpinAdapter(Context context, List<ShangPin> list) {
        this.context = context;
        this.list = list;
    }
    
    @Override
    public int getCount() {
        return list.size();
    }
    
    @Override
    public ShangPin getItem(int i) {
        return list.get(i);
    }
    
    @Override
    public long getItemId(int i) {
        return i;
    }
    
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Myholder holder;
        
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_shangpin, parent, false);
            holder = new Myholder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (Myholder) convertView.getTag();
        }
    
        ShangPin shangPin = list.get(position);
        holder.tvName.setText(shangPin.name);
        holder.tvPrice.setText(shangPin.price);
        holder.tvCount.setText(shangPin.count);
        return convertView;
    }
    
    class Myholder {
        TextView tvName;
        TextView tvPrice;
        TextView tvCount;
        
        public Myholder(View convertView) {
            tvName = (TextView) convertView.findViewById(R.id.tv_name);
            tvPrice = (TextView) convertView.findViewById(R.id.tv_phone);
            tvCount = (TextView) convertView.findViewById(R.id.tv_relation);
        }
    }
}
