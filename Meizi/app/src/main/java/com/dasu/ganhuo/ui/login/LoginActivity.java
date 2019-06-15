package com.dasu.ganhuo.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.Toast;
import com.dasu.ganhuo.R;
import com.dasu.ganhuo.ui.home.HomeActivity;
import com.dasu.ganhuo.utils.AppPreference;
import com.dasu.ganhuo.utils.CheckUtils;

/**
 * LoginActivity
 *
 * @author fangyuan
 * @date 2019/6/15
 */
public class LoginActivity extends AppCompatActivity {
    
    private EditText mEtName;
    private EditText mEtPwd;
    private View mBtnLogin;
    
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
    }
    
    private void initView() {
        mEtName = (EditText) findViewById(R.id.et_name);
        mEtPwd = (EditText) findViewById(R.id.et_pwd);
        mBtnLogin = findViewById(R.id.btn_login);
        mBtnLogin.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });
        findViewById(R.id.tv_register).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                register();
            }
        });
    }
    
    private void register() {
        startActivity(new Intent(this, RegisterActivity.class));
    }
    
    private void login() {
        String name = getName();
        String password = getPwd();
        if (TextUtils.isEmpty(name)) {
            showMessage("用户名不能为空");
            return;
        } else if (TextUtils.isEmpty(password)) {
            showMessage("密码不能为空");
            return;
        }
        boolean ifHasThisUser = CheckUtils.checkHasThisUser(name);
        if (!ifHasThisUser) {
            showMessage("该用户还未注册");
            return;
        }
        boolean check = CheckUtils.check(name, password);
        if (!check) {
            showMessage("密码错误");
        }
        AppPreference.saveUsername(name);
        AppPreference.savePassword(password);
        startActivity(new Intent(this, HomeActivity.class));
        finish();
    }
    
    private void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
    
    private String getPwd() {
        return mEtPwd.getText().toString();
    }
    
    private String getName() {
        return mEtName.getText().toString();
    }
}
