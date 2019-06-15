package com.dasu.ganhuo.mode.logic.home;

import android.content.Context;
import com.dasu.ganhuo.ui.home.HomeActivity;
import com.dasu.ganhuo.utils.LogUtils;

/**
 * Created by dasu on 2017/4/18.
 * <p>
 * 负责HomeActivity的业务逻辑即数据加载
 */

public class HomeController {
    private static final String TAG = HomeController.class.getSimpleName();

    private Context mContext;
    private HomeActivity mHomeActivity;

    public HomeController(Context context) {
        if (!(context instanceof HomeActivity)) {
            LogUtils.e(TAG, TAG + "绑定错误的Activity");
            throw new UnsupportedOperationException(TAG + "绑定错误的Activity");
        }
        mContext = context;
        mHomeActivity = (HomeActivity) context;
    }


}
