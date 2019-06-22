package com.example;

import android.app.Application;
import com.example.utils.SPUtil;

/**
 * ShouZhangApplication
 *
 * @author fangyuan
 * @date 2019/6/22
 */
public class ShouZhangApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        SPUtil.init(this);
    }
}
