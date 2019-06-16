package io.github.laucherish.purezhihud.ui.splash;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;
import io.github.laucherish.purezhihud.ui.activity.NewsListActivity;
import io.github.laucherish.purezhihud.ui.login.LoginActivity;
import io.github.laucherish.purezhihud.utils.AppPreference;
import io.github.laucherish.purezhihud.utils.CheckUtils;

/**
 * SplashActivity
 *
 * @author fangyuan
 * @date 2019/6/16
 */
public class SplashActivity extends AppCompatActivity {
    
    private Handler mHandler;
    
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mHandler = new Handler();
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                jump();
            }
        }, 1000);
    }
    
    @Override
    protected void onDestroy() {
        if (mHandler != null) {
            mHandler.removeCallbacksAndMessages(null);
        }
        super.onDestroy();
    }
    
    private void jump() {
        String password = AppPreference.getPassword();
        String username = AppPreference.getUsername();
        boolean ifAutoLogin = CheckUtils.check(username, password);
        if (ifAutoLogin) {
            startActivity(new Intent(this, NewsListActivity.class));
        } else {
            startActivity(new Intent(this, LoginActivity.class));
        }
        finish();
    }
}
