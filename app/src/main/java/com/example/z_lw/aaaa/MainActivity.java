package com.example.z_lw.aaaa;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener {
    private RadioGroup radioGroup;
    private LookBookFragment lookBookFragment;
    private BookStoreFragment bookStoreFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        radioGroup.setOnCheckedChangeListener(this);
        radioGroup.check(R.id.radioButtonOne);
    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        switch (i) {
            case R.id.radioButtonOne:
                if (lookBookFragment == null) {
                    lookBookFragment = new LookBookFragment();
                }
                transaction.replace(R.id.frameLayout, lookBookFragment);
                break;
            case R.id.radioButtonTwo:
                if (bookStoreFragment == null) {
                    bookStoreFragment = new BookStoreFragment();
                }
                transaction.replace(R.id.frameLayout, bookStoreFragment);
                break;
        }
        transaction.commit();
    }
}
