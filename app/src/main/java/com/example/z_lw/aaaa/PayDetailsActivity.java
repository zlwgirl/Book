package com.example.z_lw.aaaa;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

/**
 * Created by Administrator on 2016/9/29.
 * 付费图书点击的详情界面
 */
public class PayDetailsActivity extends Activity {
    private Button button_back,button_pay;
    private ImageView imageView;
    private String imageurl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paydetails);
        button_back = (Button) findViewById(R.id.pay_button_back);
        button_pay = (Button) findViewById(R.id.button_pay);
        imageView = (ImageView) findViewById(R.id.pay_imageView_details);
        SharedPreferences preferences = getSharedPreferences("paypicture",MODE_PRIVATE);
        imageurl = preferences.getString("pay","");
        Picasso.with(this).load(imageurl).into(imageView);

        button_pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PayDetailsActivity.this,PayDemoActivity.class);
                startActivity(intent);
            }
        });
        button_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

}
