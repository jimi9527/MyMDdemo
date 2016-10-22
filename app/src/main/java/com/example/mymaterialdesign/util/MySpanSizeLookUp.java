package com.example.mymaterialdesign.util;

import android.support.v7.widget.GridLayoutManager;
import android.util.Log;

import com.example.mymaterialdesign.adpater.FootViewAdapter;

/**
 * author: daxiong9527
 * mail : 15570350453@163.com
 */

public class MySpanSizeLookUp extends GridLayoutManager.SpanSizeLookup {

    private FootViewAdapter adapter ;
    private int mSpanSize = 1;

    public MySpanSizeLookUp(FootViewAdapter adapter, int mSpanSize) {
        this.adapter = adapter;
        this.mSpanSize = mSpanSize;
    }

    @Override

    public int getSpanSize(int position) {
        boolean isHeaderOrFooter = adapter.isHeader(position) || adapter.isFooter(position);
        Log.d("test","isHeaderOrFooter:"+isHeaderOrFooter);
        return isHeaderOrFooter ? mSpanSize : 1;
    }
}
