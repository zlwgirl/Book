package com.example.z_lw.aaaa;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Z-LW on 2016/10/6.
 * 好友页面
 */
public class MyFragment extends Fragment implements View.OnClickListener {
    private ImageView upLoad;
    private GridView gridView;
    private MyGrideViewAdapter myGrideViewAdapter;
    private List<MyGriderViewBean> data;

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
        gridView = (GridView) view.findViewById(R.id.my_grideview);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        upLoad.setOnClickListener(this);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            }
        });
        myGrideViewAdapter = new MyGrideViewAdapter(getContext());
        data = new ArrayList<>();
        data.add(new MyGriderViewBean(R.mipmap.photo,"书的名字"));
        myGrideViewAdapter.setData(data);
        gridView.setAdapter(myGrideViewAdapter);

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
