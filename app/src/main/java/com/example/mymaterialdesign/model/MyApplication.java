package com.example.mymaterialdesign.model;

import android.app.Application;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * author: daxiong9527
 * mail : 15570350453@163.com
 */

public class MyApplication extends Application {
    private static RequestQueue queue;
    @Override
    public void onCreate() {
        super.onCreate();
        queue = Volley.newRequestQueue(getApplicationContext());
    }
    public static RequestQueue getQueue (){
        return queue ;
    }
}
