package com.example.myapp.main;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AlertDialog.Builder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.base.BaseActivity;
import com.example.myapp.AddNoteActivity;
import com.example.myapp.Map;
import com.example.myapp.NoteListActivity;
import com.example.myapp.R;
import com.example.myapp.login.LoginActivity;
import com.example.utils.AppPreference;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity {
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        GridView gvInfo;
        String[] titles = new String[] { "添加手账", "手账管理",  "地图", "退出登录" };
        int[] images = new int[] { R.drawable.flag, R.drawable.flagm, R.drawable.map, R.drawable.quit };
        gvInfo = findViewById(R.id.gv_info);
        
        PictureAdapter adapter = new PictureAdapter(titles, images, this);
        gvInfo.setAdapter(adapter);
        gvInfo.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = null;
                switch (position) {
                    case 0:
                        intent = new Intent(MainActivity.this, AddNoteActivity.class);
                        startActivity(intent);
                        break;
                    case 1:
                        intent = new Intent(MainActivity.this, NoteListActivity.class);
                        startActivity(intent);
                        break;
                    case 2:
                        intent = new Intent(MainActivity.this, Map.class);
                        startActivity(intent);
                        break;
                    case 3:
                        confirmQuit();
                        break;
                }
            }
        });
    }
    
    private void confirmQuit() {
        Builder builder = new Builder(this);
        builder.setMessage("确定要退出登录吗?").setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                logout();
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
    
    private void logout() {
        AppPreference.clearUserInfo();
        Intent intent = new Intent();
        sendBroadcast(intent);
        startActivity(new Intent(this, LoginActivity.class));
        finish();
    }
    
    class ViewHolder {
        public TextView title;
        public ImageView image;
    }
    
    class Picture {
        private String title;
        private int imageId;
        
        public Picture() {
            super();
        }
        
        public Picture(String title, int imageId) {
            super();
            this.title = title;
            this.imageId = imageId;
        }
        
        public String getTitle() {
            return title;
        }
        
        public void setTitle(String title) {
            this.title = title;
        }
        
        public int getImageId() {
            return imageId;
        }
        
        public void setimageId(int imageId) {
            this.imageId = imageId;
        }
    }
    
    class PictureAdapter extends BaseAdapter {
        private LayoutInflater inflater;
        private List<Picture> pictures;
        
        public PictureAdapter(String[] titles, int[] images, Context context) {
            super();
            pictures = new ArrayList<>();
            inflater = LayoutInflater.from(context);
            for (int i = 0; i < images.length; i++) {
                Picture picture = new Picture(titles[i], images[i]);
                pictures.add(picture);
            }
        }
        
        @Override
        public int getCount() {
            if (null != pictures) {
                return pictures.size();
            } else {
                return 0;
            }
        }
        
        @Override
        public Object getItem(int arg0) {
            return pictures.get(arg0);
        }
        
        @Override
        public long getItemId(int arg0) {
            return arg0;
        }
        
        @Override
        public View getView(int arg0, View arg1, ViewGroup arg2) {
            ViewHolder viewHolder;
            if (arg1 == null) {
                arg1 = inflater.inflate(R.layout.gvitem, null);
                viewHolder = new ViewHolder();
                viewHolder.title = (TextView) arg1.findViewById(R.id.ItemTitle);
                viewHolder.image = (ImageView) arg1.findViewById(R.id.ItemImage);
                arg1.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) arg1.getTag();
            }
            viewHolder.title.setText(pictures.get(arg0).getTitle());
            viewHolder.image.setImageResource(pictures.get(arg0).getImageId());
            return arg1;
        }
    }
}
