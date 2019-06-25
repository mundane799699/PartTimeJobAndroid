package com.example.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.example.R;
import com.example.MainActivity;
import com.example.Utils.AppPreference;
import com.example.Utils.CheckUtils;
import com.example.Utils.SPUtil;
import com.example.base.BaseActivity;
import com.example.bean.User;
import com.example.dao.UserDAO;
import java.util.List;

public class LoginActivity extends BaseActivity {
    
    private Button mBtnRegister;
    private Button mBtnLogin;
    private EditText mEtPwd;
    private EditText mEtUsername;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_land);
        SPUtil.init(this);
        
        mEtPwd = (EditText) findViewById(R.id.et_pwd);
        mEtUsername = (EditText) findViewById(R.id.et_username);
        mBtnLogin = (Button) findViewById(R.id.btn_login);
        mBtnRegister = (Button) findViewById(R.id.btn_register);
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
                    showMessage("���û���δע��");
                    return;
                }
                if (pwdDAO.find(username, password) == null) {
                    Toast.makeText(LoginActivity.this, "�������!", Toast.LENGTH_SHORT).show();
                } else {
                    AppPreference.saveUsername(username);
                    AppPreference.savePassword(password);
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    finish();
                }
            }
        });
    
        String password = AppPreference.getPassword();
        String username = AppPreference.getUsername();
        UserDAO pwdDAO = new UserDAO(this);
        if (pwdDAO.find(username, password) != null) {
            startActivity(new Intent(this, MainActivity.class));
            finish();
        }
    }
}
