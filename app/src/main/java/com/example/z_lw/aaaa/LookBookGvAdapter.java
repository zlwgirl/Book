package com.example.z_lw.aaaa;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import java.util.List;

/**
 * Created by Z-LW on 2016/9/27.
 */
public class LookBookGvAdapter extends BaseAdapter {
    private Context context;
    private List<Photo> pictures;
    public LookBookGvAdapter( Context context)
    {
        this.context = context;
    }

    public void setPictures(List<Photo> pictures) {
        this.pictures = pictures;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
       return pictures != null ? pictures.size():0;
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
        ViewHolder viewHolder;
        if (convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.lookbook_gv_item,parent,false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }else {
         viewHolder = (ViewHolder) convertView.getTag();
        }
        Photo photo = (Photo) getItem(position);
        viewHolder.imageView.setImageResource(photo.getImage());
        return convertView;
    }
    class ViewHolder{
        private ImageView imageView;
        public ViewHolder(View view){
            imageView = (ImageView) view.findViewById(R.id.lookbook_gv_iv);
        }
    }
}
