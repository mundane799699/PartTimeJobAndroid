package com.example.splash;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import com.example.base.BaseActivity;
import com.example.dao.UserDAO;
import com.example.myapp.login.LoginActivity;
import com.example.myapp.main.MainActivity;
import com.example.myapp.R;
import com.example.utils.AppPreference;

/**
 * SplashActivity
 *
 * @author fangyuan
 * @date 2019/6/22
 */
public class SplashActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        String password = AppPreference.getPassword();
        String username = AppPreference.getUsername();
        UserDAO pwdDAO = new UserDAO(SplashActivity.this);
        if (pwdDAO.find(username, password) == null) {
            startActivity(new Intent(this, LoginActivity.class));
        } else {
            startActivity(new Intent(this, MainActivity.class));
        }
        finish();
    }
}
