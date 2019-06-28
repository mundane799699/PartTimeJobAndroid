package com.example.testfinal.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.example.testfinal.R;
import com.example.testfinal.bean.ShangJia;
import java.util.List;

/**
 * SongAdapter
 *
 * @author fangyuan
 * @date 2019-06-23
 */
public class ShangJiaAdapter extends BaseAdapter {
    Context context;
    List<ShangJia> list;
    
    public ShangJiaAdapter(Context context, List<ShangJia> list) {
        this.context = context;
        this.list = list;
    }
    
    @Override
    public int getCount() {
        return list.size();
    }
    
    @Override
    public ShangJia getItem(int i) {
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
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_shangjia, parent, false);
            holder = new Myholder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (Myholder) convertView.getTag();
        }
    
        ShangJia shangJia = list.get(position);
        holder.tvName.setText(shangJia.name);
        holder.tvAddress.setText(shangJia.address);
        holder.tvPhone.setText(shangJia.phone);
        return convertView;
    }
    
    class Myholder {
        TextView tvName;
        TextView tvAddress;
        TextView tvPhone;
        
        public Myholder(View convertView) {
            tvName = (TextView) convertView.findViewById(R.id.tv_name);
            tvAddress = (TextView) convertView.findViewById(R.id.tv_phone);
            tvPhone = (TextView) convertView.findViewById(R.id.tv_relation);
        }
    }
}
