package com.example.z_lw.aaaa;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

/**
 * Created by Z-LW on 2016/10/20.
 * 反馈上传
 */
public class MessageUploadActivity extends Activity implements View.OnClickListener {
    private ImageView messageBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messageupload);
        messageBack = (ImageView) findViewById(R.id.my_massage_back_im);
        messageBack.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.my_massage_back_im:
                finish();
                break;
        }
    }
}
