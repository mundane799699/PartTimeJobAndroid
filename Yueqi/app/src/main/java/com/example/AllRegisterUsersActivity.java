package com.example;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ListView;
import com.example.adapter.UserAdapter;
import com.example.base.BaseActivity;
import com.example.bean.User;
import com.example.dao.UserDAO;
import com.example.login.RegisterActivity;
import com.example.xstrategy.R;
import java.util.ArrayList;
import java.util.List;

/**
 * AllRegisterUsersActivity
 *
 * @author fangyuan
 * @date 2019-06-25
 */
public class AllRegisterUsersActivity extends BaseActivity {
    
    private ListView mLv;
    private List<User> mList = new ArrayList<User>();
    private UserAdapter mUserAdapter;
    private UserDAO mUserDAO;
    private View mBtnAdd;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_register_users);
        mUserDAO = new UserDAO(this);
        mLv = (ListView) findViewById(R.id.lv);
        mBtnAdd = findViewById(R.id.btn_add);
        mBtnAdd.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                goToRegisterActivity();
            }
        });
        mUserAdapter = new UserAdapter(this, mList);
        mLv.setAdapter(mUserAdapter);
    }
    
    private void goToRegisterActivity() {
        startActivity(new Intent(this, RegisterActivity.class));
    }
    
    @Override
    protected void onResume() {
        super.onResume();
        queryAllUsers();
    }
    
    private void queryAllUsers() {
        List<User> users = mUserDAO.queryAllUsers();
        if (users != null && !users.isEmpty()) {
            mList.clear();
            mList.addAll(users);
        }
        mUserAdapter.notifyDataSetChanged();
    }
}
