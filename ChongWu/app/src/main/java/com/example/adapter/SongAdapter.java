package com.example.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.example.bean.Song;
import com.example.xstrategy.R;
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
    public Song getItem(int i) {
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
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_song, parent, false);
            holder = new Myholder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (Myholder) convertView.getTag();
        }
        
        Song song = list.get(position);
        holder.tvName.setText(song.name);
        holder.tvSinger.setText(song.singer);
        holder.tvAlbum.setText(song.album);
        return convertView;
    }
    
    class Myholder {
        TextView tvName;
        TextView tvSinger;
        TextView tvAlbum;
        
        public Myholder(View convertView) {
            tvName = (TextView) convertView.findViewById(R.id.tv_name);
            tvSinger = (TextView) convertView.findViewById(R.id.tv_phone);
            tvAlbum = (TextView) convertView.findViewById(R.id.tv_relation);
        }
    }
}
