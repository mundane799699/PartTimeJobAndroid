package com.example.myapp;

import android.Manifest;
import android.Manifest.permission;
import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AlertDialog.Builder;
import android.text.Editable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.ImageSpan;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.example.base.BaseActivity;
import com.example.dao.NoteDAO;
import com.example.model.Note;
import com.example.model.Song;
import com.example.myapp.selectmusic.SelectMusicActivity;
import com.example.utils.EasyPermission;
import com.example.utils.EasyPermission.PermissionResultListener;
import com.example.utils.ImageUtils;
import com.example.utils.ScreenUtils;
import java.io.IOException;

public class AddNoteActivity extends BaseActivity {
    
    private TextView mTvTitle;
    private EditText mEtNote;
    private TextView mTvSave;
    private TextView mTvClear;
    private View mIvInsertImage;
    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE
    };
    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static final int IMAGE_CODE = 99;
    private static final int SELECT_MUSIC_CODE = 100;
    private View mTvInsertMusic;
    private MediaPlayer mMediaPlayer;
    private TextView mTvSong;
    private View mDivider;
    private Song mCurrentSong;
    private View mIvShare;
    
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
        mTvSong = findViewById(R.id.tv_song);
        mTvTitle = findViewById(R.id.tv_title);
        mTvTitle.setText("添加手账");
        mEtNote = findViewById(R.id.et_content);
        mTvSave = findViewById(R.id.btn_save);
        mTvClear = findViewById(R.id.btn_clear);
        mIvInsertImage = findViewById(R.id.iv_insert_pic);
        mTvInsertMusic = findViewById(R.id.tv_insert_music);
        mDivider = findViewById(R.id.view_divider);
        mIvShare = findViewById(R.id.iv_share);
        mIvShare.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                share();
        
            }
        });
        
        mMediaPlayer = new MediaPlayer();
        
        mTvSave.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                String strFlag = mEtNote.getText().toString();
                if (!strFlag.isEmpty()) {
                    NoteDAO flagDAO = new NoteDAO(AddNoteActivity.this);
                    Note note;
                    if (mCurrentSong != null) {
                        note = new Note(flagDAO.getMaxId() + 1, strFlag, mCurrentSong.song, mCurrentSong.singer, mCurrentSong.path);
                    } else {
                        note = new Note(flagDAO.getMaxId() + 1, strFlag, "", "", "");
                    }
                    flagDAO.add(note);
                    Toast.makeText(AddNoteActivity.this, "保存成功!", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(AddNoteActivity.this, "请输入便签!", Toast.LENGTH_SHORT).show();
                }
            }
        });
        mTvClear.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {
                String content = mEtNote.getText().toString();
                if (!TextUtils.isEmpty(content)) {
                    confirmClear();
                }
            }
        });
        mIvInsertImage.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                // 调用图库
                callGallery();
            }
        });
        
        mTvInsertMusic.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                insertMusic();
            }
        });
    }
    
    private void share() {
        String content = mEtNote.getText().toString();
        if (TextUtils.isEmpty(content)) {
            return;
        }
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_SUBJECT, "分享");
        intent.putExtra(Intent.EXTRA_TEXT, content);
        startActivity(Intent.createChooser(intent, "分享"));
    }
    
    private void insertMusic() {
        String[] permissions = { permission.WRITE_EXTERNAL_STORAGE };
        
        EasyPermission.requestPermissions(this, permissions, new PermissionResultListener() {
            @Override
            public void onAccepted() {
                selectMusic();
            }
            
            @Override
            public void onRefused() {
            
            }
        });
    }
    
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermission.dispatchRequestPermissionsResult(this, requestCode, permissions, grantResults);
    }
    
    @Override
    protected void onDestroy() {
        EasyPermission.releaseActivity(this);
        if (mMediaPlayer != null) {
            mMediaPlayer.stop();
        }
        super.onDestroy();
    }
    
    private void selectMusic() {
        Intent intent = new Intent(this, SelectMusicActivity.class);
        startActivityForResult(intent, SELECT_MUSIC_CODE);
    }
    
    // 调用图库
    private void callGallery() {
        int permission_WRITE = ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        int permission_READ = ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE);
        if (permission_WRITE != PackageManager.PERMISSION_GRANTED || permission_READ != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, PERMISSIONS_STORAGE, REQUEST_EXTERNAL_STORAGE);
        }
        Intent getAlbum = new Intent(Intent.ACTION_PICK);
        getAlbum.setType("image/*");
        startActivityForResult(getAlbum, IMAGE_CODE);
    }
    
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case IMAGE_CODE:
                if (resultCode == RESULT_OK) {
                    inserToEditText(data);
                }
                break;
            case SELECT_MUSIC_CODE:
                if (resultCode == RESULT_OK) {
                    Song song = (Song) data.getSerializableExtra(SelectMusicActivity.KEY_SONG);
                    if (song != null) {
                        play(song);
                    }
                }
                break;
            default:
                break;
        }
    }
    
    public void play(Song song) {
        String path = song.path;
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
            mTvSong.setText("当前音乐: " + song.singer + " " + song.song);
            mCurrentSong = song;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private void inserToEditText(Intent data) {
        //参考网址：http://blog.csdn.net/abc__d/article/details/51790806
        Bitmap bm = null;
        // 外界的程序访问ContentProvider所提供数据 可以通过ContentResolver接口
        ContentResolver resolver = getContentResolver();
        try {
            // 获得图片的uri
            Uri originalUri = data.getData();
            bm = MediaStore.Images.Media.getBitmap(resolver, originalUri);
            String[] proj = { MediaStore.Images.Media.DATA };
            // 好像是android多媒体数据库的封装接口，具体的看Android文档
            Cursor cursor = managedQuery(originalUri, proj, null, null, null);
            // 按我个人理解 这个是获得用户选择的图片的索引值
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            // 将光标移至开头 ，这个很重要，不小心很容易引起越界
            cursor.moveToFirst();
            // 最后根据索引值获取图片路径
            String path = cursor.getString(column_index);
            //Log.e("insertIMG", "onActivityResult: ");
            insertImg(path);
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "图片插入失败", Toast.LENGTH_SHORT).show();
        }
    }
    
    //region 插入图片
    private void insertImg(String path) {
        //Log.e("插入图片", "insertImg:" + path);
        String tagPath = "<img src=\"" + path + "\"/>";//为图片路径加上<img>标签
        Bitmap bitmap = BitmapFactory.decodeFile(path);
        if (bitmap != null) {
            SpannableString ss = getBitmapMime(path, tagPath);
            insertPhotoToEditText(ss);
            mEtNote.append("\n");
            //Log.e("YYPT_Insert", content.getText().toString());
            
        } else {
            //Log.d("YYPT_Insert", "tagPath: "+tagPath);
            Toast.makeText(this, "插入失败，无读写存储权限，请到权限中心开启", Toast.LENGTH_LONG).show();
        }
    }
    
    private void insertPhotoToEditText(SpannableString ss) {
        Editable et = mEtNote.getText();
        int start = mEtNote.getSelectionStart();
        et.insert(start, ss);
        mEtNote.setText(et);
        mEtNote.setSelection(start + ss.length());
        mEtNote.setFocusableInTouchMode(true);
        mEtNote.setFocusable(true);
    }
    
    //region 根据图片路径利用SpannableString和ImageSpan来加载图片
    private SpannableString getBitmapMime(String path, String tagPath) {
        SpannableString ss = new SpannableString(tagPath);//这里使用加了<img>标签的图片路径
        
        int width = ScreenUtils.getScreenWidth(this);
        int height = ScreenUtils.getScreenHeight(this);
        
        Log.d("YYPT_IMG_SCREEN", "高度:" + height + ",宽度:" + width);
        
        BitmapFactory.Options options = new BitmapFactory.Options();
        Bitmap bitmap = BitmapFactory.decodeFile(path, options);
        Log.d("YYPT_IMG_IMG", "高度:" + bitmap.getHeight() + ",宽度:" + bitmap.getWidth());
        bitmap = ImageUtils.zoomImage(bitmap, (width - 32) * 0.8, bitmap.getHeight() / (bitmap.getWidth() / ((width - 32) * 0.8)));
        
        //Bitmap bitmap = ImageUtils.getSmallBitmap(path,600,480);


        /*
        //高:754，宽1008
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = 4;
        Bitmap bitmap = BitmapFactory.decodeFile(path,options);
        */
        Log.d("YYPT_IMG_COMPRESS", "高度：" + bitmap.getHeight() + ",宽度:" + bitmap.getWidth());
        
        ImageSpan imageSpan = new ImageSpan(this, bitmap);
        ss.setSpan(imageSpan, 0, tagPath.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        return ss;
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
