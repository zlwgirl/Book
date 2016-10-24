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
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.squareup.picasso.Picasso;

import java.io.File;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


/**
 * Created by Administrator on 2016/9/27.
 */
public class FreeFragment extends Fragment {
    private URL url;
    private HttpURLConnection connection;
    private InputStream is;
    private ViewFlipper flipper;
    private String sourceUrlOne ="http://139.129.215.221:8080/books/wyoos.pdf";
    private String sourceUrlTwo = "http://139.129.215.221:8080/books/LinearAlgebra.pdf";
    private String sourceUrlThree = "http://139.129.215.221:8080/books/AdvancedMathematics.pdf";
    private String imageUrlTwo ="http://139.129.215.221:8080/images/wyoos.png";
    private String imageUrlThree = "http://139.129.215.221:8080/images/LinearAlgebra.png";
    private String imageUrlOne = "http://139.129.215.221:8080/images/AdvancedMathematics.png";

    private ImageView imageView_one,imageView_two,imageView_three;
    private int[] imageId ={R.drawable.cycle1,R.drawable.cycle2,R.drawable.cycle3,R.drawable.cycle4};

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_free,null);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        flipper = (ViewFlipper) view.findViewById(R.id.flipper);
        imageView_one = (ImageView) view.findViewById(R.id.free_imageViewOne);
        imageView_two = (ImageView) view.findViewById(R.id.free_imageViewTwo);
        imageView_three = (ImageView) view.findViewById(R.id.free_imageViewThree);

    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //实现轮播图
        for (int i = 0; i < imageId.length; i++) {
            flipper.addView(getImageView(imageId[i]));
        }
        flipper.setInAnimation(getContext(),R.anim.cycle_in);
        flipper.setOutAnimation(getContext(),R.anim.cycle_out);
        //轮播图时间
        flipper.setFlipInterval(3000);
        flipper.startFlipping();
        //加载图书封面图片
        Picasso.with(getContext()).load(imageUrlOne).into(imageView_one);
        Picasso.with(getContext()).load(imageUrlTwo).into(imageView_two);
        Picasso.with(getContext()).load(imageUrlThree).into(imageView_three);

        imageView_one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            url = new URL(sourceUrlOne);
                            connection = (HttpURLConnection) url.openConnection();
                            is = connection.getInputStream();
                            if (is != null) {
                                //獲取文件擴展名
                                String expandName = sourceUrlOne.substring(sourceUrlOne.lastIndexOf(".")+1,sourceUrlOne.length()).toLowerCase();
                                //獲取文件名
                                String fileName = sourceUrlOne.substring(sourceUrlOne.lastIndexOf("/")+1,sourceUrlOne.lastIndexOf("."));
                                File file = new File("sdcard/"+fileName+"."+expandName);
                                FileOutputStream fos = new FileOutputStream(file);
                                byte buf[] = new byte[1024];
                                while (true){
                                    int numread = is.read(buf);
                                    if (numread<=0){
                                        break;
                                    }else {
                                        fos.write(buf,0,numread);
                                    }
                                }

                            }
                            is.close();
                            connection.disconnect();
                            getActivity().runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(getContext(), "success!", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }catch (MalformedURLException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
                SharedPreferences.Editor sp = getActivity().getSharedPreferences("pdf", Context.MODE_PRIVATE).edit();
                sp.putString("Pdf","wyoos.pdf");
                sp.putString("image",imageUrlOne);
                sp.commit();
                Intent intent = new Intent(getContext(),FreeDetailsActivity.class);
                getActivity().startActivity(intent);
            }
        });
        imageView_two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            url = new URL(sourceUrlTwo);
                            connection = (HttpURLConnection) url.openConnection();
                            is = connection.getInputStream();
                            if (is != null) {
                                //獲取文件擴展名
                                String expandName = sourceUrlTwo.substring(sourceUrlTwo.lastIndexOf(".")+1,sourceUrlTwo.length()).toLowerCase();
                                //獲取文件名
                                String fileName = sourceUrlTwo.substring(sourceUrlTwo.lastIndexOf("/")+1,sourceUrlTwo.lastIndexOf("."));
                                File file = new File("sdcard/"+fileName+"."+expandName);
                                FileOutputStream fos = new FileOutputStream(file);
                                byte buf[] = new byte[1024];
                                while (true){
                                    int numread = is.read(buf);
                                    if (numread<=0){
                                        break;
                                    }else {
                                        fos.write(buf,0,numread);
                                    }
                                }

                            }
                            is.close();
                            connection.disconnect();
                        } catch (MalformedURLException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    }
                }).start();
                SharedPreferences.Editor sp = getActivity().getSharedPreferences("pdf", Context.MODE_PRIVATE).edit();
                sp.putString("Pdf","LinearAlgebra.pdf");
                sp.putString("image",imageUrlTwo);
                sp.commit();
                Intent intent = new Intent(getContext(),FreeDetailsActivity.class);
                getActivity().startActivity(intent);
            }
        });
        imageView_three.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            url = new URL(sourceUrlThree);
                            connection = (HttpURLConnection) url.openConnection();
                            is = connection.getInputStream();
                            if (is != null) {
                                //獲取文件擴展名
                                String expandName = sourceUrlThree.substring(sourceUrlThree.lastIndexOf(".")+1,sourceUrlThree.length()).toLowerCase();
                                //獲取文件名
                                String fileName = sourceUrlThree.substring(sourceUrlThree.lastIndexOf("/")+1,sourceUrlThree.lastIndexOf("."));
                                File file = new File("sdcard/"+fileName+"."+expandName);
                                FileOutputStream fos = new FileOutputStream(file);
                                byte buf[] = new byte[1024];
                                while (true){
                                    int numread = is.read(buf);
                                    if (numread<=0){
                                        break;
                                    }else {
                                        fos.write(buf,0,numread);
                                    }
                                }
                            }
                            is.close();
                            connection.disconnect();
                        } catch (MalformedURLException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    }
                }).start();
                SharedPreferences.Editor sp = getActivity().getSharedPreferences("pdf", Context.MODE_PRIVATE).edit();
                sp.putString("Pdf","AdvancedMathematics.pdf");
                sp.putString("image",imageUrlThree);
                sp.commit();
                Intent intent = new Intent(getContext(),FreeDetailsActivity.class);
                getActivity().startActivity(intent);
            }
        });
    }
    public ImageView getImageView(int imageId ){
        ImageView image = new ImageView(getContext());
        image.setBackgroundResource(imageId);
        return image;
    }
}


