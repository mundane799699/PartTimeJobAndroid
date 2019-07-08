package com.example.manageuser;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import com.example.R;
import com.example.adapter.UserAdapter;
import com.example.base.BaseActivity;
import com.example.bean.User;
import com.example.dao.UserDAO;
import com.example.dialog.UpdateDeleteDialog;
import com.example.dialog.UpdateDeleteDialog.OnDialogClickListener;
import java.util.ArrayList;
import java.util.List;

public class UserListActivity extends BaseActivity {
    
    private ListView mLv;
    private UserDAO mUserDAO;
    private List<User> mUserList = new ArrayList<>();
    private UserAdapter mUserAdapter;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_user);
        mLv = findViewById(R.id.lv);
        findViewById(R.id.btn_add).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                addUser();
            }
        });
        mUserDAO = new UserDAO(this);
        mUserAdapter = new UserAdapter(mUserList);
        mLv.setAdapter(mUserAdapter);
        mLv.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                
                showUpdateOrDeleteDialog(position);
            }
        });
    }
    
    private void showUpdateOrDeleteDialog(final int position) {
        UpdateDeleteDialog dialog = new UpdateDeleteDialog(this);
        dialog.setOnDialogClickListener(new OnDialogClickListener() {
            @Override
            public void onUpdate() {
                updateUser(position);
            }
            
            @Override
            public void onDelete() {
                deleteUser(position);
            }
        });
        dialog.show();
    }
    
    private void updateUser(int position) {
        User user = mUserList.get(position);
        EditUserActivity.editUser(this, user);
    }
    
    private void deleteUser(int position) {
        User user = mUserList.get(position);
        mUserDAO.deteleUser(user);
        queryAllUser();
    }
    
    @Override
    protected void onResume() {
        super.onResume();
        queryAllUser();
    }
    
    private void queryAllUser() {
        List<User> userList = mUserDAO.queryAllUsers();
        mUserList.clear();
        if (userList != null && !userList.isEmpty()) {
            mUserList.addAll(userList);
        }
        mUserAdapter.notifyDataSetChanged();
    }
    
    private void addUser() {
        startActivity(new Intent(this, AddUserActivity.class));
    }
}
