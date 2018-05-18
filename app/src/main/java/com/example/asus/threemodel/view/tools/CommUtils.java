package com.example.asus.threemodel.view.tools;

import android.app.Activity;
import android.content.SharedPreferences;

import com.example.asus.threemodel.R;
import com.example.asus.threemodel.application.MyApplication;

public class CommUtils {
    private static final String TAG = "SPL";
    private static SharedPreferences sharedPreferences;


    public static void saveString(String flag, String str) {
        if (sharedPreferences == null) {
            sharedPreferences = MyApplication.getAppContext().getSharedPreferences(TAG, MyApplication.getAppContext().MODE_PRIVATE);
        }
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putString(flag, str);
        edit.commit();
    }

    public static String getString(String flag) {
        if (sharedPreferences == null) {
            sharedPreferences = MyApplication.getAppContext().getSharedPreferences(TAG, MyApplication.getAppContext().MODE_PRIVATE);
        }
        return sharedPreferences.getString(flag, "");

    }

    public static boolean getBoolean(String tag) {
        if (sharedPreferences == null) {
            sharedPreferences = MyApplication.getAppContext().getSharedPreferences(TAG, MyApplication.getAppContext().MODE_PRIVATE);
        }
        return sharedPreferences.getBoolean(tag, false);
    }

    public static void putBoolean(String tag, boolean content) {
        if (sharedPreferences == null) {
            sharedPreferences = MyApplication.getAppContext().getSharedPreferences(TAG, MyApplication.getAppContext().MODE_PRIVATE);
        }
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putBoolean(tag, content);
        edit.commit();
    }


    public static void clearSp(String tag) {
        if (sharedPreferences == null) {
            sharedPreferences = MyApplication.getAppContext().getSharedPreferences(TAG, MyApplication.getAppContext().MODE_PRIVATE);
        }
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.remove(tag);
        edit.commit();
    }


    public static void goAnim(Activity activity) {
        activity.overridePendingTransition(R.anim.slide_right_in, R.anim.slide_left_out);
    }


    public static void backAnim(Activity activity) {
        activity.overridePendingTransition(R.anim.slide_left_out_fan, R.anim.slide_right_in_fan);
    }


}
