package com.example.z_lw.aaaa;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import android.widget.RadioButton;
import android.widget.RadioGroup;



/**
 * Created by Administrator on 2016/9/27.
 */
public class FreeFragment extends Fragment implements AdapterView.OnItemClickListener, RadioGroup.OnCheckedChangeListener {
    private FreeGridViewAdapter gridViewAdapter;
    private GridView gridView;
    private ViewPager viewPager;
    private RadioGroup radioGroup;
    private MyAdapter myAdapter;
    private RadioButton radioButton_one,radioButton_two,radioButton_three,radioButton_four;
    private Handler handler;
    private int[] images ={R.drawable.cycle1,R.drawable.cycle2,R.drawable.cycle3,R.drawable.cycle4};

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_free,null);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        gridView = (GridView) view.findViewById(R.id.gridView);
        viewPager = (ViewPager) view.findViewById(R.id.viewpager_cycle);
        radioGroup = (RadioGroup) view.findViewById(R.id.cycle_rg);
        radioButton_one = (RadioButton) view.findViewById(R.id.rb_one);
        radioButton_two = (RadioButton) view.findViewById(R.id.rb_two);
        radioButton_three = (RadioButton) view.findViewById(R.id.rb_three);
        radioButton_four = (RadioButton) view.findViewById(R.id.rb_four);



    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        gridViewAdapter = new FreeGridViewAdapter(getContext());
        gridView.setAdapter(gridViewAdapter);
        gridView.setOnItemClickListener(this);
        myAdapter = new MyAdapter(getChildFragmentManager());
        radioGroup.setOnCheckedChangeListener(this);
        viewPager.setAdapter(myAdapter);
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position){
                    case 0:
                        radioButton_one = (RadioButton) getView().findViewById(R.id.rb_one);
                        radioButton_one.setChecked(true);
                        break;
                    case 1:
                        radioButton_two = (RadioButton) getView().findViewById(R.id.rb_two);
                        radioButton_two.setChecked(true);
                        break;
                    case 2:
                        radioButton_three = (RadioButton) getView().findViewById(R.id.rb_three);
                        radioButton_three.setChecked(true);
                        break;
                    case 3:
                        radioButton_four = (RadioButton) getView().findViewById(R.id.rb_four);
                        radioButton_four.setChecked(true);
                        break;
                }

            }
            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
        handler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message message) {
                viewPager.setCurrentItem(viewPager.getCurrentItem()+1);
                return true;
            }
        });
            new Thread(new Runnable() {
                @Override
                public void run() {
                        try {
                            Thread.sleep(3000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        handler.sendEmptyMessage(0);
                    }
            }).start();

    }
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Intent intent = new Intent(getContext(),FreeDetailsActivity.class);
        getActivity().startActivity(intent);

    }


    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        switch (i){
            case R.id.rb_one:
                viewPager.setCurrentItem(0);
                break;
            case R.id.rb_two:
                viewPager.setCurrentItem(1);
                break;
            case R.id.rb_three:
                viewPager.setCurrentItem(2);
                break;
            case R.id.rb_four:
                viewPager.setCurrentItem(3);
                break;
        }
    }
    class MyAdapter extends FragmentPagerAdapter{

        public MyAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            ImageFragment imageFragment = new ImageFragment();
            Bundle bundle = new Bundle();
            bundle.putInt("res",images[position]);
            imageFragment.setArguments(bundle);
            return imageFragment;
        }

        @Override
        public int getCount() {
            return 4;
        }
    }
}


