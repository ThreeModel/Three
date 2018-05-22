package com.example.asus.threemodel.view.tools;

import android.app.Activity;
import android.content.SharedPreferences;

import com.example.asus.threemodel.R;
import com.example.asus.threemodel.application.MyApplication;
import com.example.asus.threemodel.model.bean.VideoBean;

import java.util.ArrayList;
import java.util.List;

public class CommUtils {
    private static final String TAG = "SPL";
    private static SharedPreferences sharedPreferences;
    //  收藏的电影
    private static List<VideoBean.RetBean.ListBean.ChildListBean> mCollects = new ArrayList<>();

    /**
     * 添加电影到收藏列表
     * @param childListBean
     */
    public static void addCollectVideo(VideoBean.RetBean.ListBean.ChildListBean childListBean){
        mCollects.add(childListBean);
    }

    /**
     * 获取电影收藏列表
     * @return
     */
    public static List<VideoBean.RetBean.ListBean.ChildListBean> getCollectVideo(){
        if(mCollects != null){
            return mCollects;
        }
        return null;
    }


    /**
     * 清除全部
     * @return
     */
    public static boolean calerCollert(){
        boolean isSuccess =  mCollects.removeAll(mCollects);
        return isSuccess;
    }



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
