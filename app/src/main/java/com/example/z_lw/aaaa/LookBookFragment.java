package com.example.z_lw.aaaa;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

/**
 * Created by Z-LW on 2016/9/26.
 * 看书页面
 */
public class LookBookFragment extends Fragment implements View.OnClickListener {
    private ImageView bookBtn;
    private DrawerLayout drawerLayout;
    private TextView loginTv;
    private TextView setTv, myMessage;
    private ImageView imageView_one,imageView_two,imageView_three;
    private String imageurlone;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_lookbook, null);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        drawerLayout = (DrawerLayout) view.findViewById(R.id.drawlayout);
        bookBtn = (ImageView) view.findViewById(R.id.lookbook_im_title);

        setTv = (TextView) view.findViewById(R.id.tv_set);
        myMessage = (TextView) view.findViewById(R.id.my_message);
        loginTv = (TextView) view.findViewById(R.id.login_tv);
        imageView_one = (ImageView) view.findViewById(R.id.look_imageViewOne);
    }
        @Override
        public void onActivityCreated (@Nullable Bundle savedInstanceState){
            super.onActivityCreated(savedInstanceState);
            //接受书城页面传过来的网址SP
            SharedPreferences preferences = getActivity().getSharedPreferences("pdf", Context.MODE_PRIVATE);
            imageurlone = preferences.getString("image", "http://58pic.ooopic.com/58pic/14/70/68/34858PIC6sf.jpg");
            Picasso.with(getContext()).load(imageurlone).into(imageView_one);
            imageView_one.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getContext(),FreeDetailsActivity.class);
                    getActivity().startActivity(intent);
                }
            });



// 按钮点击弹出抽屉
            drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);// 锁定当前行
            bookBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    drawerLayout.openDrawer(Gravity.LEFT);
                }
            });

            setTv.setOnClickListener(this);
            myMessage.setOnClickListener(this);
            loginTv.setOnClickListener(this);
        }
        @Override
        public void onClick (View view){
            switch (view.getId()) {
                case R.id.tv_set:
                    Intent intent = new Intent(getContext(), SettingActivity.class);
                    startActivity(intent);
                    break;
                case R.id.my_message:
                    Intent intentMessage = new Intent(getContext(), MyMessageActicity.class);
                    startActivity(intentMessage);
                    break;
                case R.id.login_tv:
                    Intent intentLogin = new Intent(getContext(), LoginActivity.class);
                    startActivity(intentLogin);
                    break;
            }
    }


}

