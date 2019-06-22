package com.example.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.example.model.Note;
import com.example.myapp.R;
import java.util.List;

/**
 * NoteListAdapter
 *
 * @author fangyuan
 * @date 2019/6/22
 */
public class NoteListAdapter extends BaseAdapter {
    private List<Note> mNoteList;
    
    public NoteListAdapter(List<Note> noteList) {
        mNoteList = noteList;
    }
    
    @Override
    public int getCount() {
        return mNoteList.size();
    }
    
    @Override
    public Note getItem(int position) {
        return mNoteList.get(position);
    }
    
    @Override
    public long getItemId(int position) {
        return position;
    }
    
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_note, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        Note note = mNoteList.get(position);
        holder.mTvIndex.setText(note._id + ". ");
        holder.mTvContent.setText(note.flag);
        return convertView;
    }
    
    static class ViewHolder {
        
        private TextView mTvContent;
        private TextView mTvIndex;
        
        public ViewHolder(View convertView) {
            
            mTvContent = convertView.findViewById(R.id.tv_content);
            mTvIndex = convertView.findViewById(R.id.tv_index);
        }
    }
}
