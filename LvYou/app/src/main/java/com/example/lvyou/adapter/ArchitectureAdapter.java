package com.example.lvyou.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.lvyou.R;
import com.example.lvyou.Utils.MResource;
import com.example.lvyou.bean.Architecture;
import java.util.List;

public class ArchitectureAdapter extends BaseAdapter {
    
    private Context context;
    private List<Architecture> list;
    
    public ArchitectureAdapter(Context context, List<Architecture> list) {
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
    
        Architecture ar = list.get(position);
        ImageView iv = (ImageView) convertView.findViewById(R.id.iv_item);
        TextView tv_title = (TextView) convertView.findViewById(R.id.tv_item_title);
        TextView tv_content = (TextView) convertView.findViewById(R.id.tv_item_content);
        
        iv.setImageResource(MResource.getIdByDrawableName(context, ar.drawableName));
        tv_title.setText(ar.name);
        tv_content.setText(ar.describe);
        
        return convertView;
    }
}
