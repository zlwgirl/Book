package com.example.z_lw.aaaa;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Z-LW on 2016/9/26.
 * 看书页面
 */
public class LookBookFragment extends Fragment implements View.OnClickListener {
    private ImageView bookBtn;
    private DrawerLayout drawerLayout;
    private TextView loginTv;
    private TextView setTv, myMessage;
    private ImageView loginIm, imageView_one,imageView_two,imageView_three;
    private TextView userName;
<<<<<<< HEAD
    private String imageurlone;
    private TextView upLoad;

    /* 头像文件 */
//    private static final String IMAGE_FILE_NAME = "temp_head_image.jpg";
//    /* 请求识别码 */
//    private static final int CODE_GALLERY_REQUEST = 0xa0;//本地
//    private static final int CODE_RESULT_REQUEST = 0xa2;//最终裁剪后的结果
=======
    private String imageurl;
>>>>>>> 03fa6f09adf0a8da1af2d81bafee28cef93dace8


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
<<<<<<< HEAD
=======
//        circleImageView = (CircleImageView) view.findViewById(R.id.drawerlayout_cr_photo);
        bookBtn = (ImageView) view.findViewById(R.id.lookbook_im_title);
>>>>>>> 03fa6f09adf0a8da1af2d81bafee28cef93dace8
        setTv = (TextView) view.findViewById(R.id.tv_set);
        myMessage = (TextView) view.findViewById(R.id.my_message);
        loginTv = (TextView) view.findViewById(R.id.login_tv);
        userName = (TextView) view.findViewById(R.id.username_tv);
<<<<<<< HEAD
        upLoad = (TextView) view.findViewById(R.id.upload);
        imageView_one = (ImageView) view.findViewById(R.id.look_imageViewOne);
    }
        @Override
        public void onActivityCreated (@Nullable Bundle savedInstanceState){
            super.onActivityCreated(savedInstanceState);
            //接受书城页面传过来的网址SP
            SharedPreferences preferences = getActivity().getSharedPreferences("pdf", Context.MODE_PRIVATE);
            imageurlone = preferences.getString("image", "http://58pic.ooopic.com/58pic/14/70/68/34858PIC6sf.jpg");
            Picasso.with(getContext()).load(imageurlone).into(imageView_one);

            SharedPreferences spUsername = getActivity().getSharedPreferences("Register", getActivity().MODE_PRIVATE);
            String name = spUsername.getString("userName", "");
            userName.setText(name);


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
            upLoad.setOnClickListener(this);
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
                case R.id.upload:
                    Intent intentUpLoad = new Intent(getContext(), UpLoadActivity.class);
                    startActivity(intentUpLoad);
                    break;
=======
//        imageView_look = (ImageView) view.findViewById(R.id.look_imageView);
        imageView_look = (ImageView) view.findViewById(R.id.look_imageViewOne);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //接受书城页面传过来的网址SP
        SharedPreferences preferences = getActivity().getSharedPreferences("download", Context.MODE_PRIVATE);
        imageurl = preferences.getString("url", "http://58pic.ooopic.com/58pic/14/70/68/34858PIC6sf.jpg");
        Picasso.with(getContext()).load(imageurl).into(imageView_look);
        SharedPreferences spUsername = getActivity().getSharedPreferences("Register", getActivity().MODE_PRIVATE);
        String name = spUsername.getString("userName", "");
        userName.setText(name);



// 按钮点击弹出抽屉
        drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);// 锁定当前行
        bookBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(Gravity.LEFT);
>>>>>>> 03fa6f09adf0a8da1af2d81bafee28cef93dace8
            }
        });

        // 头击像的点击
//        circleImageView.setOnClickListener(this);
        // 设置点
        setTv.setOnClickListener(this);
        myMessage.setOnClickListener(this);
        loginTv.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
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

<<<<<<< HEAD

=======
    //异步任务将图片显示在imageview上
    class MyAsync extends AsyncTask<String, Void, Bitmap> {
        URL url = null;
        HttpURLConnection connection = null;
        InputStream stream = null;
        Bitmap bitmap = null;

        @Override
        protected Bitmap doInBackground(String... strings) {
            try {
                url = new URL(strings[0]);
                connection = (HttpURLConnection) url.openConnection();
                stream = connection.getInputStream();
                bitmap = BitmapFactory.decodeStream(stream);

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                stream.close();
                connection.disconnect();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return bitmap;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            imageView_look.setImageBitmap(bitmap);
        }
    }
>>>>>>> 03fa6f09adf0a8da1af2d81bafee28cef93dace8
//    public void cropPhoto(Uri uri) {
//        Intent intent = new Intent("com.android.camera.action.CROP");
//        intent.setDataAndType(uri, "image/*");
//        intent.putExtra("crop", "true");
//        // aspectX aspectY 是宽高的比例
//        intent.putExtra("aspectX", 1);
//        intent.putExtra("aspectY", 1);
//        // outputX outputY 是裁剪图片宽高
//        intent.putExtra("outputX", 150);
//        intent.putExtra("outputY", 150);
//        intent.putExtra("return-data", true);
//        startActivityForResult(intent, 3);
//    }


}

