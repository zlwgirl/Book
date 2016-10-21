package com.example.z_lw.aaaa;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Z-LW on 2016/10/19.
 */
public class MyGrideViewAdapter extends BaseAdapter {
    private List<MyGriderViewBean> data;
    private Context context;

    public MyGrideViewAdapter( Context context) {
        this.context = context;
    }

    public void setData(List<MyGriderViewBean> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return data != null ? data.size() : 0;
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        GrideViewHolder holder =null;
        if (convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.item_mygrideview,viewGroup,false);
            holder = new GrideViewHolder(convertView);
            convertView.setTag(holder);
        }else {
            holder = (GrideViewHolder) convertView.getTag();
        }
        holder.nameTv.setText(data.get(position).getName());
        holder.photoImage.setImageResource(data.get(position).getImageId());
        return convertView;
    }



    class GrideViewHolder{
        private ImageView photoImage;
        private TextView nameTv;
        public GrideViewHolder(View view){
            photoImage = (ImageView) view.findViewById(R.id.item_image);
            nameTv = (TextView) view.findViewById(R.id.item_text);
        }
    }
}
