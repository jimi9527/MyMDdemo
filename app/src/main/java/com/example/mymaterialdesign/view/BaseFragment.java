package com.example.mymaterialdesign.view;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mymaterialdesign.adpater.FootViewAdapter;
import com.example.mymaterialdesign.util.MySpanSizeLookUp;

/**
 * author: daxiong9527
 * mail : 15570350453@163.com
 */

public abstract class BaseFragment extends Fragment {
    private View view ;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(getLayout(),null);
        initView(view);
        initData();
        return view;
    }
    protected abstract int getLayout();
    protected abstract  void initView(View view);
    protected abstract  void initData();
    /**
     * 初始化recylerview
     */
    public RecyclerView initRecylerView(int id){
        RecyclerView mRecylerView = (RecyclerView) view.findViewById(id);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(
                view.getContext(),3);
        mRecylerView.setItemAnimator(new DefaultItemAnimator());
        gridLayoutManager.setOrientation(GridLayoutManager.VERTICAL);
        Log.d("test","gridLayoutManager.getSpanCount():"+gridLayoutManager.getSpanCount());
        gridLayoutManager.setSpanSizeLookup( new MySpanSizeLookUp((FootViewAdapter)
                mRecylerView.getAdapter(),gridLayoutManager.getSpanCount()));
        mRecylerView.setLayoutManager(gridLayoutManager);
        return mRecylerView;
    }
}
