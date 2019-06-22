package com.example.myapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AlertDialog.Builder;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.example.base.BaseActivity;
import com.example.dao.NoteDAO;
import com.example.model.Note;

public class NoteDetailActivity extends BaseActivity {
    
    private int mNoteId;
    private NoteDAO mNoteDao;
    private EditText mEtContent;
    private View mBtnDelete;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_detail);
        
        findViewById(R.id.iv_back).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                updateNote();
                finish();
            }
        });
        mBtnDelete = findViewById(R.id.btn_delete);
        TextView tvTitle = findViewById(R.id.tv_title);
        tvTitle.setText("修改备忘录");
        mEtContent = findViewById(R.id.et_content);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        mNoteId = bundle.getInt(NoteListActivity.FLAG);
        mNoteDao = new NoteDAO(NoteDetailActivity.this);
        Note note = mNoteDao.find(mNoteId);
        mEtContent.setText(note.flag);
        mEtContent.setSelection(mEtContent.getText().length());
        mBtnDelete.setOnClickListener(new OnClickListener() {
            
            @Override
            public void onClick(View arg0) {
                confirmDelete();
            }
        });
    }
    
    private void updateNote() {
        Note note = new Note();
        note._id = mNoteId;
        note.flag = mEtContent.getText().toString();
        mNoteDao.update(note);
    }
    
    @Override
    public void onBackPressed() {
        updateNote();
        super.onBackPressed();
    }
    
    private void confirmDelete() {
        Builder builder = new Builder(this);
        builder.setMessage("确定删除该条便笺?").setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mNoteDao.detele(mNoteId);
                Toast.makeText(NoteDetailActivity.this, "删除成功！", Toast.LENGTH_SHORT).show();
                finish();
            }
        }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
