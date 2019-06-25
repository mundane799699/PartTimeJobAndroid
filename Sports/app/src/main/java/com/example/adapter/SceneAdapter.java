package com.example.adapter;

import com.example.R;
import com.example.Utils.MResource;
import com.example.bean.WuyangYundong;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class SceneAdapter extends BaseAdapter {
    
    private Context context;
    private List<WuyangYundong> list;
    
    public SceneAdapter(Context context, List<WuyangYundong> list) {
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
        
        WuyangYundong scene = list.get(position);
        ImageView iv = (ImageView) convertView.findViewById(R.id.iv_item);
        TextView tv_title = (TextView) convertView.findViewById(R.id.tv_item_title);
        TextView tv_content = (TextView) convertView.findViewById(R.id.tv_item_content);
        
        iv.setImageResource(MResource.getIdByDrawableName(context, scene.drawableName));
        tv_title.setText(scene.name);
        tv_content.setText(scene.describe);
        
        return convertView;
    }
}
