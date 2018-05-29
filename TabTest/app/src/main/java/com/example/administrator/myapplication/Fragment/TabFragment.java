package com.example.administrator.myapplication.Fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.Utils.GlideImageLoader;
import com.example.administrator.myapplication.adapter.RecycleViewAdapter;
import com.example.administrator.myapplication.constant.ActivityConstants;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/5/29 0029.
 */

public class TabFragment extends Fragment {
    private RecyclerView recyclerView;
    private View layout;
    private int requestCode;
    private static final int REQUEST_CODE_AMOUNT = 5;
    int mPosition;
    private RecycleViewAdapter mAdapter;
    private Banner mBanner;
    private List<String> mdata = new ArrayList<>();
    private List<String> imageUrl = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (layout != null) {
            // 防止多次new出片段对象，造成图片错乱问题
            return layout;
        }
        requestCode = ActivityConstants.REQUEST_CODE_MAINACTIVITY++;
        layout = inflater.inflate(R.layout.fragment_tab, null, false);
        mPosition = getArguments().getInt("position");
        initView();
        initDate();
        return layout;
    }

    private void initView() {
        recyclerView = layout.findViewById(R.id.recycler);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        mAdapter = new RecycleViewAdapter(R.layout.home_item_view, mdata);

        View top = getLayoutInflater().inflate(R.layout.layout_banner, (ViewGroup) recyclerView.getParent(), false);
        mBanner = top.findViewById(R.id.banner);
        mAdapter.addHeaderView(top);
        recyclerView.setAdapter(mAdapter);
    }

    private void initDate() {
        for (int i = 0; i < 20; i++) {
            mdata.add("lunatic" + mPosition + "_" + i);
        }
        mAdapter.setNewData(mdata);
        if (mPosition == 0) {
            imageUrl.clear();
            imageUrl.add("http://d.hiphotos.baidu.com/image/pic/item/8cb1cb13495409237691bcd49e58d109b2de49a4.jpg");
            imageUrl.add("http://a.hiphotos.baidu.com/image/pic/item/730e0cf3d7ca7bcb6a172486b2096b63f624a82f.jpg");
            imageUrl.add("http://a.hiphotos.baidu.com/image/pic/item/d009b3de9c82d1584cab701b8c0a19d8bc3e426a.jpg");
            initBanner(imageUrl);
        } else {
            mBanner.setVisibility(View.GONE);
        }

    }

    private void initBanner(List<String> imageUrl) {
        mBanner.setImages(imageUrl)
                .setImageLoader(new GlideImageLoader())
                .setDelayTime(3000)
                .start();
        mBanner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {

            }
        });
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    //如果你需要考虑更好的体验，可以这么操作
    @Override
    public void onStart() {
        super.onStart();
        //开始轮播
        mBanner.startAutoPlay();
    }

    @Override
    public void onStop() {
        super.onStop();
        //结束轮播
        mBanner.stopAutoPlay();
    }
}
