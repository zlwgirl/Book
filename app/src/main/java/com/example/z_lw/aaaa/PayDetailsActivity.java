package com.example.z_lw.aaaa;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

/**
 * Created by Administrator on 2016/9/29.
 * 付费图书点击的详情界面
 */
public class PayDetailsActivity extends Activity {
    private Button button_back;
    private Button button_pay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paydetails);
        button_back = (Button) findViewById(R.id.pay_button_back);
        button_pay = (Button) findViewById(R.id.button_pay);
        button_pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(PayDetailsActivity.this, "购买此书", Toast.LENGTH_LONG).show();

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
