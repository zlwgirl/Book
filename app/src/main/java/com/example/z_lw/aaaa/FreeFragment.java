package com.example.z_lw.aaaa;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/9/27.
 */
public class FreeFragment extends Fragment implements AdapterView.OnItemClickListener {
    private GridViewAdapter gridViewAdapter;
    private GridView gridView;
    private Banner banner;
    private String[] images  = {"http://img.taodiantong.cn/v55183/infoimg/2013-07/130720115322ky.jpg",
            "http://pic30.nipic.com/20130626/8174275_085522448172_2.jpg",
            "http://pic18.nipic.com/20111215/577405_080531548148_2.jpg",
            "http://pic15.nipic.com/20110722/2912365_092519919000_2.jpg",
            "http://pic.58pic.com/58pic/12/64/27/55U58PICrdX.jpg"};
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_free,null);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        gridView = (GridView) view.findViewById(R.id.gridView);
        banner = (Banner) view.findViewById(R.id.banner);

    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        gridViewAdapter = new GridViewAdapter(getContext());
        gridView.setAdapter(gridViewAdapter);
        gridView.setOnItemClickListener(this);


    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Intent intent = new Intent(getContext(),DetailsActivity.class);
        getActivity().startActivity(intent);
        ShowBanner();

    }
    public void ShowBanner() {
//        // 设置小圆点
//        banner.setBannerStyle(Banner.);
//
////        banner.setBannerStyle(Banner.CIRCLE_INDICATOR);
//        // 设置位置 居中
//        banner.setIndicatorGravity(Banner.CENTER);
        // 设置轮播时间
        banner.setDelayTime(3000);
        // 设置图片
        banner.setImages(images);
    }

}

