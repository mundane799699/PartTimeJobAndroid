package com.example.myapp.register;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.example.base.BaseActivity;
import com.example.dao.UserDAO;
import com.example.myapp.R;

public class RegisterActivity extends BaseActivity {
    
    private Button mBtnRegister;
    private EditText mEtUserName;
    private EditText mEtPwd;
    private EditText mEtPwdAgain;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mBtnRegister = findViewById(R.id.btn_register);
        mEtUserName = findViewById(R.id.et_username);
        mEtPwd = findViewById(R.id.et_pwd);
        mEtPwdAgain = findViewById(R.id.et_pwd_agagin);
        mBtnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserDAO pwdDAO = new UserDAO(RegisterActivity.this);
                String username = mEtUserName.getText().toString();
                String password = mEtPwd.getText().toString();
                String secondPwd = mEtPwdAgain.getText().toString();
                if (TextUtils.isEmpty(username)) {
                    showMessage("用户名不能为空");
                    return;
                } else if (TextUtils.isEmpty(password) || TextUtils.isEmpty(secondPwd)) {
                    showMessage("密码不能为空");
                    return;
                } else if (!TextUtils.equals(password, secondPwd)) {
                    showMessage("两次输入的密码不一致");
                    return;
                }
                if (pwdDAO.add(username, password) == null) {
                    Toast.makeText(RegisterActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(RegisterActivity.this, "注册失败", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
