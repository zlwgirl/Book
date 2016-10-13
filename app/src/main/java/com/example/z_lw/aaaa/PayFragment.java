package com.example.z_lw.aaaa;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import net.tsz.afinal.core.AsyncTask;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Administrator on 2016/9/27.
 */
public class PayFragment extends Fragment {
    private ImageView imageView_pay;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pay,null);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        imageView_pay = (ImageView) view.findViewById(R.id.pay_imageView_one);
        imageView_pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor sp = getActivity().getSharedPreferences("paypicture", Context.MODE_PRIVATE).edit();
                sp.putString("pay","http://139.129.215.221:8080/images/wyoos.png");
                sp.commit();
                Intent intent = new Intent(getContext(),PayDetailsActivity.class);
                getActivity().startActivity(intent);
            }
        });
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Picasso.with(getContext()).load("http://139.129.215.221:8080/images/wyoos.png").into(imageView_pay);
    }

}
