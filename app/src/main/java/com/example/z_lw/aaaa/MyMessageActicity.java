package com.example.z_lw.aaaa;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Z-LW on 2016/9/28.
 * 我的资料
 */
public class MyMessageActicity extends Activity implements View.OnClickListener {
    private TextView about,message_upload;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mymessage);
        ImageView back = (ImageView) findViewById(R.id.message_back_im);
        message_upload = (TextView) findViewById(R.id.message_upload);
        about = (TextView) findViewById(R.id.about);
        message_upload .setOnClickListener(this);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        about.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.about:
                Intent intent = new Intent(MyMessageActicity.this,AboutOurActivity.class);
                startActivity(intent);
                break;
            case R.id.message_upload:
                Intent intentUploadMessage = new Intent(MyMessageActicity.this,MessageUploadActivity.class);
                startActivity(intentUploadMessage);
                break;
        }
    }
}
