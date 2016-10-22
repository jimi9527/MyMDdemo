package com.example.mymaterialdesign.adpater;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.mymaterialdesign.util.ViewUtils;
import com.example.mymaterialdesign.view.BaseFragment;
import com.example.mymaterialdesign.view.ProductFrament;

import java.util.List;

import static android.R.id.list;

/**
 * author: daxiong9527
 * mail : 15570350453@163.com
 */

public class MainAdapter extends FragmentStatePagerAdapter {
    private String[] title;
    public MainAdapter(FragmentManager fm,String[] title) {
        super(fm);
        this.title = title ;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = ViewUtils.createFragment(ProductFrament.class,false);
        Bundle bundle = new Bundle();
        bundle.putString("title",title[position]);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public int getCount() {
        return title.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return title[position];
    }
}
