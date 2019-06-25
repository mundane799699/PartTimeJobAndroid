package com.example.login;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.bean.User;
import com.example.xstrategy.R;
import com.example.base.BaseActivity;
import com.example.dao.UserDAO;

import java.util.List;

public class RegisterActivity extends BaseActivity {

    private Button mBtnRegister;
    private EditText mEtUserName;
    private EditText mEtPwd;
    private EditText mEtPwdAgain;
    private UserDAO mUserDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mUserDao = new UserDAO(RegisterActivity.this);

        mBtnRegister = (Button) findViewById(R.id.btn_register);
        mEtUserName = (EditText) findViewById(R.id.et_username);
        mEtPwd = (EditText) findViewById(R.id.et_pwd);
        mEtPwdAgain = (EditText) findViewById(R.id.et_pwd_agagin);
        mBtnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String username = mEtUserName.getText().toString();
                String password = mEtPwd.getText().toString();
                String secondPwd = mEtPwdAgain.getText().toString();
                if (TextUtils.isEmpty(username)) {
                    showMessage(getString(R.string.usernamenotempty));
                    return;
                } else if (TextUtils.isEmpty(password) || TextUtils.isEmpty(secondPwd)) {
                    showMessage(getString(R.string.passwordnotempty));
                    return;
                } else if (!TextUtils.equals(password, secondPwd)) {
                    showMessage(getString(R.string.passwordnotsame));
                    return;
                }
                if (checkHasThisUserName(username)) {
                    showMessage(getString(R.string.hasregistered));
                    return;
                }
                mUserDao.add(username, password);
                Toast.makeText(RegisterActivity.this, getString(R.string.registersuccess), Toast.LENGTH_SHORT).show();
                finish();

            }
        });
    }

    private boolean checkHasThisUserName(String username) {
        List<User> users = mUserDao.queryAllUsers();
        if (users != null && !users.isEmpty()) {
            for (User user : users) {
                if (TextUtils.equals(username, user.name)) {
                    return true;
                }
            }
        }
        return false;
    }

}
