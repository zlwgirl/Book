package com.example.z_lw.aaaa;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
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
    private String sourceUrl = "http://139.129.215.221:8080/books/AdvancedMathematics.pdf";
    private URL url;
    private HttpURLConnection connection;
    private InputStream is;
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
        flipper.setAutoStart(true);  //设置自动播放功能
        flipper.setFlipInterval(3000);    //轮播图间隔时间
        flipper.startFlipping();
        flipper.setInAnimation(getContext(),R.anim.left_cycle_in);
        flipper.setOutAnimation(getContext(),R.anim.left_cycle_out);
        flipper.showPrevious();

        //点击封面书，跳转到详情界面
        //第一本书
        imageView_one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            url = new URL(sourceUrl);
                            connection = (HttpURLConnection) url.openConnection();
                            is = connection.getInputStream();
                            if (is != null) {
                                //获取文件扩建名
                                String expandName = sourceUrl.substring(sourceUrl.lastIndexOf(".") + 1, sourceUrl.length()).toLowerCase();
                                //获取文件名
                                String fileName = sourceUrl.substring(sourceUrl.lastIndexOf("/") + 1, sourceUrl.lastIndexOf("."));
                                File file = new File("sdcard/" + fileName + "." + expandName);
                                FileOutputStream fos = new FileOutputStream(file);
                                byte buf[] = new byte[1024];
                                while (true) {
                                    int numread = is.read(buf);
                                    if (numread <= 0) {
                                        break;
                                    } else {
                                        fos.write(buf, 0, numread);
                                    }
                                }
                            }
                            is.close();
                            connection.disconnect();

                        } catch (MalformedURLException e1) {
                            e1.printStackTrace();
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        }
                    }
                }).start();
                Toast.makeText(getContext(), "文件下载完成，保存到本地！", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getContext(),FreeDetailsActivity.class);
                getActivity().startActivity(intent);
                //点击下载将书封面网址传到第一页
                SharedPreferences.Editor sp = getActivity().getSharedPreferences("download", Context.MODE_PRIVATE).edit();
                sp.putString("url","http://139.129.215.221:8080/images/AdvancedMathematics.png");
                sp.commit();

            }
        });
        //加载图书封面图片
        Picasso.with(getContext()).load("http://139.129.215.221:8080/images/wyoos.png").into(imageView_one);
        Picasso.with(getContext()).load("http://139.129.215.221:8080/images/AdvancedMathematics.png").into(imageView_two);
    }
    public ImageView getImageView(int imageId ){
        ImageView image = new ImageView(getContext());
        image.setBackgroundResource(imageId);
        return image;
    }

}


