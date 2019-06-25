package com.example.utils;

import android.text.TextUtils;
import com.example.model.User;
import java.util.List;

/**
 * CheckUtils
 *
 * @author fangyuan
 * @date 2019/6/16
 */
public class CheckUtils {
    
    public static boolean checkHasThisUser(List<User> userList, String username) {
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
