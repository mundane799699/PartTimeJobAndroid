package com.dasu.ganhuo.ui.meizi;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AlertDialog.Builder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import com.dasu.ganhuo.R;
import com.dasu.ganhuo.ui.category.CategoryActivity;
import com.dasu.ganhuo.ui.history.HistoryActivity;
import com.dasu.ganhuo.ui.login.LoginActivity;
import com.dasu.ganhuo.ui.reading.ReadingActivity;
import com.dasu.ganhuo.ui.video.VideoActivity;
import com.dasu.ganhuo.utils.AppPreference;

/**
 * MeiziFragment
 *
 * @author fangyuan
 * @date 2019/6/15
 */
public class MeiziFragment extends Fragment {
    private Context mContext;
    private Activity mActivity;
    
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }
    
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mActivity = activity;
    }
    
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_meizi, container, false);
        return view;
    }
    
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
    }
    
    
    private void initView(View view) {
        view.findViewById(R.id.fl_meizi).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(mContext, MeiziActivity.class));
            }
        });
        view.findViewById(R.id.fl_history).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(mContext, HistoryActivity.class));
            }
        });
        view.findViewById(R.id.fl_read).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(mContext, ReadingActivity.class));
            }
        });
        view.findViewById(R.id.fl_category).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(mContext, CategoryActivity.class));
            }
        });
        view.findViewById(R.id.fl_video).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(mContext, VideoActivity.class));
            }
        });
        view.findViewById(R.id.fl_logout).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmQuit();
            }
        });
        
    }
    
    private void confirmQuit() {
        Builder builder = new Builder(mActivity);
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
        startActivity(new Intent(mContext, LoginActivity.class));
        mActivity.finish();
    }
}
