package com.example.mymaterialdesign.util;


import android.content.Context;
import android.view.WindowManager;

import com.example.mymaterialdesign.view.BaseFragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * author: daxiong9527
 * mail : 15570350453@163.com
 */

public class ViewUtils {
    public static Map<String,BaseFragment> fragmentList = new HashMap<>();

    /**
     * 根据Class创建Fragment
     *
     * @param clazz the Fragment of create
     * @return resultFragment
     */
    public static BaseFragment createFragment(Class<?> clazz, boolean isObtain) {
        BaseFragment resultFragment = null;
        String className = clazz.getName();
        if (fragmentList.containsKey(className)) {
            resultFragment = fragmentList.get(className);
        } else {
            try {
                try {
                    resultFragment = (BaseFragment) Class.forName(className).newInstance();
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            if (isObtain)
                fragmentList.put(className, resultFragment);
        }

        return resultFragment;
    }

    public static BaseFragment createFragment(Class<?> clazz) {
        return createFragment(clazz, true);
    }
    public static String append5(String s1,String s2,String s3, String s4,String  s5,String s6){
        StringBuilder sb = new StringBuilder();
        return sb.append(s1).append(s2).append(s3).append("/").append(s4).append("/").append(s5).append("/").append(s6).toString();
    }
    public static int GetScreenWidth(Context context){
        int width  = 0;
        if(context != null){
            WindowManager wm = (WindowManager)context.getSystemService(context.WINDOW_SERVICE);
            width = wm.getDefaultDisplay().getWidth();
        }
        return width ;
    }

}
