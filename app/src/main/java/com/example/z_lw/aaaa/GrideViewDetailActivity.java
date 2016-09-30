package com.example.z_lw.aaaa;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

/**
 * Created by Z-LW on 2016/9/29.
 * 看书页的详情
 */
public class GrideViewDetailActivity extends Activity {
    private ImageView back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grideviewdetail);
        back = (ImageView) findViewById(R.id.grideview_back_im);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
