package com.example.daliantear.shop;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.example.daliantear.R;
import com.example.daliantear.bean.Shop;
import java.util.List;

/**
 * FuzhuangAdapter
 *
 * @author fangyuan
 * @date 2019-06-30
 */
public class ShopAdapter extends BaseAdapter {
    
    Context context;
    List<Shop> list;
    
    public ShopAdapter(Context context, List<Shop> list) {
        this.context = context;
        this.list = list;
    }
    
    @Override
    public int getCount() {
        return list.size();
    }
    
    @Override
    public Shop getItem(int i) {
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
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
            holder = new Myholder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (Myholder) convertView.getTag();
        }
    
        Shop data = list.get(position);
        holder.tvName.setText(data.name);
        holder.tvAddress.setText(data.address);
        holder.tvPhone.setText(data.phone);
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
