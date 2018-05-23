package com.example.asus.threemodel.application;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Process;

import com.example.asus.threemodel.model.SharedKey;

/**
 * date : 2018/5/18 17:13
 */
public class CommonUtil {

    private static SharedPreferences sharedPreferences;

    public static void runUiThread(Runnable runnable) {
        if (Process.myTid() == MyApplication.getTid()) {
            runnable.run();
        } else {
            MyApplication.getHandler().post(runnable);
        }
    }


    private static void getSharePreference() {
        if (sharedPreferences == null)
            sharedPreferences = MyApplication.getAppContext().getSharedPreferences("cgShared", Context.MODE_PRIVATE);
    }

    public static void saveColorValue(int color) {
        getSharePreference();
        sharedPreferences.edit().putInt(SharedKey.COLOR_KEY, color).commit();
    }

    public static int obtainColorValue() {
        getSharePreference();
        return sharedPreferences.getInt(SharedKey.COLOR_KEY, -1);
    }
}
