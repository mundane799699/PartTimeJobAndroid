package com.example.tuijian;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.R;
import com.example.Utils.MResource;
import com.example.bean.Tuijian;

public class TuijianDetailActivity extends AppCompatActivity {
    
    private static final String KEY_TUIJIAN = "KEY_TUIJIAN";
    private TextView mTvDescribe;
    private ImageView mIv;
    private Tuijian mTuiJian;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tuijian_detail);
        mTvDescribe = findViewById(R.id.tv_describe);
        mIv = findViewById(R.id.iv);
        Intent intent = getIntent();
        mTuiJian = (Tuijian) intent.getSerializableExtra(KEY_TUIJIAN);
        if (mTuiJian != null) {
            mTvDescribe.setText(mTuiJian.describe);
            mIv.setImageResource(MResource.getIdByDrawableName(this, mTuiJian.drawable));
        }
    }
    
    public static void openTuijianDetail(Context context, Tuijian tuijian) {
        Intent intent = new Intent(context, TuijianDetailActivity.class);
        intent.putExtra(KEY_TUIJIAN, tuijian);
        context.startActivity(intent);
    }
}
