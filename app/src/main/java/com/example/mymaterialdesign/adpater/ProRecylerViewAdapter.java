package com.example.mymaterialdesign.adpater;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.example.mymaterialdesign.R;
import com.example.mymaterialdesign.model.ProIma;
import com.example.mymaterialdesign.util.ViewUtils;

import java.util.List;

/**
 * author: daxiong9527
 * mail : 15570350453@163.com
 */

public class ProRecylerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private List<ProIma> listImgs ;
    public ProRecylerViewAdapter(Context context,List<ProIma> listImgs) {
        this.context =context ;
        this.listImgs = listImgs ;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View MyView = LayoutInflater.from(context).inflate(R.layout.item_pro,null);
        MyViewHodler myViewHodler = new MyViewHodler(MyView);
        return myViewHodler;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams)
                ((MyViewHodler)holder).mImage.getLayoutParams();
        layoutParams.width = ViewUtils.GetScreenWidth(context)/3 ;
        layoutParams.height = ViewUtils.GetScreenWidth(context)/3 ;
        ((MyViewHodler)holder).mImage.setLayoutParams(layoutParams);
        Log.d("test","listImgs.size():"+listImgs.size());
        if(listImgs.size() != 0){
        Glide.with(context).load(listImgs.get(position).url).into(((MyViewHodler)holder).mImage);
        }
      //  ((MyViewHodler)holder).mImage
    }

    @Override
    public int getItemCount() {
        return listImgs.size();
    }
    class MyViewHodler extends RecyclerView.ViewHolder{
        ImageView mImage;
        public MyViewHodler(View itemView) {
            super(itemView);
            mImage = (ImageView)itemView.findViewById(R.id.img_pro);
        }
    }
}
