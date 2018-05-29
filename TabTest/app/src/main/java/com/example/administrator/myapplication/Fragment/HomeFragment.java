package com.example.administrator.myapplication.Fragment;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.adapter.RecycleViewGridAdapter;
import com.example.administrator.myapplication.adapter.TabFragmentAdapter;
import com.example.administrator.myapplication.constant.ActivityConstants;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/5/29 0029.
 */

public class HomeFragment extends Fragment  implements View.OnClickListener{
    private View layout;
    private int requestCode;
    private static final int REQUEST_CODE_AMOUNT = 5;
    private ImageView imageView;
    private ViewPager viewPager;
    private  TabLayout tablayout;


    private List<Fragment> mFragmentArrays = new ArrayList<>();
    private List<String> mTabs = new ArrayList<>();
    private View view;
    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {
        if (layout != null) {
            // 防止多次new出片段对象，造成图片错乱问题
            return layout;
        }
        requestCode = ActivityConstants.REQUEST_CODE_MAINACTIVITY++;
        layout = inflater.inflate(R.layout.fragment_home, null, false);
        initView();
        initDate();
        return layout;
    }



    private void initView() {
          imageView=layout.findViewById(R.id.iv_fenlei);
          tablayout=layout.findViewById(R.id.tablayout);
          viewPager=layout.findViewById(R.id.tab_viewpager);
          imageView.setOnClickListener(this);
    }

    private void initDate() {
        tablayout.removeAllTabs();
        viewPager.removeAllViews();
        if (mFragmentArrays != null) {
            mFragmentArrays.clear();
            mTabs.clear();
        }
        mTabs.add("推荐");
        mTabs.add("手机");
        mTabs.add("智能");
        mTabs.add("电视");
        mTabs.add("电脑");
        mTabs.add("双摄");
        mTabs.add("生活周边");
        mTabs.add("盒子");
        mTabs.add("推荐");
        mTabs.add("手机");
        mTabs.add("智能");
        mTabs.add("电视");
        mTabs.add("电脑");
        mTabs.add("双摄");
        mTabs.add("生活周边");
        mTabs.add("盒子");


        for (int i = 0; i < mTabs.size(); i++) {

            Fragment fragment = new TabFragment();
            Bundle bundle = new Bundle();
            bundle.putInt("position", i);
            fragment.setArguments(bundle);
            mFragmentArrays.add(fragment);
        }

        viewPager.setAdapter(new TabFragmentAdapter(getFragmentManager(), mFragmentArrays, mTabs));
        tablayout.setupWithViewPager(viewPager);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_fenlei:
                startPopuwindows(view);
                break;
        }
    }

    private void startPopuwindows(View view1) {
        View view=LayoutInflater.from(getActivity()).inflate(R.layout.layout_main_popuwindows,null);
        RecyclerView recyclerView=view.findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),5));
        RecycleViewGridAdapter gridAdapter=new RecycleViewGridAdapter(R.layout.item_gride_fenlei,mTabs);
        recyclerView.setAdapter(gridAdapter);

        final PopupWindow popupWindow=new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT,true);
        popupWindow.showAsDropDown(view1);

        gridAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                Toast.makeText(getActivity(),"点击了"+mTabs.get(position),Toast.LENGTH_SHORT).show();
                viewPager.setCurrentItem(position);
                popupWindow.dismiss();
            }
        });
        gridAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                viewPager.setCurrentItem(position);
                popupWindow.dismiss();
            }
        });
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
