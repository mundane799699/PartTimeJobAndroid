package com.example.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.xstrategy.R;
import com.example.Utils.MResource;
import com.example.bean.ChuiGuanYueQi;
import java.util.List;

public class FoodAdapter extends BaseAdapter {
    
    private Context context;
    private List<ChuiGuanYueQi> list;
    
    public FoodAdapter(Context context, List<ChuiGuanYueQi> list) {
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
    
        ChuiGuanYueQi food = list.get(position);
        ImageView iv = (ImageView) convertView.findViewById(R.id.iv_item);
        TextView tv_title = (TextView) convertView.findViewById(R.id.tv_item_title);
        TextView tv_content = (TextView) convertView.findViewById(R.id.tv_item_content);
        
        iv.setImageResource(MResource.getIdByDrawableName(context, food.drawableName));
        tv_title.setText(food.name);
        tv_content.setText(food.describe);
        
        return convertView;
    }
}
