package com.example.utils;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Created by mundane on 2019/1/24 下午2:15
 */
public class EasyPermission {
    private static Map<Fragment, Map<Integer, PermissionResultListener>> sFragmentContainer =
            new HashMap<>();
    
    private static Map<FragmentActivity, Map<Integer, PermissionResultListener>>
            sActivityContainer = new HashMap<>();
    
    public static void dispatchRequestPermissionsResult(Fragment fragment, int requestCode,
            String[] permissions, int[] grantResults) {
        if (!sFragmentContainer.containsKey(fragment)) {
            return;
        }
        Map<Integer, PermissionResultListener> map = sFragmentContainer.get(fragment);
        if (!map.containsKey(requestCode)) {
            return;
        }
        PermissionResultListener listener = map.get(requestCode);
        if (listener != null) {
            if (verifyPermissions(grantResults)) {
                listener.onAccepted();
            } else {
                listener.onRefused();
            }
        }
    }
    
    public static void dispatchRequestPermissionsResult(FragmentActivity activity, int requestCode,
            String[] permissions, int[] grantResults) {
        if (!sActivityContainer.containsKey(activity)) {
            return;
        }
        Map<Integer, PermissionResultListener> map = sActivityContainer.get(activity);
        if (!map.containsKey(requestCode)) {
            return;
        }
        PermissionResultListener listener = map.get(requestCode);
        if (listener != null) {
            if (verifyPermissions(grantResults)) {
                listener.onAccepted();
            } else {
                listener.onRefused();
            }
        }
    }
    
    public static void releaseFragment(Fragment fragment) {
        if (!sFragmentContainer.containsKey(fragment)) {
            return;
        }
        Map<Integer, PermissionResultListener> map = sFragmentContainer.get(fragment);
        if (map!=null && !map.isEmpty()) {
            map.clear();
        }
        sFragmentContainer.remove(fragment);
    }
    
    public static void releaseActivity(FragmentActivity activity) {
        if (!sActivityContainer.containsKey(activity)) {
            return;
        }
        Map<Integer, PermissionResultListener> map = sActivityContainer.get(activity);
        if (map!=null && !map.isEmpty()) {
            map.clear();
        }
        sActivityContainer.remove(activity);
    }
    
    public static void requestPermissions(Fragment fragment, String[] permissions,
            PermissionResultListener listener) {
        if (listener == null) {
            return;
        }
        if (mayRequestPermissions(permissions, fragment.getContext())) {
            listener.onAccepted();
        } else {
            int requestCode = new Random().nextInt(0x0000FFFF);
            HashMap<Integer, PermissionResultListener> map = new HashMap<>();
            map.put(requestCode, listener);
            sFragmentContainer.put(fragment, map);
            fragment.requestPermissions(permissions, requestCode);
        }
    }
    
    public static void requestPermissions(FragmentActivity activity, String[] permissions,
            PermissionResultListener listener) {
        if (listener == null) {
            return;
        }
        if (mayRequestPermissions(permissions, activity)) {
            listener.onAccepted();
        } else {
            int requestCode = new Random().nextInt(0x0000FFFF);
            HashMap<Integer, PermissionResultListener> map = new HashMap<>();
            map.put(requestCode, listener);
            sActivityContainer.put(activity, map);
            ActivityCompat.requestPermissions(activity, permissions, requestCode);
        }
    }
    
    private static boolean verifyPermissions(int[] grantResults) {
        for (int grantResult : grantResults) {
            if (grantResult != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }
    
    private static boolean mayRequestPermissions(String[] permissions, Context context) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return true;
        }
        for (String permission : permissions) {
            if (ContextCompat.checkSelfPermission(context, permission)
                    != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }
    
    public interface PermissionResultListener {
        void onAccepted();
        
        void onRefused();
    }
}
