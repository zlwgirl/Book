package com.example.z_lw.aaaa;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
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
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


/**
 * Created by Administrator on 2016/9/27.
 */
public class FreeFragment extends Fragment implements AdapterView.OnItemClickListener, RadioGroup.OnCheckedChangeListener {
    private ViewPager viewPager;
    private RadioGroup radioGroup;
    private MyAdapter myAdapter;
    private RadioButton radioButton_one,radioButton_two,radioButton_three,radioButton_four;
    private Handler handler;
    private ImageView imageView_one,imageView_two;
    private int[] images ={R.drawable.cycle1,R.drawable.cycle2,R.drawable.cycle3,R.drawable.cycle4};


    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_free,null);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewPager = (ViewPager) view.findViewById(R.id.viewpager_cycle);
        radioGroup = (RadioGroup) view.findViewById(R.id.cycle_rg);
        radioButton_one = (RadioButton) view.findViewById(R.id.rb_one);
        radioButton_two = (RadioButton) view.findViewById(R.id.rb_two);
        radioButton_three = (RadioButton) view.findViewById(R.id.rb_three);
        radioButton_four = (RadioButton) view.findViewById(R.id.rb_four);
        imageView_one = (ImageView) view.findViewById(R.id.imageView_one);
        imageView_two = (ImageView) view.findViewById(R.id.imageView_two);


    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        myAdapter = new MyAdapter(getChildFragmentManager());
        radioGroup.setOnCheckedChangeListener(this);
        viewPager.setAdapter(myAdapter);
        //点击封面书，跳转到详情界面
        imageView_one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(),FreeDetailsActivity.class);
                getActivity().startActivity(intent);
            }
        });
        new MyAsyntask().execute("http://139.129.215.221:8080/images/wyoos.png",
                "http://139.129.215.221:8080/images/AdvancedMathematics.png");
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
                viewPager.setCurrentItem(viewPager.getCurrentItem());
                return true;
            }
        });
        //开线程，轮播图轮播时间
//            new Thread(new Runnable() {
//                @Override
//                public void run() {
//                        try {
//                            Thread.sleep(3000);
//                        } catch (InterruptedException e) {
//                            e.printStackTrace();
//                        }
//                        handler.sendEmptyMessage(0);
//                    }
//            }).start();

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

    class MyAsyntask extends AsyncTask<String,Void ,Bitmap> {
        @Override
        protected Bitmap doInBackground(String... strings) {
            URL url = null;
            HttpURLConnection connection = null;
            InputStream stream = null;
            Bitmap bitmap = null;
            try {
                url = new URL(strings[1]);
                connection = (HttpURLConnection) url.openConnection();
                stream = connection.getInputStream();
                bitmap = BitmapFactory.decodeStream(stream);
            } catch (MalformedURLException e) {
                    e.printStackTrace();
            } catch (IOException e) {
                    e.printStackTrace();
            }
            return bitmap;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            imageView_one.setImageBitmap(bitmap);
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


