package com.example.myapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AlertDialog.Builder;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ImageSpan;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.example.base.BaseActivity;
import com.example.dao.NoteDAO;
import com.example.model.Note;
import com.example.utils.ImageUtils;
import com.example.utils.ScreenUtils;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NoteDetailActivity extends BaseActivity {
    
    private int mNoteId;
    private NoteDAO mNoteDao;
    private EditText mEtContent;
    private View mBtnDelete;
    private MediaPlayer mMediaPlayer;
    private TextView mTvSong;
    private View mDivider;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_detail);
        mTvSong = findViewById(R.id.tv_song);
        mDivider = findViewById(R.id.view_divider);
        mMediaPlayer = new MediaPlayer();
        findViewById(R.id.iv_back).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                updateNote();
                finish();
            }
        });
        mBtnDelete = findViewById(R.id.btn_delete);
        TextView tvTitle = findViewById(R.id.tv_title);
        tvTitle.setText("修改手账");
        mEtContent = findViewById(R.id.et_content);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        mNoteId = bundle.getInt(NoteListActivity.FLAG);
        mNoteDao = new NoteDAO(NoteDetailActivity.this);
        Note note = mNoteDao.find(mNoteId);
        initContent(note.flag);
        if (note.hasSong()) {
            play(note.songPath, note.songSinger, note.songName);
        }
        mEtContent.setSelection(mEtContent.getText().length());
        mBtnDelete.setOnClickListener(new OnClickListener() {
            
            @Override
            public void onClick(View arg0) {
                confirmDelete();
            }
        });
    }
    
    @Override
    protected void onDestroy() {
        if (mMediaPlayer != null) {
            mMediaPlayer.stop();
        }
        super.onDestroy();
    }
    
    public void play(String path, String singer, String songName) {
        try {
            //        重置音频文件，防止多次点击会报错
            mMediaPlayer.reset();
            //        调用方法传进播放地址
            mMediaPlayer.setDataSource(path);
            //            异步准备资源，防止卡顿
            mMediaPlayer.prepareAsync();
            //            调用音频的监听方法，音频准备完毕后响应该方法进行音乐播放
            mMediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mediaPlayer) {
                    mediaPlayer.start();
                }
            });
            mTvSong.setVisibility(View.VISIBLE);
            mDivider.setVisibility(View.VISIBLE);
            mTvSong.setText("当前音乐: " + singer + " " + songName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private void initContent(String input) {
        //String regex = "<img src=\\".*?\\"\\/>";
        Pattern p = Pattern.compile("\\<img src=\".*?\"\\/>");
        Matcher m = p.matcher(input);
        //List<String> result = new ArrayList<String>();
        SpannableString spannable = new SpannableString(input);
        while (m.find()) {
            //Log.d("YYPT_RGX", m.group());
            //这里s保存的是整个式子，即<img src="xxx"/>，start和end保存的是下标
            String s = m.group();
            int start = m.start();
            int end = m.end();
            //path是去掉<img src=""/>的中间的图片路径
            String path = s.replaceAll("\\<img src=\"|\"\\/>", "").trim();
            //Log.d("YYPT_AFTER", path);
            
            //利用spannableString和ImageSpan来替换掉这些图片
            int width = ScreenUtils.getScreenWidth(this);
            int height = ScreenUtils.getScreenHeight(this);
            
            try {
                BitmapFactory.Options options = new BitmapFactory.Options();
                Bitmap bitmap = BitmapFactory.decodeFile(path, options);
                bitmap = ImageUtils.zoomImage(bitmap, (width - 32) * 0.8, bitmap.getHeight() / (bitmap.getWidth() / ((width - 32) * 0.8)));
                ImageSpan imageSpan = new ImageSpan(this, bitmap);
                spannable.setSpan(imageSpan, start, end, Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        mEtContent.setText(spannable);
        //content.append("\n");
        //Log.d("YYPT_RGX_SUCCESS",content.getText().toString());
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
