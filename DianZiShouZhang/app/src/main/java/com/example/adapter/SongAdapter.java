package com.example.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.example.model.Song;
import com.example.myapp.R;
import com.example.utils.MusicUtils;
import java.util.List;

/**
 * SongAdapter
 *
 * @author fangyuan
 * @date 2019-06-23
 */
public class SongAdapter extends BaseAdapter {
    Context context;
    List<Song> list;
    
    public SongAdapter(Context context, List<Song> list) {
        this.context = context;
        this.list = list;
    }
    
    @Override
    public int getCount() {
        return list.size();
    }
    
    @Override
    public Object getItem(int i) {
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
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.text, parent, false);
            holder = new Myholder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (Myholder) convertView.getTag();
        }
        
        holder.t_song.setText(list.get(position).song);
        holder.t_singer.setText(list.get(position).singer);
        String time = MusicUtils.formatTime(list.get(position).duration);
        
        holder.t_duration.setText(time);
        holder.t_position.setText(position + 1 + "");
        
        return convertView;
    }
    
    class Myholder {
        TextView t_position;
        TextView t_song;
        TextView t_singer;
        TextView t_duration;
        
        public Myholder(View convertView) {
            t_position = convertView.findViewById(R.id.t_postion);
            t_song = convertView.findViewById(R.id.t_song);
            t_singer = convertView.findViewById(R.id.t_singer);
            t_duration = convertView.findViewById(R.id.t_duration);
        }
    }
}
