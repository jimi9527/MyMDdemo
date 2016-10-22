package com.example.mymaterialdesign.view;

import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.example.mymaterialdesign.R;
import com.example.mymaterialdesign.adpater.MainAdapter;

/**
 * author: daxiong9527
 * mail : 15570350453@163.com
 */

public class MainFragment extends BaseFragment {
    FragmentManager fragmentManager;
    private ViewPager mViewPager;
    private MainAdapter mMainAdapter;
    private String [] mTitles;
    @Override
    protected int getLayout() {
        return R.layout.app_bar_main;
    }

    @Override
    protected void initView(View view) {
        initFragment(view);
    }


    @Override
    protected void initData() {

    }

    private void initFragment(View view){
        mTitles = new String[]{"人像", "婚礼", "风光"};
        fragmentManager = getChildFragmentManager();
        TabLayout tabLayout = (TabLayout)view.findViewById(R.id.tablelayout);
        mViewPager = (ViewPager)view.findViewById(R.id.viewpager);
        mMainAdapter = new MainAdapter(fragmentManager,mTitles);
        mViewPager.setAdapter(mMainAdapter);
        tabLayout.setupWithViewPager(mViewPager);
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
    }
}
