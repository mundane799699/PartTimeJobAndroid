package io.github.laucherish.purezhihud.ui.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AlertDialog.Builder;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import butterknife.Bind;
import io.github.laucherish.purezhihud.R;
import io.github.laucherish.purezhihud.base.BaseActivity;
import io.github.laucherish.purezhihud.ui.login.LoginActivity;
import io.github.laucherish.purezhihud.utils.AppPreference;

/**
 * Created by laucherish on 16/3/23.
 */
public class SettingActivity extends BaseActivity {
    @Bind(R.id.toolbar)
    Toolbar mToolbar;
    private TextView mTvCurrentUser;
    public static final String ACTION_LOGOUT = "logout";
    
    @Override
    protected int getLayoutId() {
        return R.layout.activity_about;
    }

    @Override
    protected void afterCreate(Bundle savedInstanceState) {
        init();
    }

    private void init() {
        setSupportActionBar(mToolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        mTvCurrentUser = (TextView) findViewById(R.id.tv_current_user);
        mTvCurrentUser.setText("当前用户: " + AppPreference.getUsername());
        findViewById(R.id.tv_logout).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmQuit();
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
        intent.setAction(ACTION_LOGOUT);
        sendBroadcast(intent);
        startActivity(new Intent(this, LoginActivity.class));
        finish();
    }

    public static void start(Context context) {
        Intent intent = new Intent(context, SettingActivity.class);
        context.startActivity(intent);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


}
