package com.example.z_lw.aaaa;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

/**
 * Created by Administrator on 2016/9/30.
 */
public class ImageFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ImageView imageView = new ImageView(getContext());
        int res = getArguments().getInt("res");
        imageView.setImageResource(res);
        imageView.setScaleType(ImageView.ScaleType.CENTER);
        return imageView;
    }
}
