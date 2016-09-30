package com.example.z_lw.aaaa;

import android.content.Context;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

/**
 * Created by Administrator on 2016/9/27.
 * 书城中轮播图下方的平铺布局的适配器
 */
public class GridViewAdapter extends BaseAdapter {
    private Context context;
    private List<Photo> pictures;
    public GridViewAdapter(Context context) {
        this.context = context;
    }
    public void setPictures(List<Photo> pictures) {
        this.pictures = pictures;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return pictures!=null?pictures.size():3;
    }

    @Override
    public Object getItem(int position) {
        return pictures.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        GrideViewHolder grideViewHolder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_gridview,parent,false);
            grideViewHolder = new GrideViewHolder(convertView);
            convertView.setTag(grideViewHolder);
        }else {
            grideViewHolder = (GrideViewHolder) convertView.getTag();
        }
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url = new URL("http://139.129.215.221:8080/images/AdvancedMathematics.png");
                    URLConnection connection = url.openConnection();
                    InputStream inputStream = connection.getInputStream();


                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        grideViewHolder.imageView.setImageResource(R.mipmap.timg);
        return convertView;
    }
    class GrideViewHolder{
        private ImageView imageView;
        public GrideViewHolder(View view){
            imageView = (ImageView) view.findViewById(R.id.imageView);
        }

    }
}
