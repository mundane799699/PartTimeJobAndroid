package com.example.lvyou.Utils;

import android.content.Context;
import android.content.SharedPreferences;
import java.lang.reflect.Method;
import java.util.Map;

public class SPUtil {
    
    private static SharedPreferences sp;
    private static SharedPreferences.Editor sEditor;
    private static final String DEFAULT_STRING_VALUE = "";
    private static final int DEFAULT_INT_VALUE = 0;
    private static final long DEFAULT_LONG_VALUE = 0;
    
    private static Context sContext;
    
    
    public static void init(Context context) {
        sContext = context.getApplicationContext();
    }
    
    private static SharedPreferences getSp() {
        if (sp == null) {
            sp = sContext.getSharedPreferences(SpKey.SP_NAME, Context.MODE_PRIVATE);
        }
        return sp;
    }
    
    private static SharedPreferences.Editor getEditor() {
        if (sEditor == null) {
            SharedPreferences sharedPreferences = sContext
                    .getSharedPreferences(SpKey.SP_NAME, Context.MODE_PRIVATE);
            sEditor = sharedPreferences.edit();
        }
        return sEditor;
    }
    
    public static void putString(String key, String value) {
        SharedPreferences.Editor editor = getEditor();
        editor.putString(key, value);
        editor.apply();
    }
    
    public static String getString(String key) {
        SharedPreferences sp = getSp();
        return sp.getString(key, DEFAULT_STRING_VALUE);
    }
    
    public static void putInt(String key, int value) {
        SharedPreferences.Editor editor = getEditor();
        editor.putInt(key, value);
        editor.apply();
    }
    
    public static int getInt(String key) {
        SharedPreferences sp = getSp();
        return sp.getInt(key, DEFAULT_INT_VALUE);
    }
    
    public static int getInt(String key, int defaultValue) {
        SharedPreferences sp = getSp();
        return sp.getInt(key, defaultValue);
    }
    
    public static void putLong(String key, long value) {
        SharedPreferences.Editor editor = getEditor();
        editor.putLong(key, value);
        editor.apply();
    }
    
    public static long getLong(String key) {
        SharedPreferences sp = getSp();
        return sp.getLong(key, DEFAULT_LONG_VALUE);
    }
    
    public static void putBoolean(String key, boolean value) {
        SharedPreferences.Editor editor = getEditor();
        editor.putBoolean(key, value);
        editor.apply();
    }
    
    public static boolean getBoolean(String key, boolean defValue) {
        SharedPreferences sp = getSp();
        return sp.getBoolean(key, defValue);
    }
    
    public static void put(String key, Object object) {
        SharedPreferences.Editor editor = getEditor();
        if (object instanceof String) {
            editor.putString(key, (String) object);
        } else if (object instanceof Integer) {
            editor.putInt(key, (Integer) object);
        } else if (object instanceof Boolean) {
            editor.putBoolean(key, (Boolean) object);
        } else if (object instanceof Float) {
            editor.putFloat(key, (Float) object);
        } else if (object instanceof Long) {
            editor.putLong(key, (Long) object);
        } else {
            editor.putString(key, object.toString());
        }
        SharedPreferencesCompat.apply(editor);
    }
    
    public static Object get(String key, Object defaultObject) {
        SharedPreferences sp = getSp();
        if (defaultObject instanceof String) {
            return sp.getString(key, (String) defaultObject);
        } else if (defaultObject instanceof Integer) {
            return sp.getInt(key, (Integer) defaultObject);
        } else if (defaultObject instanceof Boolean) {
            return sp.getBoolean(key, (Boolean) defaultObject);
        } else if (defaultObject instanceof Float) {
            return sp.getFloat(key, (Float) defaultObject);
        } else if (defaultObject instanceof Long) {
            return sp.getLong(key, (Long) defaultObject);
        }
        return null;
    }
    
    public static void remove(String key) {
        SharedPreferences.Editor editor = getEditor();
        editor.remove(key);
        SharedPreferencesCompat.apply(editor);
    }
    
    public static void clear() {
        SharedPreferences.Editor editor = getEditor();
        editor.clear();
        SharedPreferencesCompat.apply(editor);
    }
    
    public static boolean contains(String key) {
        SharedPreferences sp = getSp();
        return sp.contains(key);
    }
    
    public static Map<String, ?> getAll() {
        SharedPreferences sp = getSp();
        return sp.getAll();
    }
    
    /**
     * 创建一个解决SharedPreferencesCompat.apply方法的一个兼容类
     *
     * @author zhy
     */
    private static class SharedPreferencesCompat {
        private static final Method sApplyMethod = findApplyMethod();
        
        /**
         * 反射查找apply的方法
         */
        @SuppressWarnings({ "unchecked", "rawtypes" })
        private static Method findApplyMethod() {
            try {
                Class clz = SharedPreferences.Editor.class;
                return clz.getMethod("apply");
            } catch (NoSuchMethodException e) {
            }
            
            return null;
        }
        
        /**
         * 如果找到则使用apply执行，否则使用commit
         */
        public static void apply(SharedPreferences.Editor editor) {
            try {
                if (sApplyMethod != null) {
                    sApplyMethod.invoke(editor);
                    return;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            editor.commit();
        }
    }
}