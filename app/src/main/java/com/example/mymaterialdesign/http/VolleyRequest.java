package com.example.mymaterialdesign.http;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;
import com.example.mymaterialdesign.model.MyApplication;

/**
 * author: daxiong9527
 * mail : 15570350453@163.com
 */

public class VolleyRequest {
    private static String TAG = "VolleyRequest" ;
    private Context context;
    private StringRequest request;

    public void VolleyGetRequest(Context context,String url,String tag,VolleyHttp volleyHttp){
        MyApplication.getQueue().cancelAll(tag);
        Log.d(TAG,"Thread.currentThread().getName():"+Thread.currentThread().getName());
        request = new StringRequest(Request.Method.GET,url,volleyHttp.listener(),volleyHttp.errorListener());
        request.setTag(tag);
        MyApplication.getQueue().add(request);
    }
}
