package com.dasu.ganhuo.ui.base;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AlertDialog.Builder;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.dasu.ganhuo.R;
import com.dasu.ganhuo.ui.category.CategoryActivity;
import com.dasu.ganhuo.ui.history.HistoryActivity;
import com.dasu.ganhuo.ui.login.LoginActivity;
import com.dasu.ganhuo.ui.reading.ReadingActivity;
import com.dasu.ganhuo.ui.video.VideoActivity;
import com.dasu.ganhuo.utils.AppPreference;

/**
 * Created by suxq on 2017/4/17.
 * <p>
 * 带有侧滑菜单的主页面，支持设置是否需要有toolbar
 * 只有主菜单页面才需要继承该类，二级菜单无需继承该类
 */

public abstract class DrawerActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener {
    
    //维护所有menu，子类继承该类时需实现绑定某一 menu
    protected static final int MENU_HISTORY = R.id.nav_history;
    protected static final int MENU_CATEGORY = R.id.nav_category;
    protected static final int MENU_READING = R.id.nav_reading;
    protected static final int MENU_VIDEO = R.id.nav_video;
    protected static final int MENU_MEIZI = R.id.nav_meizi;
    protected static final int MENU_LOGOUT = R.id.nav_logout;
    
    private ViewGroup mContentView;
    private DrawerLayout mDrawerLayout;
    private NavigationView mNavigationView;
    
    private long mFirstClickTime;
    
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initContentView();
        
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mNavigationView = (NavigationView) findViewById(R.id.nav_view);
        TextView tvCurrentUser = (TextView) mNavigationView.getHeaderView(0).findViewById(R.id.tv_current_user);
        tvCurrentUser.setText(AppPreference.getUsername());
        mNavigationView.setNavigationItemSelectedListener(this);
    }
    
    @Override
    protected void onStart() {
        super.onStart();
    }
    
    private void initContentView() {
        ViewGroup viewGroup = (ViewGroup) findViewById(android.R.id.content);
        viewGroup.removeAllViews();
        View rootView = LayoutInflater.from(this).inflate(R.layout.base_activity_drawerlayout, null);
        viewGroup.addView(rootView);
        mContentView = (ViewGroup) findViewById(R.id.layout_content);
    }
    
    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        LayoutInflater.from(this).inflate(layoutResID, mContentView, true);
    }
    
    @Override
    public void setContentView(View view) {
        mContentView.addView(view);
    }
    
    @Override
    public void setContentView(View view, ViewGroup.LayoutParams params) {
        mContentView.addView(view, params);
    }
    
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        //open avtivity
        item.setChecked(true);
        Intent intent = new Intent();
        switch (item.getItemId()) {
            case MENU_MEIZI:
                closeDrawer();
                return true;
            case MENU_HISTORY:
                intent.setClass(this, HistoryActivity.class);
                break;
            case MENU_CATEGORY:
                intent.setClass(this, CategoryActivity.class);
                break;
            case MENU_READING:
                intent.setClass(this, ReadingActivity.class);
                break;
            case MENU_VIDEO:
                intent.setClass(this, VideoActivity.class);
                break;
            case MENU_LOGOUT:
                confirmQuit();
                return true;
            default:
                break;
        }
        startActivity(intent);
        return true;
    }
    
    private void confirmQuit() {
        Builder builder = new Builder(this);
        builder.setMessage("确定要退出登录吗?").setPositiveButton("确定", new OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                logout();
            }
        }).setNegativeButton("取消", new OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
    
    private void logout() {
        AppPreference.clearUserInfo();
        startActivity(new Intent(this, LoginActivity.class));
        finish();
    }
    
    private void closeThenStartActivity(final Intent intent) {
        final DrawerLayout.DrawerListener drawerListener = new DrawerLayout.SimpleDrawerListener() {
            @Override
            public void onDrawerClosed(View drawerView) {
                startActivity(intent);
            }
        };
        mDrawerLayout.removeDrawerListener(drawerListener);
        mDrawerLayout.addDrawerListener(drawerListener);
        closeDrawer();
    }
    
    
    protected boolean closeDrawer() {
        if (mDrawerLayout != null && mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawers();
            return true;
        }
        return false;
    }
    
    protected DrawerLayout getDrawerLayout() {
        return mDrawerLayout;
    }
    
    /**
     * 如果子类页面需要有Toolbar的，在xml布局里添加相应view，在onCreate()里将初始化后的toolbar对象传递进来
     * 基类负责对toolbar进行基本的配置
     */
    protected void addToolbar(Toolbar toolbar) {
        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, getDrawerLayout(), toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        
        getDrawerLayout().addDrawerListener(toggle);
        toggle.syncState();
    }
    
}
