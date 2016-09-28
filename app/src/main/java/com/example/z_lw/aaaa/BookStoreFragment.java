package com.example.z_lw.aaaa;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;

/**
 * Created by Z-LW on 2016/9/26.
 */
public class BookStoreFragment extends Fragment implements RadioGroup.OnCheckedChangeListener {
    private RadioGroup radioGroup;
    private FreeFragment freeFragment;
    private PayFragment payFragment;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_bookstore,null);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        radioGroup  = (RadioGroup) view.findViewById(R.id.radioGroup);
        radioGroup.setOnCheckedChangeListener(this);
        radioGroup.check(R.id.radioButton_free);
    }
    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        FragmentManager manager = getChildFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        switch (i){
            case R.id.radioButton_free:
                if (freeFragment == null) {
                    freeFragment = new FreeFragment();
                }
                transaction.replace(R.id.fragment_replace,freeFragment);
                break;
            case R.id.radioButton_pay:
                if (payFragment == null) {
                    payFragment = new PayFragment();
                }
                transaction.replace(R.id.fragment_replace,payFragment);
                break;
        }
        transaction.commit();
    }
}
