package com.example.mymaterialdesign.http;

import com.android.volley.Response;
import com.android.volley.VolleyError;

/**
 * author: daxiong9527
 * mail : 15570350453@163.com
 */

public abstract class VolleyHttp {

    public  Response.Listener onSuccessListner;
    public  Response.ErrorListener onFailListner;

    public abstract void onSuccess(String result);
    public abstract  void onFail(VolleyError volleyError);

    public Response.Listener<String> listener(){
        onSuccessListner = new Response.Listener<String>(){

            @Override
            public void onResponse(String response) {
                onSuccess(response);
            }
        };
        return onSuccessListner;
    }
    public Response.ErrorListener errorListener(){
        onFailListner = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                onFail(error);
            }
        };
        return  onFailListner;
    }
}
