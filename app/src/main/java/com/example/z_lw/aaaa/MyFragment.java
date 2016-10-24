package com.example.z_lw.aaaa;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

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
        View view = inflater.inflate(R.layout.fragment_my,null);
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
        SharedPreferences sp = getActivity().getSharedPreferences("UPLOAD", Context.MODE_PRIVATE);
       String bookname = sp.getString("name","");
        String pic = sp.getString("picture","");
        photoImage.setImageResource(R.mipmap.book_replace);
        nameTv.setText(bookname);
        SharedPreferences spUsername = getActivity().getSharedPreferences("Register", getActivity().MODE_PRIVATE);
        String name = spUsername.getString("userName", "");
        companyName.setText(name);


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.my_upload:
                Intent upLoadIntent = new Intent(getActivity(),UpLoadActivity.class);
                startActivity(upLoadIntent);
                break;
        }
    }
}
