package com.dasu.ganhuo.ui.load;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Window;
import android.view.WindowManager;
import com.dasu.ganhuo.R;
import com.dasu.ganhuo.mode.logic.load.LoadController;
import com.dasu.ganhuo.ui.base.ActivityStack;
import com.dasu.ganhuo.ui.base.BaseActivity;
import com.dasu.ganhuo.ui.home.HomeActivity;
import com.dasu.ganhuo.ui.login.LoginActivity;
import com.dasu.ganhuo.utils.AppPreference;
import com.dasu.ganhuo.utils.CheckUtils;

/**
 * Created by dasu on 2017/4/14.
 */

public class LoadActivity extends BaseActivity {
    private static final String TAG = "LoadActivity";
    
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_load);
        new LoadController(this).loadBaseData();
    }
    
    public void onLoadFinish() {
        String password = AppPreference.getPassword();
        String username = AppPreference.getUsername();
        boolean ifAutoLogin = CheckUtils.check(username, password);
        if (ifAutoLogin) {
            startActivity(new Intent(mContext, HomeActivity.class));
            ActivityStack.getInstance().popAndFinishActivity();
        } else {
            startActivity(new Intent(this, LoginActivity.class));
            finish();
        }
        
    }
    
    
}
