package com.example.manageuser;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import com.example.R;
import com.example.bean.User;
import com.example.dao.UserDAO;

public class AddUserActivity extends AppCompatActivity {
    
    private UserDAO mUserDAO;
    private EditText mEtName;
    private EditText mEtPwd;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);
        mUserDAO = new UserDAO(this);
        mEtName = findViewById(R.id.et_name);
        mEtPwd = findViewById(R.id.et_pwd);
        findViewById(R.id.btn_save).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                insertUser();
            }
        });
    }
    
    private void insertUser() {
        String name = mEtName.getText().toString();
        String pwd = mEtPwd.getText().toString();
        User user = new User(name, pwd);
        mUserDAO.insert(user);
        finish();
    }
}
