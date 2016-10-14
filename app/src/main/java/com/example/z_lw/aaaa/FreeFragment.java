package com.example.z_lw.aaaa;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ViewFlipper;

import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


/**
 * Created by Administrator on 2016/9/27.
 */
public class FreeFragment extends Fragment {
    private ImageView imageView_one,imageView_two;
    private ViewFlipper flipper;
    private int[] imageId ={R.drawable.cycle1,R.drawable.cycle2,R.drawable.cycle3,R.drawable.cycle4};

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_free,null);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        flipper = (ViewFlipper) view.findViewById(R.id.flipper);
        imageView_one = (ImageView) view.findViewById(R.id.imageView_one);
        imageView_two = (ImageView) view.findViewById(R.id.imageView_two);
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //实现轮播图
        for (int i = 0; i < imageId.length; i++) {
            flipper.addView(getImageView(imageId[i]));
        }
//        flipper.setInAnimation(getContext(),R.anim.cycle_in);
//        flipper.setOutAnimation(getContext(),R.anim.cycle_out);
        //轮播图时间
        flipper.setFlipInterval(3000);
        flipper.startFlipping();

        //点击封面书，跳转到详情界面
        imageView_one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(),FreeDetailsActivity.class);
                getActivity().startActivity(intent);
            }
        });
        //加载图书封面图片
        Picasso.with(getContext()).load("http://139.129.215.221:8080/images/AdvancedMathematics.png").into(imageView_one);
        Picasso.with(getContext()).load("http://139.129.215.221:8080/images/wyoos.png").into(imageView_two);
    }
    public ImageView getImageView(int imageId ){
        ImageView image = new ImageView(getContext());
        image.setBackgroundResource(imageId);
        return image;
    }
}


