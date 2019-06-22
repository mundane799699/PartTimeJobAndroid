package com.example.myapp;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AlertDialog.Builder;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.example.base.BaseActivity;
import com.example.dao.NoteDAO;
import com.example.model.Note;

public class AddNoteActivity extends BaseActivity {
    
    private TextView mTvTitle;
    private EditText mEtNote;
    private Button mBtnSave;
    private Button mBtnClear;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);
        findViewById(R.id.iv_back).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mTvTitle = findViewById(R.id.tv_title);
        mTvTitle.setText("添加备忘录");
        mEtNote = findViewById(R.id.et_content);
        mBtnSave = findViewById(R.id.btn_save);
        mBtnClear = findViewById(R.id.btn_clear);
        
        mBtnSave.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                String strFlag = mEtNote.getText().toString();
                if (!strFlag.isEmpty()) {
                    NoteDAO flagDAO = new NoteDAO(AddNoteActivity.this);
                    Note tb_flag = new Note(flagDAO.getMaxId() + 1, strFlag);
                    flagDAO.add(tb_flag);
                    Toast.makeText(AddNoteActivity.this, "保存成功!", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(AddNoteActivity.this, "请输入便签!", Toast.LENGTH_SHORT).show();
                }
            }
        });
        mBtnClear.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {
                String content = mEtNote.getText().toString();
                if (!TextUtils.isEmpty(content)) {
                    confirmClear();
                }
            }
        });
    }
    
    private void confirmClear() {
        Builder builder = new Builder(this);
        builder.setMessage("确定清空内容?").setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mEtNote.setText("");
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
