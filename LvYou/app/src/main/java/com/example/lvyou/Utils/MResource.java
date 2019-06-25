package com.example.lvyou.Utils;

import android.content.Context;

/**
 * 根据资源的名字获取其Id?
 *
 * @author caoyang
 */
public class MResource {
    public static int getIdByDrawableName(Context context, String resName) {
        return context.getResources().getIdentifier(resName, "drawable", context.getPackageName());
    }
    
}
