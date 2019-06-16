package io.github.laucherish.purezhihud.ui.login;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.Toast;
import io.github.laucherish.purezhihud.R;
import io.github.laucherish.purezhihud.bean.User;
import io.github.laucherish.purezhihud.db.DbUtils;

/**
 * RegisterActivity
 *
 * @author fangyuan
 * @date 2019/6/16
 */
public class RegisterActivity extends AppCompatActivity {
    
    private EditText mEtName;
    private EditText mEtPwd;
    private EditText mEtPwdAgain;
    
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initView();
    }
    
    private void initView() {
        findViewById(R.id.btn_register).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                register();
            }
        });
        mEtName = (EditText) findViewById(R.id.et_name);
        mEtPwd = (EditText) findViewById(R.id.et_pwd);
        mEtPwdAgain = (EditText) findViewById(R.id.et_pwd_again);
    }
    
    
    
    private void register() {
        String name = getName();
        String firstPwd = getFirstPwd();
        String secondPwd = getSecondPwd();
        if (TextUtils.isEmpty(name)) {
            showMessage("用户名不能为空");
            return;
        } else if (TextUtils.isEmpty(firstPwd) || TextUtils.isEmpty(secondPwd)) {
            showMessage("密码不能为空");
            return;
        } else if (!TextUtils.equals(firstPwd, secondPwd)) {
            showMessage("两次输入的密码不一致");
            return;
        }
        User user = new User(name, firstPwd);
        DbUtils.saveUser(user);
        showMessage("注册成功");
        finish();
    }
    
    private void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
    
    private String getSecondPwd() {
        return mEtPwdAgain.getText().toString();
    }
    
    private String getFirstPwd() {
        return mEtPwd.getText().toString();
    }
    
    private String getName() {
        return mEtName.getText().toString();
    }
}
