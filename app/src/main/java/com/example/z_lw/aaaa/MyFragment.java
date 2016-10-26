package com.example.z_lw.aaaa;

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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by Z-LW on 2016/10/6.
 * 好友页面
 */
public class MyFragment extends Fragment implements View.OnClickListener {
    private ImageView upLoad;
    private ImageView photoImage;
    private TextView nameTv;
    private TextView companyName;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my, null);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        upLoad = (ImageView) view.findViewById(R.id.my_upload);
        photoImage = (ImageView) view.findViewById(R.id.item_image);
        nameTv = (TextView) view.findViewById(R.id.item_text);
        companyName = (TextView) view.findViewById(R.id.compang_name);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        upLoad.setOnClickListener(this);
        companyName.setText("公司名/用户名");
        SharedPreferences sp = getActivity().getSharedPreferences("UPLOAD", getActivity().MODE_PRIVATE);
        String bookname = sp.getString("name", "");
        final String picture = sp.getString("picture", "");
        nameTv.setText(bookname);
        SharedPreferences spUsername = getActivity().getSharedPreferences("Register", getActivity().MODE_PRIVATE);
        String name = spUsername.getString("userName", "");
        companyName.setText(name);
        final String PHOTO = "http://139.129.215.221:8080" + picture+".jpg";

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url = new URL(PHOTO);
                    URLConnection conn = url.openConnection();
                    InputStream is  = conn.getInputStream();
                    final Bitmap bitmap = BitmapFactory.decodeStream(is);
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getContext(), "sucess", Toast.LENGTH_SHORT).show();
                            photoImage.setImageBitmap(bitmap);
                        }
                    });
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.my_upload:
                Intent upLoadIntent = new Intent(getActivity(), UpLoadActivity.class);
                startActivity(upLoadIntent);
                break;
        }
    }

}
