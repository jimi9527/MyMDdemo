package com.example.mymaterialdesign.view;

import android.content.Context;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import com.android.volley.VolleyError;
import com.example.mymaterialdesign.R;
import com.example.mymaterialdesign.adpater.FootViewAdapter;
import com.example.mymaterialdesign.adpater.ProRecylerViewAdapter;
import com.example.mymaterialdesign.http.VolleyHttp;
import com.example.mymaterialdesign.http.VolleyRequest;
import com.example.mymaterialdesign.model.ProIma;
import com.example.mymaterialdesign.util.ContentValue;
import com.example.mymaterialdesign.util.MySpanSizeLookUp;
import com.example.mymaterialdesign.util.SignPassUtil;
import com.example.mymaterialdesign.util.ViewUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * author: daxiong9527
 * mail : 15570350453@163.com
 */

public class ProductFrament extends BaseFragment {
    private static String TAG = "ProductFrament" ;
    RecyclerView mRecylerView;
    private List<ProIma> listIma;
    Context context ;
    private ProRecylerViewAdapter mProRecylerViewAdapter;
    private FootViewAdapter mFootViewAdapter;
    public ProductFrament() {
    }
    @Override
    protected int getLayout() {
        return R.layout.profragment;
    }

    @Override
    protected void initView(View view) {
        context = getContext() ;
        listIma = new ArrayList<>();
        RecyclerView mRecylerView = (RecyclerView) view.findViewById(R.id.pro_recylerview);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(
                view.getContext(),3);
        mRecylerView.setItemAnimator(new DefaultItemAnimator());
        gridLayoutManager.setOrientation(GridLayoutManager.VERTICAL);
        Log.d("test","gridLayoutManager.getSpanCount():"+gridLayoutManager.getSpanCount());
        mRecylerView.setLayoutManager(gridLayoutManager);
        mProRecylerViewAdapter = new ProRecylerViewAdapter(context,listIma);
        mFootViewAdapter = new FootViewAdapter(context,mProRecylerViewAdapter);
        View mFootView = LayoutInflater.from(context).inflate(R.layout.footview,null);
        mFootViewAdapter.setmFootView(mFootView);
        mRecylerView.setAdapter(mFootViewAdapter);
        gridLayoutManager.setSpanSizeLookup( new MySpanSizeLookUp((FootViewAdapter)
                mRecylerView.getAdapter(),gridLayoutManager.getSpanCount()));
        mRecylerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });
    }
    @Override
    protected void initData() {
        String title = getArguments().getString("title");
        Log.d(TAG,"title:"+title);
        final String date = String.valueOf(new Date().getTime());
        SignPassUtil.init();
        SignPassUtil.setToken("null");
        SignPassUtil.setTimestamp(date);
        SignPassUtil.addParam("pag", 1);
        SignPassUtil.addParam("cnt", 24);
        SignPassUtil.addParam("key", title);
        SignPassUtil.getSignature(SignPassUtil.getParams());
        // 新版本
        String httpurlreq = ViewUtils.append5(ContentValue.NEWSORT, "pag" + 1 + "/",
                "cnt" +24+"/key"+ URLEncoder.encode(title), date,"null", SignPassUtil.getSignature(SignPassUtil.getParams()));
        Log.d(TAG, "httpurlreq:" + httpurlreq);
        new VolleyRequest().VolleyGetRequest(getContext(), httpurlreq, title, new VolleyHttp() {
            @Override
            public void onSuccess(String result) {
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    JSONObject jsonObjectDate = jsonObject.getJSONObject("data");
                    JSONArray jsonObjectList = jsonObjectDate.getJSONArray("list");
                    Log.d(TAG,"jsonObjectList:"+jsonObjectList);
                    for(int i = 0 ; i <jsonObjectList.length() ; i++){
                        ProIma mProIma = new ProIma();
                        JSONObject jsonObjectIndex = jsonObjectList.getJSONObject(i);
                        mProIma.pgId = jsonObjectIndex.getInt("pgId");
                        mProIma.id = jsonObjectIndex.getInt("id");
                        String  imageurl = "http://pub.alltuu.com/work/PG" + mProIma.pgId  + "/content" + mProIma.id + ".jpg";
                        Log.d(TAG,"imageurl:"+imageurl);
                        mProIma.url = imageurl;
                        listIma.add(mProIma);
                    }
                    mFootViewAdapter.notifyDataSetChanged();

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFail(VolleyError volleyError) {
                Log.d(TAG,"volleyError:"+volleyError);
            }
        });
    }
}
