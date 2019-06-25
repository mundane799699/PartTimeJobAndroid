package com.example.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.example.bean.User;
import com.example.xstrategy.R;
import java.util.List;

/**
 * SongAdapter
 *
 * @author fangyuan
 * @date 2019-06-23
 */
public class UserAdapter extends BaseAdapter {
    Context context;
    List<User> list;
    
    public UserAdapter(Context context, List<User> list) {
        this.context = context;
        this.list = list;
    }
    
    @Override
    public int getCount() {
        return list.size();
    }
    
    @Override
    public User getItem(int i) {
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
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user, parent, false);
            holder = new Myholder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (Myholder) convertView.getTag();
        }
        
        User user = list.get(position);
        holder.tvUsername.setText(user.name);
        holder.tvPassword.setText(user.password);
        return convertView;
    }
    
    class Myholder {
        TextView tvUsername;
        TextView tvPassword;
        
        public Myholder(View convertView) {
            tvUsername = (TextView) convertView.findViewById(R.id.tv_username);
            tvPassword = (TextView) convertView.findViewById(R.id.tv_password);
        }
    }
}
