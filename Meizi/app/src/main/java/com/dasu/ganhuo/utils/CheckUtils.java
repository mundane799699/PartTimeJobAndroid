package com.dasu.ganhuo.utils;

import android.text.TextUtils;
import com.dasu.ganhuo.db.DbUtils;
import com.dasu.ganhuo.entity.User;
import java.util.List;

/**
 * CheckUtils
 *
 * @author fangyuan
 * @date 2019/6/16
 */
public class CheckUtils {
    public static boolean check(String username, String password) {
        if (TextUtils.isEmpty(username) || TextUtils.isEmpty(password)) {
            return false;
        }
        List<User> userList = DbUtils.queryAllUsers();
        if (userList == null || userList.isEmpty()) {
            return false;
        }
        for (User user : userList) {
            if (TextUtils.equals(user.name, username) && TextUtils.equals(user.pwd, password)) {
                return true;
            }
        }
        return false;
    }
    
    public static boolean checkHasThisUser(String username) {
        List<User> userList = DbUtils.queryAllUsers();
        if (userList == null || userList.isEmpty()) {
            return false;
        }
        for (User user : userList) {
            if (TextUtils.equals(user.name, username)) {
                return true;
            }
        }
        return false;
    }
}
