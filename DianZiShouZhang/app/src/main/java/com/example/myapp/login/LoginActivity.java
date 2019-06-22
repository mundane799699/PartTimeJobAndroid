package com.example.myapp.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.example.base.BaseActivity;
import com.example.dao.UserDAO;
import com.example.model.User;
import com.example.myapp.main.MainActivity;
import com.example.myapp.R;
import com.example.myapp.register.RegisterActivity;
import com.example.utils.AppPreference;
import com.example.utils.CheckUtils;
import java.util.List;

public class LoginActivity extends BaseActivity {
    
    private Button mBtnRegister;
    private Button mBtnLogin;
    private EditText mEtPwd;
    private EditText mEtUsername;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mEtPwd = findViewById(R.id.et_pwd);
        mEtUsername = findViewById(R.id.et_username);
        mBtnLogin = findViewById(R.id.btn_login);
        mBtnRegister = findViewById(R.id.btn_register);
        mBtnRegister.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
        mBtnLogin.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = mEtUsername.getText().toString();
                String password = mEtPwd.getText().toString();
                UserDAO pwdDAO = new UserDAO(LoginActivity.this);
                List<User> allRegisterUsers = pwdDAO.queryAllUsers();
                boolean ifHasThisUser = CheckUtils.checkHasThisUser(allRegisterUsers, username);
                if (!ifHasThisUser) {
                    showMessage("该用户还未注册");
                    return;
                }
                if (pwdDAO.find(username, password) == null) {
                    Toast.makeText(LoginActivity.this, "密码错误!", Toast.LENGTH_SHORT).show();
                } else {
                    AppPreference.saveUsername(username);
                    AppPreference.savePassword(password);
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    finish();
                }
            }
        });
    }
}
