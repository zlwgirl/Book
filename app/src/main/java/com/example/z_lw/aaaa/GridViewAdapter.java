package com.example.z_lw.aaaa;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

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
        grideViewHolder.imageView.setImageResource(R.mipmap.ic_launcher);
        return convertView;
    }
    class GrideViewHolder{
        private ImageView imageView;
        public GrideViewHolder(View view){
            imageView = (ImageView) view.findViewById(R.id.imageView);
        }

    }
}
