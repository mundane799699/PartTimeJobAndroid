package com.example.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.example.R;
import com.example.bean.User;
import java.util.List;

public class UserAdapter extends BaseAdapter {
    
    private List<User> list;
    
    public UserAdapter(List<User> list) {
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
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        User user = list.get(position);
        holder.mTv.setText("用户名" + user.name + "  密码" + user.password);
        return convertView;
    }
    
    static class ViewHolder {
        
        private TextView mTv;
        
        public ViewHolder(View convertView) {
            mTv = convertView.findViewById(R.id.tv);
        }
    }
}
