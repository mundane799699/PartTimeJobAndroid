package com.example.main;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.all.AllFragment;
import com.example.base.BaseActivity;
import com.example.mine.CardFragment;
import com.example.myapp.R;
import com.example.today.TodayFragment;

public class MainActivity extends BaseActivity {
    
    private TodayFragment mTodayFragment;
    private ImageView mIvToday;
    private Fragment mCurrentFragment;
    private TextView mTvToday;
    private TextView mTvAll;
    private TextView mTvMine;
    private ImageView mIvAll;
    private ImageView mIvMine;
    private View mLlToday;
    private View mLlAll;
    private View mLlMine;
    private AllFragment mAllFragment;
    private CardFragment mMineFragment;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mIvToday = findViewById(R.id.iv_today);
        mIvAll = findViewById(R.id.iv_all);
        mIvMine = findViewById(R.id.iv_mine);
        
        mTvToday = findViewById(R.id.tv_today);
        mTvAll = findViewById(R.id.tv_all);
        mTvMine = findViewById(R.id.tv_mine);
        
        mLlToday = findViewById(R.id.ll_today);
        mLlAll = findViewById(R.id.ll_all);
        mLlMine = findViewById(R.id.ll_mine);
        
        mTodayFragment = new TodayFragment();
        mAllFragment = new AllFragment();
        mMineFragment = new CardFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.fl_content, mTodayFragment).commitNowAllowingStateLoss();
        setTodaySelected(true);
        mCurrentFragment = mTodayFragment;
        
        mLlToday.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mCurrentFragment == mTodayFragment) {
                    return;
                }
                turnToFragment(mTodayFragment);
                setTodaySelected(true);
                setAllSelected(false);
                setMineSelected(false);
            }
        });
        
        mLlAll.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mCurrentFragment == mAllFragment) {
                    return;
                }
                turnToFragment(mAllFragment);
                setTodaySelected(false);
                setAllSelected(true);
                setMineSelected(false);
            }
        });
        
        mLlMine.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mCurrentFragment == mMineFragment) {
                    return;
                }
                turnToFragment(mMineFragment);
                setTodaySelected(false);
                setAllSelected(false);
                setMineSelected(true);
            }
        });
    }
    
    private void setTodaySelected(boolean selected) {
        mIvToday.setSelected(selected);
        mTvToday.setSelected(selected);
    }
    
    private void setAllSelected(boolean selected) {
        mIvAll.setSelected(selected);
        mTvAll.setSelected(selected);
    }
    
    private void setMineSelected(boolean selected) {
        mIvMine.setSelected(selected);
        mTvMine.setSelected(selected);
    }
    
    private void turnToFragment(Fragment targetFragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.hide(mCurrentFragment);
        if (targetFragment.isAdded()) {
            transaction.show(targetFragment).commitNowAllowingStateLoss();
        } else {
            transaction.add(R.id.fl_content, targetFragment).commitNowAllowingStateLoss();
        }
        mCurrentFragment = targetFragment;
    }
}
