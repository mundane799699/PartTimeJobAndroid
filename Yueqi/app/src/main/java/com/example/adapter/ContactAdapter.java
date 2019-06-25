package com.example.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.example.bean.Contact;
import com.example.xstrategy.R;
import java.util.List;

/**
 * SongAdapter
 *
 * @author fangyuan
 * @date 2019-06-23
 */
public class ContactAdapter extends BaseAdapter {
    Context context;
    List<Contact> list;
    
    public ContactAdapter(Context context, List<Contact> list) {
        this.context = context;
        this.list = list;
    }
    
    @Override
    public int getCount() {
        return list.size();
    }
    
    @Override
    public Contact getItem(int i) {
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
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_contact, parent, false);
            holder = new Myholder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (Myholder) convertView.getTag();
        }
        
        Contact contact = list.get(position);
        holder.tvName.setText(contact.name);
        holder.tvPhone.setText(contact.phone);
        holder.tvRelation.setText(contact.relation);
        return convertView;
    }
    
    class Myholder {
        TextView tvName;
        TextView tvPhone;
        TextView tvRelation;
        
        public Myholder(View convertView) {
            tvName = (TextView) convertView.findViewById(R.id.tv_name);
            tvPhone = (TextView) convertView.findViewById(R.id.tv_phone);
            tvRelation = (TextView) convertView.findViewById(R.id.tv_relation);
        }
    }
}
