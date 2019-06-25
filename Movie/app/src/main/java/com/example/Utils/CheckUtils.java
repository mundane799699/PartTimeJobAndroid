package com.example.Utils;

import android.text.TextUtils;
import com.example.bean.User;
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
