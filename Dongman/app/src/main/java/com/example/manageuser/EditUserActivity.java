package com.example.manageuser;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import com.example.R;
import com.example.bean.User;
import com.example.dao.UserDAO;

public class EditUserActivity extends AppCompatActivity {
    private static final String KEY_USER = "USER";
    private User mUser;
    private EditText mEtName;
    private EditText mEtPwd;
    private UserDAO mUserDAO;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_user);
        mUserDAO = new UserDAO(this);
        Intent intent = getIntent();
        mUser = (User) intent.getSerializableExtra(KEY_USER);
        mEtName = findViewById(R.id.et_name);
        mEtPwd = findViewById(R.id.et_pwd);
        findViewById(R.id.btn_update).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                updateUser();
        
            }
        });
        if (mUser != null) {
            mEtName.setText(mUser.name);
            mEtPwd.setText(mUser.password);
        }
    }
    
    private void updateUser() {
        mUser.name = mEtName.getText().toString();
        mUser.password = mEtPwd.getText().toString();
        mUserDAO.updateUser(mUser);
        finish();
    }
    
    public static void editUser(Context context, User user) {
        Intent intent = new Intent(context, EditUserActivity.class);
        intent.putExtra(KEY_USER, user);
        context.startActivity(intent);
    }
}
