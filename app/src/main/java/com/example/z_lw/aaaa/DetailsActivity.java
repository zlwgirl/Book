package com.example.z_lw.aaaa;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by Administrator on 2016/9/28.
 */
public class DetailsActivity extends AppCompatActivity implements View.OnClickListener {
    private Button button;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        button = (Button) findViewById(R.id.button_back);
        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        finish();
    }
}
