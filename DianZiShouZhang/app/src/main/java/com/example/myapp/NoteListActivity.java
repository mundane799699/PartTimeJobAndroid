package com.example.myapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;
import com.example.adapter.NoteListAdapter;
import com.example.base.BaseActivity;
import com.example.dao.NoteDAO;
import com.example.model.Note;
import java.util.ArrayList;
import java.util.List;

public class NoteListActivity extends BaseActivity {
    public static final String FLAG = "id";
    private ListView mLvNotes;
    private TextView mTvTitle;
    private List<Note> mNoteList = new ArrayList<>();
    private NoteListAdapter mNoteListAdapter;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_list);
        findViewById(R.id.iv_back).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mTvTitle = findViewById(R.id.tv_title);
        mTvTitle.setText("手账管理");
        mLvNotes = findViewById(R.id.lv_note);
        mLvNotes.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
    
                Note note = mNoteList.get(position);
                Intent intent = new Intent(NoteListActivity.this, NoteDetailActivity.class);
                intent.putExtra(FLAG, note._id);
                startActivity(intent);
            }
        });
        mNoteListAdapter = new NoteListAdapter(mNoteList);
        mLvNotes.setAdapter(mNoteListAdapter);
    }
    
    @Override
    protected void onResume() {
        super.onResume();
        queryAllNotes();
    }
    
    private void queryAllNotes() {
        NoteDAO noteDAO = new NoteDAO(NoteListActivity.this);
        List<Note> noteList = noteDAO.getScrollData();
        if (noteList != null) {
            mNoteList.clear();
            mNoteList.addAll(noteList);
            mNoteListAdapter.notifyDataSetChanged();
        }
    }
}
