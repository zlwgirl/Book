package com.example.z_lw.aaaa;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.TextView;

/**
 * Created by Administrator on 2016/9/28.
 * 欢迎页
 */
public class WelComeActivity extends Activity {
    private CountDownTimer timer;
    private TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        textView = (TextView) findViewById(R.id.textView);
        timer = new CountDownTimer(3000,1000) {
            @Override
            public void onTick(long l) {
                textView.setText(l/1000+"秒后跳转");
            }

            @Override
            public void onFinish() {
                Intent intent = new Intent(WelComeActivity.this,MainActivity.class);
                startActivity(intent);
                finish();

            }
        }.start();
    }


}
