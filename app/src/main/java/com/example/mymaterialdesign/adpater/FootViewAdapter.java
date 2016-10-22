package com.example.mymaterialdesign.adpater;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

/**
 * author: daxiong9527
 * mail : 15570350453@163.com
 */

public class FootViewAdapter extends RecyclerView.Adapter {
    private final static int HEADTYPE = 0;
    private final static int FOOTTYPE = 1;
    private final static int NOMALTYPE = 2;
    private static final String TAG = "FootViewAdapter";
    private Context mContext;
    private RecyclerView.Adapter<RecyclerView.ViewHolder> mAdapter;
    private View mHeadView;
    private View mFootView;

    public FootViewAdapter(Context mContext,RecyclerView.Adapter<RecyclerView.ViewHolder> mAdapter) {
        this.mContext = mContext;
        this.mAdapter = mAdapter;
    }

    public View getmHeadView() {
        return mHeadView;
    }

    public void setmHeadView(View mHeadView) {
        this.mHeadView = mHeadView;
    }

    public View getmFootView() {
        return mFootView;
    }

    public void setmFootView(View mFootView) {
        this.mFootView = mFootView;
    }

    private int getHeadNum() {
        return null == mHeadView ? 0 : 1;
    }

    private int getFootNum() {
        return null == mFootView ? 0 : 1;
    }
    public boolean isHeader(int position) {
        return getHeadNum() > 0 && position == 0;
    }

    public boolean isFooter(int position) {
        int lastPosition = getItemCount() - 1;
        return getFootNum() > 0 && position == lastPosition;
    }

    @Override
    public int getItemViewType(int position) {
        if (position < getHeadNum()) {
            return HEADTYPE;
        } else if (getHeadNum() <= position && position < (mAdapter.getItemCount() + getHeadNum())) {
            return NOMALTYPE;
        } else {
            return FOOTTYPE;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.d(TAG,"viewType:"+viewType);
        if (viewType == HEADTYPE) {
            HeadHolder headHolder = new HeadHolder(mHeadView);
            return headHolder;
        } else if (viewType == FOOTTYPE) {
            FootHolder footHolder = new FootHolder(mFootView);
            return footHolder;
        } else {
            return mAdapter.onCreateViewHolder(parent, NOMALTYPE);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Log.d(TAG,"holder:"+holder);
        Log.d(TAG,"position:"+position);
        Log.d(TAG,"getHeadNum:"+getHeadNum());
       if(position >= getHeadNum() && position <mAdapter.getItemCount() +getHeadNum()){
           mAdapter.onBindViewHolder(holder, position-getHeadNum());
       }else{
           ViewGroup.LayoutParams layoutParams = holder.itemView.getLayoutParams();
           if(layoutParams instanceof StaggeredGridLayoutManager.LayoutParams) {
               ((StaggeredGridLayoutManager.LayoutParams) layoutParams).setFullSpan(true);
           }
       }
    }

    @Override
    public int getItemCount() {
        return mAdapter.getItemCount() + getHeadNum() + getFootNum();
    }


    private static class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(View itemView) {
            super(itemView);
        }
    }

    class HeadHolder extends RecyclerView.ViewHolder {
        public HeadHolder(View itemView) {
            super(itemView);
        }
    }

    class FootHolder extends RecyclerView.ViewHolder {
        public FootHolder(View itemView) {
            super(itemView);
        }
    }
}
