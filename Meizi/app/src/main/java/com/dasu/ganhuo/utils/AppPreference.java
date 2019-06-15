package com.dasu.ganhuo.utils;

/**
 * Created by mundane on 2018/9/20 下午7:08
 */

public class AppPreference implements SpKey {
    
    public static void saveUsername(String username) {
        SPUtil.putString(KEY_USER_NAME, username);
    }
    
    public static void savePassword(String password) {
        SPUtil.putString(KEY_PASSWORD, password);
    }
    
    public static String getUsername() {
        return SPUtil.getString(KEY_USER_NAME);
    }
    
    public static String getPassword() {
        return SPUtil.getString(KEY_PASSWORD);
    }
    
    public static void clearUserInfo() {
        SPUtil.remove(KEY_USER_NAME);
        SPUtil.remove(KEY_PASSWORD);
    }
    
    
}
