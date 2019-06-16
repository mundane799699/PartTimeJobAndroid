package com.dasu.ganhuo.ui.home;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import com.dasu.ganhuo.R;
import com.dasu.ganhuo.mode.logic.home.HomeController;
import com.dasu.ganhuo.ui.base.DrawerActivity;
import com.dasu.ganhuo.ui.meizi.MeiziFragment;

/**
 * 今日推荐页面，只负责界面数据的展示，业务逻辑交由{@link HomeController} 负责
 * 双方互相持有引用，可直接交互
 * 建议Activity不主动从Controller拿数据，只能被动获取
 */
public class HomeActivity extends DrawerActivity {
    private MeiziFragment mMeiziFragment;
    
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        addToolbar((Toolbar) findViewById(R.id.toolbar));
        mMeiziFragment = new MeiziFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.fl_content, mMeiziFragment).commitNowAllowingStateLoss();
    }
    
    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
    
    
}
