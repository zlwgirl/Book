package com.example.z_lw.aaaa;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Z-LW on 2016/9/26.
 */
public class LookBookFragment extends Fragment {
    private Button bookBtn;
    private GridView gridView;
    private LookBookGvAdapter gvAdapter;
    private List<Photo> photo;
    private DrawerLayout drawerLayout;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_lookbook,null);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        bookBtn  = (Button) view.findViewById(R.id.lookbook_bt_title);
        gridView = (GridView) view.findViewById(R.id.lookbook_gv);
        drawerLayout = (DrawerLayout) view.findViewById(R.id.drawlayout);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        photo = new ArrayList<>();
        for (int i = 0; i < photo.size(); i++) {
            photo.add(new Photo(R.mipmap.ic_launcher));
        }

        gvAdapter = new LookBookGvAdapter(getContext(),photo);
        gridView.setAdapter(gvAdapter);

        bookBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                drawerLayout.openDrawer(Gravity.LEFT);
            }
        });


        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getContext(), "hahaha", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
